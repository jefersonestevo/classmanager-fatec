package br.com.classmanager.client.dto.action;

import br.com.classmanager.client.dto.def.impl.DTOManterActionBase;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.enums.AcaoManter;

public class ManterGrupoAction extends DTOManterActionBase<Grupo, Long>{

	private static final long serialVersionUID = 1L;

	public ManterGrupoAction(AcaoManter acao) {
		super(acao);
	}

}
