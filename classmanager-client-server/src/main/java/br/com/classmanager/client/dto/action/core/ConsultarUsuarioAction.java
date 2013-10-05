package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;

public class ConsultarUsuarioAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
