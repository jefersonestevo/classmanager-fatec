package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import javax.inject.Named;

import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;

@Named
@DAO
public class DaoGrupoJPA extends DaoCRUDJPA<Grupo, Long> implements IDaoGrupo {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Grupo> getEntidadePersistente() {
		return Grupo.class;
	}

}
