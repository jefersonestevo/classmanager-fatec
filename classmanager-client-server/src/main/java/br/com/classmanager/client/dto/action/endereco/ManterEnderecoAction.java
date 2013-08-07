package br.com.classmanager.client.dto.action.endereco;

import br.com.classmanager.client.dto.def.impl.DTOManterActionBase;
import br.com.classmanager.client.entidades.endereco.Endereco;
import br.com.classmanager.client.enums.AcaoManter;

public class ManterEnderecoAction extends DTOManterActionBase<Endereco, Long> {

	private static final long serialVersionUID = 4324323666804805478L;

	public ManterEnderecoAction(AcaoManter acao) {
		super(acao);
	}

}
