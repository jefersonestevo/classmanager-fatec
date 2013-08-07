package br.com.classmanager.client.dto.action.endereco;

import br.com.classmanager.client.dto.def.impl.DTOManterActionBase;
import br.com.classmanager.client.entidades.endereco.Local;
import br.com.classmanager.client.enums.AcaoManter;

public class ManterLocalAction extends DTOManterActionBase<Local, Long> {

	private static final long serialVersionUID = 7557043639779142087L;

	public ManterLocalAction(AcaoManter acao) {
		super(acao);
	}

}
