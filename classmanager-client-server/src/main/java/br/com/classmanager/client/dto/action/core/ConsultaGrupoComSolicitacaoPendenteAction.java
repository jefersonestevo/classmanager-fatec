package br.com.classmanager.client.dto.action.core;

import java.util.List;

import br.com.classmanager.client.dto.def.DTOServicoAction;

public class ConsultaGrupoComSolicitacaoPendenteAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private List<Long> idGruposConsulta;

	public List<Long> getIdGruposConsulta() {
		return idGruposConsulta;
	}

	public void setIdGruposConsulta(List<Long> idGruposConsulta) {
		this.idGruposConsulta = idGruposConsulta;
	}

}
