package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.impl.DTOPesquisarActionBase;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.client.enums.AcaoPesquisar;

public class PesquisarServicoEnvioAction extends
		DTOPesquisarActionBase<ServicoEnvio, Long> {

	private static final long serialVersionUID = -832017706298793576L;

	public PesquisarServicoEnvioAction(AcaoPesquisar acao) {
		super(acao);
	}

}
