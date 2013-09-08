package br.com.classmanager.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.ConsultaPaisAction;
import br.com.classmanager.client.entidades.endereco.Pais;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoPais;
import br.com.classmanager.server.domain.service.impl.ServicoPesquisarBase;

@Named("ConsultaPaisAction")
public class ConsultaPaisService extends
		ServicoPesquisarBase<ConsultaPaisAction, Pais, Long> {

	@Inject
	@DAO
	private IDaoPais dao;

	@Override
	protected IDaoPais getDao() {
		return dao;
	}

}
