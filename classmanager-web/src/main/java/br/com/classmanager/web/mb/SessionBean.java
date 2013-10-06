package br.com.classmanager.web.mb;

import java.io.IOException;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.classmanager.client.dto.action.core.ConsultarUsuarioAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@SessionScoped
public class SessionBean extends GenericManagedBean {

	private static final long serialVersionUID = 2290694929905000248L;

	private static final Logger log = Logger.getLogger(SessionBean.class);

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	private Usuario usuario;
	private boolean atualizarUsuario = false;

	public Usuario getUsuario() {
		if (usuario == null || atualizarUsuario) {
			try {
				atualizarUsuario();
			} catch (ClassManagerException e) {
				log.error("Erro ao carregar usuario da sessao. ", e);
			}
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void deslogar() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
		try {
			ExternalContext ext = FacesContext.getCurrentInstance()
					.getExternalContext();
			ext.redirect(ext.getRequestContextPath()
					+ "/pages/web/restrito/main.jsf");
		} catch (IOException e) {
		}
	}

	private synchronized void atualizarUsuario() throws ClassManagerException {
		String login = FacesContext.getCurrentInstance().getExternalContext()
				.getUserPrincipal().getName();
		ConsultarUsuarioAction consultaUsuario = new ConsultarUsuarioAction();
		consultaUsuario.setUsuarioAtual(new Usuario());
		consultaUsuario.setLogin(login);
		Usuario usr = (Usuario) service.execute(consultaUsuario);
		setUsuario(usr);
	}

	public boolean isAtualizarUsuario() {
		return atualizarUsuario;
	}

	public void setAtualizarUsuario(boolean atualizarUsuario) {
		this.atualizarUsuario = atualizarUsuario;
	}

}
