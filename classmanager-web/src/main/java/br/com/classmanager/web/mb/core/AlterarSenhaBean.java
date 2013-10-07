package br.com.classmanager.web.mb.core;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.AlterarSenhaAction;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@RequestScoped
public class AlterarSenhaBean extends GenericManagedBean {

	private static final long serialVersionUID = -4831748402384198620L;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	private AlterarSenhaAction action = new AlterarSenhaAction();

	public String alterarSenha() {
		try {
			service.execute(action);
			addInfoMessage(getMessage("Alterar_Senha_Sucesso"));
			action = new AlterarSenhaAction();
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		return "/pages/web/restrito/usuario/alterar_senha.jsf";
	}

	public String voltarTelaHome() {
		return "/pages/web/restrito/main.jsf";
	}

	public AlterarSenhaAction getAction() {
		return action;
	}

	public void setAction(AlterarSenhaAction action) {
		this.action = action;
	}

}
