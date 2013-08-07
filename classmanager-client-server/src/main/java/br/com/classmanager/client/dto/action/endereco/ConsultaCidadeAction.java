package br.com.classmanager.client.dto.action.endereco;

import br.com.classmanager.client.dto.def.impl.DTOPesquisarActionBase;
import br.com.classmanager.client.entidades.endereco.Cidade;
import br.com.classmanager.client.enums.AcaoPesquisar;

public class ConsultaCidadeAction extends DTOPesquisarActionBase<Cidade, Long> {

	private static final long serialVersionUID = 4126335159272320659L;

	public ConsultaCidadeAction(AcaoPesquisar acao) {
		super(acao);
	}

	private Long idEstado;

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

}
