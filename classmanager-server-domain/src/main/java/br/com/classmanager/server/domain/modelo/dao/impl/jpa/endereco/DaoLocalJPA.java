package br.com.classmanager.server.domain.modelo.dao.impl.jpa.endereco;

import javax.inject.Named;

import br.com.classmanager.client.entidades.endereco.Local;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.endereco.IDaoLocal;

@Named
@DAO
public class DaoLocalJPA extends DaoCRUDJPA<Local, Long> implements IDaoLocal {

	private static final long serialVersionUID = 3672527260466668732L;

	@Override
	protected Class<Local> getEntidadePersistente() {
		return Local.class;
	}

}
