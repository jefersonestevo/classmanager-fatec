package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;

public class ConsultarPostagemGrupoAction extends DTOServicoAction {

	private static final long serialVersionUID = -832017706298793576L;

	private Long idGrupo;

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

}
