package br.com.classmanager.server.domain.modelo.dao.impl.jpa;

import javax.inject.Named;

import br.com.classmanager.client.entidades.endereco.Pais;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoPesquisaJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoPais;

@Named
@DAO
public class DaoPaisJPA extends DaoPesquisaJPA<Pais, Long> implements IDaoPais {

	private static final long serialVersionUID = -6031438033472151420L;

	@Override
	protected Class<Pais> getEntidadePersistente() {
		return Pais.class;
	}

}
