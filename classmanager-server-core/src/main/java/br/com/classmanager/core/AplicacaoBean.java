package br.com.classmanager.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.classmanager.client.EventoTeste;
import br.com.classmanager.client.IAppBean;
import br.com.classmanager.client.componentes.qualifiers.Assincrono;
import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.entidades.geral.ConteudoEmail;
import br.com.classmanager.client.entidades.geral.Email;
import br.com.classmanager.client.entidades.geral.EnvioEmail;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.core.componentes.interceptors.UserAware;
import br.com.classmanager.server.domain.eventos.EnvioEmailEvento;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.exceptions.ClassManagerConstraintBDException;
import br.com.classmanager.server.domain.modelo.dao.interfaces.geral.IDaoConteudoEmail;
import br.com.classmanager.server.domain.modelo.dao.interfaces.geral.IDaoEmail;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;

@ApplicationScoped
@Stateful
public class AplicacaoBean implements IAppBean, Serializable {

	private static final long serialVersionUID = 6002645531392370230L;

	private LinkedList<String> lista = new LinkedList<String>();

	@Inject
	@DAO
	private IDaoUsuario daoUsuario;

	@Inject
	@DAO
	private IDaoEmail daoEmail;

	@Inject
	@DAO
	private IDaoConteudoEmail daoConteudoEmail;

	@Inject
	@Assincrono
	private Event<EnvioEmailEvento> eventoEnvioEmail;

	@Inject
	private Event<EventoTeste> evento;

	@UserAware
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void atualizar(DTOAction action, String texto)
			throws ClassManagerException {
		if (texto == null || "".equals(texto))
			return;
		lista.add(texto);
		evento.fire(new EventoTeste(texto));

		try {

			List<Usuario> listaUsuario = daoUsuario.pesquisarLista();
			if (CollectionUtils.isEmpty(listaUsuario)) {
				Usuario usuario = new Usuario();
				usuario.setNome("Jeferson Estevo");
				usuario.setEmail("jefersonestevo@gmail.com");

				daoUsuario.inserir(usuario);
				listaUsuario.add(usuario);
			}

			ConteudoEmail conteudo = new ConteudoEmail();
			conteudo.setAssunto("teste");
			conteudo.setConteudo("<b>bla</b>bla");
			daoConteudoEmail.inserir(conteudo);

			Email email = new Email();
			email.setConteudoEmail(conteudo);
			email.setDe(listaUsuario.get(0));
			email.setListaEnvios(new ArrayList<EnvioEmail>());
			email.setDataEnvioOriginal(new Date());
			daoEmail.inserir(email);

			List<Long> idUsuarios = new ArrayList<Long>();
			for (Usuario usuario : listaUsuario) {
				idUsuarios.add(usuario.getId());
			}

			EnvioEmailEvento envioEmailEvt = new EnvioEmailEvento(
					email.getId(), idUsuarios);

			System.out.println("DISPARANDO EVENTO APP BEAN");
			this.eventoEnvioEmail.fire(envioEmailEvt);
			System.out.println("CONTINUACAO PROC APP BEAN");

		} catch (ClassManagerConstraintBDException e) {
			throw e.getConstraintException();
		} catch (ClassManagerException e) {
			e.printStackTrace();
		}

	}

	public LinkedList<String> getLista() {
		return lista;
	}

}
