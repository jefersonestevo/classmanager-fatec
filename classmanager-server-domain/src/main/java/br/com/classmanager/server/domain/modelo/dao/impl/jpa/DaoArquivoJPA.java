package br.com.classmanager.server.domain.modelo.dao.impl.jpa;

import javax.inject.Named;

import br.com.classmanager.client.entidades.geral.Arquivo;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoArquivo;

@Named
@DAO
public class DaoArquivoJPA extends DaoCRUDJPA<Arquivo, Long> implements
		IDaoArquivo {

	private static final long serialVersionUID = -8442559311093421099L;

	@Override
	protected Class<Arquivo> getEntidadePersistente() {
		return Arquivo.class;
	}

}
