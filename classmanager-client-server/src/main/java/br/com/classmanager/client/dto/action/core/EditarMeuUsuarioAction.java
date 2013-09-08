package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.usuario.Usuario;

public class EditarMeuUsuarioAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
