package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ExcluirUsuarioGrupoAction;
import br.com.classmanager.client.dto.geral.NullDTO;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("ExcluirUsuarioGrupoAction")
public class ExcluirUsuarioGrupoService extends
		Servico<ExcluirUsuarioGrupoAction, NullDTO> {

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public NullDTO execute(ExcluirUsuarioGrupoAction request)
			throws ClassManagerException {
		daoUsuarioGrupo.remover(request.getUsuarioGrupo());
		return new NullDTO();
	}

}
