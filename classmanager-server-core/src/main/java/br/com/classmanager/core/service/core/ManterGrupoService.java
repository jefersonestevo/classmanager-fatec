package br.com.classmanager.core.service.core;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ManterGrupoAction;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;
import br.com.classmanager.server.domain.service.impl.ServicoManterBase;

@Named("ManterGrupoAction")
public class ManterGrupoService extends
		ServicoManterBase<ManterGrupoAction, Grupo, Long> {

	@Inject
	@DAO
	private IDaoGrupo dao;

	@Override
	protected IDaoGrupo getDao() {
		return dao;
	}

}
