package br.com.classmanager.client.dto.def;

import br.com.classmanager.client.entidades.usuario.Usuario;

public class DTOServicoAction implements DTOAction {

	private static final long serialVersionUID = 5246241998359527477L;

	protected Usuario usuarioAtual;

	public Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Usuario usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

}
