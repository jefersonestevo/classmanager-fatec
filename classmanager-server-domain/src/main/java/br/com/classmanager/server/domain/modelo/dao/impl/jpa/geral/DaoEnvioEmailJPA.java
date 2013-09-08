package br.com.classmanager.server.domain.modelo.dao.impl.jpa.geral;

import javax.inject.Named;

import br.com.classmanager.client.entidades.geral.EnvioEmail;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.geral.IDaoEnvioEmail;

@Named
@DAO
public class DaoEnvioEmailJPA extends DaoCRUDJPA<EnvioEmail, Long> implements
		IDaoEnvioEmail {

	private static final long serialVersionUID = 3461653761857974099L;

	@Override
	protected Class<EnvioEmail> getEntidadePersistente() {
		return EnvioEmail.class;
	}

}
