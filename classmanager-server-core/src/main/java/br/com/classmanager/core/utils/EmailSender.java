package br.com.classmanager.core.utils;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
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

import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.core.exceptions.ClassManagerEmailException;

public class EmailSender {

	public static void enviarEmail(String email, String assunto, String conteudo)
			throws ClassManagerEmailException {
		enviarEmail(email, assunto, conteudo, null);
	}

	public static void enviarEmail(String email, String assunto,
			String conteudo, List<ArquivoAnexo> anexos)
			throws ClassManagerEmailException {

		try {
			Session session = getSession();
			dispararEmail(session, "hintersystem@gmail.com", email, assunto,
					conteudo, anexos);
		} catch (ClassManagerEmailException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ClassManagerEmailException(
					CodigoExcecao.ERRO_ENVIO_EMAIL, e);
		}
	}

	private static void dispararEmail(Session session, String emailDe,
			String email, String assunto, String conteudo,
			List<ArquivoAnexo> anexos) throws ClassManagerEmailException {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailDe));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));

			message.setSentDate(new Date());
			message.setSubject(assunto);

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(conteudo, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (CollectionUtils.isNotEmpty(anexos)) {
				for (ArquivoAnexo anexo : anexos) {
					BodyPart attachmentoBodyPart = new MimeBodyPart();
					DataSource source = new ByteArrayDataSource(
							anexo.getDados(), "Application/octet-stream");
					attachmentoBodyPart.setDataHandler(new DataHandler(source));
					attachmentoBodyPart.setFileName(anexo.getNomeArquivo());
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

	private static Session getSession() {
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
								"hintersystem@gmail.com", "hintersystem1");
					}
				});
	}

	public static final class ArquivoAnexo {
		private String nomeArquivo;
		private byte[] dados;

		public String getNomeArquivo() {
			return nomeArquivo;
		}

		public void setNomeArquivo(String nomeArquivo) {
			this.nomeArquivo = nomeArquivo;
		}

		public byte[] getDados() {
			return dados;
		}

		public void setDados(byte[] dados) {
			this.dados = dados;
		}
	}
}
