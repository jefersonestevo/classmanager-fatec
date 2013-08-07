package br.com.classmanager.server.domain.modelo.dao.impl.jpa.endereco;

import javax.inject.Named;

import br.com.classmanager.client.entidades.endereco.Endereco;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.endereco.IDaoEndereco;

@Named
@DAO
public class DaoEnderecoJPA extends DaoCRUDJPA<Endereco, Long> implements
		IDaoEndereco {

	private static final long serialVersionUID = -7405351302904821101L;

	@Override
	protected Class<Endereco> getEntidadePersistente() {
		return Endereco.class;
	}

}
