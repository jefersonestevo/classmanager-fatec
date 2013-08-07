package br.com.classmanager.server.domain.service;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;

public abstract class Servico<REQ extends DTOServicoAction, RESP extends DTO>
		implements IService<REQ, RESP> {

	/**
	 * Método para verificar, programaticamente, se o usuario informado possui
	 * permissão para execução deste serviço específico. <br />
	 * Default <code>true</code>
	 * 
	 * @param usuario
	 * @return
	 * @throws SisraException
	 */
	public boolean hasPermissao(Usuario usuario)
			throws ClassManagerSecurityException {
		return true;
	}

}
