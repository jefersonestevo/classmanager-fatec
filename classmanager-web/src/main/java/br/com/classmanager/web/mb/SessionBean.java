package br.com.classmanager.web.mb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.classmanager.client.dto.action.core.ConsultarUsuarioAction;
import br.com.classmanager.client.dto.action.core.ConsultarUsuarioGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.entidades.enums.Sexo;
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
	private List<UsuarioGrupo> listaGrupos = new ArrayList<UsuarioGrupo>();
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

	public String deslogar() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
			try {
				ExternalContext ext = FacesContext.getCurrentInstance()
						.getExternalContext();
				ext.redirect(ext.getRequestContextPath()
						+ "/pages/web/restrito/main.jsf");
			} catch (IOException e) {
			}
		}

		return null;
	}

	private synchronized void atualizarUsuario() throws ClassManagerException {
		String login = FacesContext.getCurrentInstance().getExternalContext()
				.getUserPrincipal().getName();
		ConsultarUsuarioAction consultaUsuario = new ConsultarUsuarioAction();
		consultaUsuario.setUsuarioAtual(new Usuario());
		consultaUsuario.setLogin(login);
		Usuario usr = (Usuario) service.execute(consultaUsuario);
		setUsuario(usr);

		ConsultarUsuarioGrupoAction consultaUsuarioGrupo = new ConsultarUsuarioGrupoAction();
		consultaUsuarioGrupo.setIdUsuario(usr.getId());
		ListaDTO<UsuarioGrupo> dto = (ListaDTO<UsuarioGrupo>) service
				.execute(consultaUsuarioGrupo);
		setListaGrupos(dto.getLista());

		atualizarUsuario = false;
	}

	public boolean isAtualizarUsuario() {
		return atualizarUsuario;
	}

	public void setAtualizarUsuario(boolean atualizarUsuario) {
		this.atualizarUsuario = atualizarUsuario;
	}

	public StreamedContent getFotoUsuario() {
		StreamedContent foto = null;
		if (getUsuario().getFotoUsuario() != null) {
			foto = new DefaultStreamedContent(new ByteArrayInputStream(usuario
					.getFotoUsuario().getFoto()));
		} else {
			try {
				String sexo = null;
				if (Sexo.F.equals(getUsuario().getSexo())) {
					sexo = "feminino";
				} else {
					sexo = "masculino";
				}

				ServletContext ctx = (ServletContext) FacesContext
						.getCurrentInstance().getExternalContext().getContext();
				String realPath = ctx.getRealPath("/");
				realPath += "resources/imagens/usuario_" + sexo + ".jpg";
				foto = new DefaultStreamedContent(new ByteArrayInputStream(
						FileUtils.readFileToByteArray(new File(realPath))));
			} catch (IOException e) {
			}
		}
		return foto;
	}

	public void setFotoUsuario(StreamedContent foto) {
	}

	public List<UsuarioGrupo> getListaGrupos() {
		getUsuario();
		return listaGrupos;
	}

	public void setListaGrupos(List<UsuarioGrupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

}
