package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import java.util.List;

import javax.inject.Named;

import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
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

	@Override
	public List<UsuarioGrupo> pesquisarPorUsuario(Long id)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT u FROM ");
		query.append(getEntidadePersistente().getName() + " AS u ");
		query.append(" WHERE u.usuario.id = ? ");

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), new Object[] { id });
	}

	@Override
	public List<UsuarioGrupo> pesquisarPorGrupo(Long id)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT u FROM ");
		query.append(getEntidadePersistente().getName() + " AS u ");
		query.append(" WHERE u.grupo.id = ? ");

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), new Object[] { id });
	}

}
