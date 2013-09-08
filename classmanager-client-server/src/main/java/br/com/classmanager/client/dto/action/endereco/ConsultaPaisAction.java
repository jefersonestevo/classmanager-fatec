package br.com.classmanager.client.dto.action.endereco;

import br.com.classmanager.client.dto.def.impl.DTOPesquisarActionBase;
import br.com.classmanager.client.entidades.endereco.Pais;
import br.com.classmanager.client.enums.AcaoPesquisar;

public class ConsultaPaisAction extends DTOPesquisarActionBase<Pais, Long> {

	private static final long serialVersionUID = 7174796523590696739L;

	public ConsultaPaisAction(AcaoPesquisar acao) {
		super(acao);
	}

}
