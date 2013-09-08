package br.com.classmanager.core.listener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import br.com.classmanager.client.componentes.qualifiers.Assincrono;
import br.com.classmanager.client.componentes.qualifiers.Sincrono;
import br.com.classmanager.client.entidades.geral.Arquivo;
import br.com.classmanager.client.entidades.geral.Email;
import br.com.classmanager.client.entidades.geral.EnvioEmail;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.core.exceptions.ClassManagerEmailException;
import br.com.classmanager.core.exceptions.ClassManagerEmailException.DadosEmailException;
import br.com.classmanager.server.domain.eventos.EnvioEmailEvento;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.geral.IDaoConteudoEmail;
import br.com.classmanager.server.domain.modelo.dao.interfaces.geral.IDaoEmail;
import br.com.classmanager.server.domain.modelo.dao.interfaces.geral.IDaoEnvioEmail;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;

@Named("EnvioEmailServiceCore")
@Stateless(name = "EnvioEmailListener")
public class EnvioEmailListener implements Serializable {

	private static final long serialVersionUID = 1796719146311562822L;

	private static final Logger log = Logger
			.getLogger(EnvioEmailListener.class);

	@Inject
	@DAO
	private IDaoEnvioEmail daoEnvioEmail;

	@Inject
	@DAO
	private IDaoConteudoEmail daoConteudoEmail;

	@Inject
	@DAO
	private IDaoUsuario daoUsuario;

	@Inject
	@DAO
	private IDaoEmail daoEmail;

	@Asynchronous
	public void enviarEmailAssincrono(@Observes @Assincrono EnvioEmailEvento evt) {
		try {
			enviarEmail(evt);
		} catch (ClassManagerEmailException e) {
			log.error("==========================");
			log.error(getClass().getSimpleName()
					+ " > Erro na execução de envio de email Assincrono.");
			log.error(e.getMessage(), e);
			log.error("==========================");
		}
	}

	public void enviarEmailPadrao(@Observes @Sincrono EnvioEmailEvento evt)
			throws ClassManagerEmailException {
		System.out.println("DISPARANDO EMAIL NAO ASSINCRONO");
		enviarEmail(evt);
	}

	private void enviarEmail(EnvioEmailEvento evt)
			throws ClassManagerEmailException {

		try {
			Session session = getSession();

			ClassManagerEmailException exception = null;

			Email email = daoEmail.pesquisar(evt.getIdEmail());

			if (email.getConteudoEmail().getId() == null) {
				daoConteudoEmail.inserir(email.getConteudoEmail());
			}

			List<Usuario> listaUsuarios = daoUsuario.pesquisarLista(evt
					.getIdUsuarios());
			for (Usuario usuario : listaUsuarios) {
				int qntdTentativas = 0;

				EnvioEmail envioEmail = new EnvioEmail();
				envioEmail.setEmail(email);
				envioEmail.setEmailEnviado(false);
				envioEmail.setQuantidadeTentativasEnvio(qntdTentativas);
				envioEmail.setUltimaDataEnvio(null);
				envioEmail.setPara(usuario);

				daoEnvioEmail.inserir(envioEmail);

				try {

					// dispararEmail(session, envioEmail, evt.getEventoEmail()
					// .getListaAnexos());

					envioEmail.setQuantidadeTentativasEnvio(qntdTentativas + 1);
					envioEmail.setUltimaDataEnvio(new Date());
					envioEmail.setEmailEnviado(true);
					envioEmail = daoEnvioEmail.alterar(envioEmail);
				} catch (Exception e) {
					envioEmail.setUltimaDataEnvio(new Date());
					envioEmail.setQuantidadeTentativasEnvio(qntdTentativas + 1);
					envioEmail.setEmailEnviado(false);
					envioEmail = daoEnvioEmail.alterar(envioEmail);

					if (exception == null) {
						exception = new ClassManagerEmailException(
								CodigoExcecao.ERRO_ENVIO_EMAIL);
					}
					exception.adicionarErroEmail(new DadosEmailException(
							envioEmail.getEmail().getDe().getEmail(),
							envioEmail.getPara().getEmail(), new Date(), e));
				}
			}

			if (exception != null) {
				throw exception;
			}

		} catch (ClassManagerException e) {
			if (e instanceof ClassManagerEmailException) {
				throw (ClassManagerEmailException) e;
			} else {
				throw new ClassManagerEmailException(
						CodigoExcecao.ERRO_DESCONHECIDO_ENVIO_EMAIL, e);
			}
		}
	}

	private void dispararEmail(Session session, EnvioEmail envioEmail,
			List<Arquivo> anexos) throws ClassManagerEmailException {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(envioEmail.getEmail().getDe()
					.getEmail()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(envioEmail.getPara().getEmail()));

			message.setSentDate(new Date());
			message.setSubject(envioEmail.getEmail().getConteudoEmail()
					.getAssunto());

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(envioEmail.getEmail().getConteudoEmail()
					.getConteudo(), "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (CollectionUtils.isNotEmpty(anexos)) {
				for (Arquivo anexo : anexos) {
					BodyPart attachmentoBodyPart = new MimeBodyPart();
					DataSource source = new ByteArrayDataSource(
							anexo.getArquivo(), "Application/octet-stream");
					attachmentoBodyPart.setDataHandler(new DataHandler(source));
					attachmentoBodyPart.setFileName(anexo.getNome());
					multipart.addBodyPart(attachmentoBodyPart);
				}
			}

			message.setContent(multipart);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new ClassManagerEmailException(
					CodigoExcecao.ERRO_ENVIO_EMAIL, e);
		}
	}

	private Session getSession() {
		Properties props = new Properties();

		// TODO - Alterar propriedades
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		return Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"jefersonestevo@gmail.com", "teste01");
					}
				});
	}

}
