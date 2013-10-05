package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.impl.DTOManterActionBase;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;

public class ManterUsuarioAction extends DTOManterActionBase<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String login;
	private String email;

	public ManterUsuarioAction(AcaoManter acao) {
		super(acao);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
