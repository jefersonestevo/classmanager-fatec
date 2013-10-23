package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;

public class ConfirmarEntradaGrupoAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private UsuarioGrupo usuarioGrupo;

	public UsuarioGrupo getUsuarioGrupo() {
		return usuarioGrupo;
	}

	public void setUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		this.usuarioGrupo = usuarioGrupo;
	}

}