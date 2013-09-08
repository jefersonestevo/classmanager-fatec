package br.com.classmanager.server.domain.modelo.dao.impl.jpa;

import javax.inject.Named;

import br.com.classmanager.client.entidades.geral.Parametro;
import br.com.classmanager.client.enums.EnumParametro;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.IDaoParametro;

@Named
@DAO
public class DaoParametroJPA extends DaoCRUDJPA<Parametro, EnumParametro>
		implements IDaoParametro {

	private static final long serialVersionUID = -5966462916705744403L;

	@Override
	protected Class<Parametro> getEntidadePersistente() {
		return Parametro.class;
	}

}
