package br.com.classmanager.web.mb.core;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.dto.action.core.EsqueciMinhaSenhaAction;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.web.componentes.qualifiers.ServiceView;
import br.com.classmanager.web.mb.def.GenericManagedBean;
import br.com.classmanager.web.service.ClassManagerServiceView;

@Named
@RequestScoped
public class EsqueciMinhaSenhaBean extends GenericManagedBean {

	private static final long serialVersionUID = -4831748402384198620L;

	@Inject
	@ServiceView
	private ClassManagerServiceView service;

	private EsqueciMinhaSenhaAction action;

	public EsqueciMinhaSenhaBean() {
		action = new EsqueciMinhaSenhaAction();
	}

	public String enviarSenha() {
		try {
			if (StringUtils.isNotEmpty(action.getLogin())
					|| StringUtils.isNotEmpty(action.getEmail())) {
				service.execute(action, true);
				addInfoMessage(getMessage("Esqueci_Minha_Senha_Enviada_Com_Sucesso"));
				action = new EsqueciMinhaSenhaAction();
			} else {
				addErrorMessage(getMessage("Error_Esqueci_Minha_Senha_Login_Email_Nao_Informado"));
			}
		} catch (ClassManagerException e) {
			addExceptionMessage(e);
		}
		return "/pages/web/publico/esqueci_minha_senha.jsf";
	}

	public EsqueciMinhaSenhaAction getAction() {
		return action;
	}

	public void setAction(EsqueciMinhaSenhaAction action) {
		this.action = action;
	}

}
