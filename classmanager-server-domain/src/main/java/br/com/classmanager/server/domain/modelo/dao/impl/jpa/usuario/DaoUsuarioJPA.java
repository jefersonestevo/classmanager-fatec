package br.com.classmanager.server.domain.modelo.dao.impl.jpa.usuario;

import java.util.List;

import javax.inject.Named;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.usuario.IDaoUsuario;

@Named
@DAO
public class DaoUsuarioJPA extends DaoCRUDJPA<Usuario, Long> implements
		IDaoUsuario {

	private static final long serialVersionUID = -6158245061878940514L;

	@Override
	protected Class<Usuario> getEntidadePersistente() {
		return Usuario.class;
	}

	@Override
	public List<Usuario> pesquisarLista(List<Long> idUsuarios)
			throws ClassManagerException {
		return getTemplate().pesquisarLista(getEntidadePersistente(),
				idUsuarios);
	}

	@Override
	public List<Usuario> pesquisarLista(String nome, String login, String email)
			throws ClassManagerException {
		boolean hasWhere = false;
		StringBuilder query = new StringBuilder();
		query.append(" SELECT u FROM ");
		query.append(getEntidadePersistente().getName() + " AS u ");

		if (StringUtils.isNotEmpty(nome)) {
			if (!hasWhere) {
				query.append(" WHERE ");
				hasWhere = true;
			} else {
				query.append(" AND ");
			}
			query.append(" upper(u.nome) LIKE '%" + StringUtils.upperCase(nome)
					+ "%' ");
		}

		if (StringUtils.isNotEmpty(login)) {
			if (!hasWhere) {
				query.append(" WHERE ");
				hasWhere = true;
			} else {
				query.append(" AND ");
			}
			query.append(" upper(u.login) LIKE '%"
					+ StringUtils.upperCase(login) + "%' ");
		}

		if (StringUtils.isNotEmpty(email)) {
			if (!hasWhere) {
				query.append(" WHERE ");
				hasWhere = true;
			} else {
				query.append(" AND ");
			}
			query.append(" upper(u.email) LIKE '%"
					+ StringUtils.upperCase(email) + "%' ");
		}

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString());
	}

	@Override
	public Usuario pesquisarPorLogin(String login) throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT u FROM ");
		query.append(getEntidadePersistente().getName() + " AS u ");
		query.append(" WHERE ");
		query.append(" u.login = ? ");

		List<Usuario> lista = getTemplate().pesquisarQuery(
				getEntidadePersistente(), query.toString(),
				new Object[] { login });
		if (CollectionUtils.isNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

	@Override
	public Usuario pesquisarPorEmail(String email) throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT u FROM ");
		query.append(getEntidadePersistente().getName() + " AS u ");
		query.append(" WHERE ");
		query.append(" u.email = ? ");

		List<Usuario> lista = getTemplate().pesquisarQuery(
				getEntidadePersistente(), query.toString(),
				new Object[] { email });
		if (CollectionUtils.isNotEmpty(lista)) {
			return lista.get(0);
		}
		return null;
	}

}
