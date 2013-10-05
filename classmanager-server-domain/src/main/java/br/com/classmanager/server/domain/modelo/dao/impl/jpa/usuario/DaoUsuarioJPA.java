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
		StringBuilder query = new StringBuilder();
		query.append(" SELECT u FROM ");
		query.append(getEntidadePersistente().getName() + " AS u ");
		query.append(" WHERE ");
		query.append(" 1 = 1 ");

		if (StringUtils.isNotEmpty(nome)) {
			query.append(" AND ");
			query.append(" u.nome LIKE '%" + nome + "%' ");
		}

		if (StringUtils.isNotEmpty(login)) {
			query.append(" AND ");
			query.append(" u.login LIKE '%" + login + "%' ");
		}

		if (StringUtils.isNotEmpty(email)) {
			query.append(" AND ");
			query.append(" u.email LIKE '%" + email + "%' ");
		}

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString());
	}

	@Override
	public Usuario pesquisarPorLogin(String login)
			throws ClassManagerException {
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

}
