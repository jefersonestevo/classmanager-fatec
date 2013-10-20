package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;

public class ConsultarUsuarioGrupoAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

}
