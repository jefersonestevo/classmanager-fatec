package br.com.classmanager.client.dto.action.endereco;

import br.com.classmanager.client.dto.def.impl.DTOPesquisarActionBase;
import br.com.classmanager.client.entidades.endereco.Estado;
import br.com.classmanager.client.enums.AcaoPesquisar;

public class ConsultaEstadoAction extends DTOPesquisarActionBase<Estado, Long> {

	private static final long serialVersionUID = 4126335159272320659L;

	public ConsultaEstadoAction(AcaoPesquisar acao) {
		super(acao);
	}

	private Long idPais;

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

}
