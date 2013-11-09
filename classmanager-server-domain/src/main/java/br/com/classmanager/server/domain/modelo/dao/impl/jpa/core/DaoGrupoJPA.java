package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.enums.StatusUsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
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

	@Override
	public List<Grupo> pesquisarGrupoAtivoPorUsuario(Long idUsuario)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT DISTINCT g FROM ");
		query.append(getEntidadePersistente() + " AS g ");
		query.append(" JOIN g.usuariosGrupo AS ug ");
		query.append(" WHERE g.usuarioCriador.id = ? ");
		query.append(" OR ( ug.usuario.id = ? AND ug.status = ? ) ");

		return getTemplate().pesquisarQuery(
				getEntidadePersistente(),
				query.toString(),
				new Object[] { idUsuario, idUsuario,
						StatusUsuarioGrupo.PARTICIPANTE });
	}

	@Override
	public List<Grupo> pesquisarLista(Long id, String titulo)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append(" SELECT DISTINCT g FROM ");
		query.append(getEntidadePersistente().getName() + " AS g ");
		query.append(" WHERE 1 = 1 ");

		if (id != null) {
			query.append(" AND g.id = ? ");
			params.add(id);
		}

		if (StringUtils.isNotEmpty(titulo)) {
			query.append(" AND upper(g.titulo) like '%"
					+ StringUtils.upperCase(titulo) + "%' ");
		}

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), params.toArray());
	}

	@Override
	public List<Grupo> pesquisarLista(List<Long> idUsuarios)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append(" SELECT DISTINCT g FROM ");
		query.append(getEntidadePersistente().getName() + " AS g ");
		query.append(" WHERE 1 = 2 ");

		for (Long id : idUsuarios) {
			query.append(" OR g.id = ? ");
			params.add(id);
		}

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), params.toArray());
	}

}
