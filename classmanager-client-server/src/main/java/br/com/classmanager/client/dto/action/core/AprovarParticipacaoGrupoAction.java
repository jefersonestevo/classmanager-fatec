package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;

public class AprovarParticipacaoGrupoAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private Long idUsuarioGrupo;

	public Long getIdUsuarioGrupo() {
		return idUsuarioGrupo;
	}

	public void setIdUsuarioGrupo(Long idUsuarioGrupo) {
		this.idUsuarioGrupo = idUsuarioGrupo;
	}

}
