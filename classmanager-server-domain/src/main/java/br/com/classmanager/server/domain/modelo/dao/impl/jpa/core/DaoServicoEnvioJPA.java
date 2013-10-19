package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import javax.inject.Named;

import br.com.classmanager.client.entidades.core.ServicoEnvio;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoServicoEnvio;

@Named
@DAO
public class DaoServicoEnvioJPA extends DaoCRUDJPA<ServicoEnvio, Long>
		implements IDaoServicoEnvio {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ServicoEnvio> getEntidadePersistente() {
		return ServicoEnvio.class;
	}

}
