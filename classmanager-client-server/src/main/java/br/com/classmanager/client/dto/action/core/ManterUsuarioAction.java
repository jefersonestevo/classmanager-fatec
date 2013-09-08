package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.impl.DTOManterActionBase;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;

public class ManterUsuarioAction extends DTOManterActionBase<Usuario, Long> {

	private static final long serialVersionUID = 1L;

	public ManterUsuarioAction(AcaoManter acao) {
		super(acao);
	}

}
