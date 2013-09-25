package br.com.classmanager.web.mb.core;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ManterUsuarioAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@SessionScoped
public class UsuarioBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

	private Usuario usuario = new Usuario();

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	public UsuarioBean() {
		System.out.println("Entrou no userBean");
	}

	public String inserir() {
		try {
			ManterUsuarioAction action = new ManterUsuarioAction(
					AcaoManter.INSERIR);
			action.setEntidade(usuario);
			this.usuario = (Usuario) service.execute(action);
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return "/pages/web/restrito/usuario.jsf";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
