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
public class UsuarioPublicoBean extends GenericManagedBean {

	private static final long serialVersionUID = 5459527705332664135L;

	private Usuario usuario = new Usuario();
	private String confirmaSenha = "";

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	public String inserir() {
		try {
			if (this.confirmaSenha != null && usuario.getSenha() != null
					&& this.confirmaSenha.equals(usuario.getSenha())) {
				ManterUsuarioAction action = new ManterUsuarioAction(
						AcaoManter.INSERIR);
				action.setEntidade(usuario);
				service.execute(action,true);
				usuario = new Usuario();
				showSuccessMessage();
			} else {
				addErrorMessage(getMessage("Senha_Nao_Confere"));
				return null;
			}

		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}

		return "login";
	}

	public String IrParaTelaDeLogin() {
		return "login";
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
