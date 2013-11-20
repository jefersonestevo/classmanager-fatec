package br.com.classmanager.client.dto.action.core;

import java.util.List;

import br.com.classmanager.client.dto.def.DTOServicoAction;

public class ConsultaUltimosGruposAtualizadosAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private List<Long> idGrupos;

	public List<Long> getIdGrupos() {
		return idGrupos;
	}

	public void setIdGrupos(List<Long> idGrupos) {
		this.idGrupos = idGrupos;
	}

}
