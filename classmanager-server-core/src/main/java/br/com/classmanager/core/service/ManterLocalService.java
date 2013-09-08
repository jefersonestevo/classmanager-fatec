package br.com.classmanager.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.ManterLocalAction;
import br.com.classmanager.client.entidades.endereco.Local;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoLocal;
import br.com.classmanager.server.domain.service.impl.ServicoManterBase;

@Named("ManterLocalAction")
public class ManterLocalService extends
		ServicoManterBase<ManterLocalAction, Local, Long> {

	@Inject
	@DAO
	private IDaoLocal dao;

	public IDaoLocal getDao() {
		return dao;
	}

}
