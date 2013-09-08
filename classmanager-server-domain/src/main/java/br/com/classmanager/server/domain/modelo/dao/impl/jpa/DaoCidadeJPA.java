package br.com.classmanager.server.domain.modelo.dao.impl.jpa;

import java.util.List;

import javax.inject.Named;

import br.com.classmanager.client.entidades.endereco.Cidade;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoPesquisaJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoCidade;

@Named
@DAO
public class DaoCidadeJPA extends DaoPesquisaJPA<Cidade, Long> implements
		IDaoCidade {

	private static final long serialVersionUID = 4869015637965832501L;

	@Override
	protected Class<Cidade> getEntidadePersistente() {
		return Cidade.class;
	}

	@Override
	public List<Cidade> pesquisarPorEstado(Long idEstado)
			throws ClassManagerException {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT c FROM ");
		query.append(Cidade.class.getName() + " AS c ");
		query.append(" WHERE c.estado.id = ? ");

		return getTemplate().pesquisarQuery(getEntidadePersistente(),
				query.toString(), new Object[] { idEstado });
	}

}
