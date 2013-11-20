package br.com.classmanager.server.domain.modelo.dao.impl.jpa.core;

import javax.inject.Named;

import br.com.classmanager.client.entidades.core.MiniCurriculo;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.def.impl.jpa.DaoCRUDJPA;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoMiniCurriculo;

@Named
@DAO
public class DaoMiniCurriculoJPA extends DaoCRUDJPA<MiniCurriculo, Long>
		implements IDaoMiniCurriculo {

	private static final long serialVersionUID = 1L;

	@Override
	protected Class<MiniCurriculo> getEntidadePersistente() {
		return MiniCurriculo.class;
	}

}
