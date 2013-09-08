package br.com.classmanager.server.domain.modelo.dao.impl.jpa;

import java.util.List;

import javax.inject.Named;

import br.com.classmanager.client.entidades.endereco.Estado;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoPesquisaJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoEstado;

@Named
@DAO
public class DaoEstadoJPA extends DaoPesquisaJPA<Estado, Long> implements
		IDaoEstado {

	private static final long serialVersionUID = 787857814289830554L;

	@Override
	protected Class<Estado> getEntidadePersistente() {
		return Estado.class;
	}

	@Override
	public List<Estado> pesquisarPorPais(Long idPais)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT e FROM ");
		query.append(Estado.class.getName() + " AS e ");
		query.append(" WHERE e.pais.id = ? ");

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), new Object[] { idPais });
	}

}
