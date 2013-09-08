package br.com.classmanager.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.ManterUsuarioAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoUsuario;
import br.com.classmanager.server.domain.service.impl.ServicoManterBase;

@Named("ManterUsuarioAction")
public class ManterUsuarioService extends
		ServicoManterBase<ManterUsuarioAction, Usuario, Long> {

	@Inject
	@DAO
	private IDaoUsuario dao;

	@Override
	protected IDaoUsuario getDao() {
		return dao;
	}

}
