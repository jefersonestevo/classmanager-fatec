package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import javax.inject.Named;

import br.com.classmanager.client.entidades.core.ComentarioPostagem;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoComentarioPostagem;

@Named
@DAO
public class DaoComentarioPostagemJPA extends
		DaoCRUDJPA<ComentarioPostagem, Long> implements IDaoComentarioPostagem {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<ComentarioPostagem> getEntidadePersistente() {
		return ComentarioPostagem.class;
	}

}
