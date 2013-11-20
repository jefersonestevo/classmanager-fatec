package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;

@Named
@DAO
public class DaoPostagemJPA extends DaoCRUDJPA<Postagem, Long> implements
		IDaoPostagem {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Postagem> getEntidadePersistente() {
		return Postagem.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<Long, List<Postagem>> pesquisarPostagemPorGrupo(
			List<Long> idGrupos) throws ClassManagerException {
		Map<String, Object> params = new HashMap<String, Object>();

		StringBuilder query = new StringBuilder();
		query.append(" SELECT post.grupo.id, post FROM ");
		query.append(getEntidadePersistente().getName() + " AS post ");
		query.append(" WHERE post.grupo.id IN (:listaGrupos) ");

		params.put("listaGrupos", idGrupos);

		Map<Long, List<Postagem>> mapRetorno = new HashMap<Long, List<Postagem>>();
		List<Object[]> ret = (List<Object[]>) getTemplate().getEntityManager()
				.find(getEntidadePersistente(), query.toString(), params);
		for (Object[] retornoAtual : ret) {
			Long idGrupo = (Long) retornoAtual[0];
			Postagem post = (Postagem) retornoAtual[1];

			if (mapRetorno.get(idGrupo) == null) {
				mapRetorno.put(idGrupo, new ArrayList<Postagem>());
			}
			mapRetorno.get(idGrupo).add(post);
		}
		return mapRetorno;
	}

	@Override
	public List<Postagem> pesquisarPorGrupo(Long idGrupo)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT post FROM ");
		query.append(getEntidadePersistente().getName() + " AS post ");
		query.append(" WHERE post.grupo.id = ? ");

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), new Object[] { idGrupo });
	}

	@Override
	public List<Postagem> pesquisarPostagensDosGrupos(List<Long> idGrupos)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		query.append(" SELECT post FROM ");
		query.append(getEntidadePersistente().getName() + " AS post ");
		query.append(" WHERE 1 = 2 ");
		for (Long id : idGrupos) {
			query.append(" OR post.grupo.id = ? ");
			params.add(id);
		}

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), params.toArray());
	}
}
