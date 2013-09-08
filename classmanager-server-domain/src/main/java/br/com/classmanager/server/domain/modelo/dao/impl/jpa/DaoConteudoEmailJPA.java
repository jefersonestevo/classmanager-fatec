package br.com.classmanager.server.domain.modelo.dao.impl.jpa;

import javax.inject.Named;

import br.com.classmanager.client.entidades.geral.ConteudoEmail;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoConteudoEmail;

@Named
@DAO
public class DaoConteudoEmailJPA extends DaoCRUDJPA<ConteudoEmail, Long>
		implements IDaoConteudoEmail {

	private static final long serialVersionUID = 2894890277019995749L;

	@Override
	protected Class<ConteudoEmail> getEntidadePersistente() {
		return ConteudoEmail.class;
	}

}
