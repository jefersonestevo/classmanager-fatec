package br.com.classmanager.core.service.core;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.PesquisarServicoEnvioAction;
import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoServicoEnvio;
import br.com.classmanager.server.domain.service.impl.ServicoPesquisarBase;

@Named("PesquisarServicoEnvioAction")
public class PesquisarServicoEnvioService extends
		ServicoPesquisarBase<PesquisarServicoEnvioAction, ServicoEnvio, Long> {

	@Inject
	@DAO
	private IDaoServicoEnvio dao;

	public IDaoServicoEnvio getDao() {
		return dao;
	}

	public void setDao(IDaoServicoEnvio dao) {
		this.dao = dao;
	}

}
