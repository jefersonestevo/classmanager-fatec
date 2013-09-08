package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import javax.inject.Named;

import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;

@Named
@DAO
public class DaoUsuarioGrupoJPA extends DaoCRUDJPA<UsuarioGrupo, Long>
		implements IDaoUsuarioGrupo {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<UsuarioGrupo> getEntidadePersistente() {
		return UsuarioGrupo.class;
	}

}
