package br.com.classmanager.core.service.core;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ConsultaGrupoPorUsuarioAction;
import br.com.classmanager.client.dto.core.GrupoSimples;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoGrupo;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConsultaGrupoPorUsuarioAction")
public class ConsultaGrupoPorUsuarioService extends
		Servico<ConsultaGrupoPorUsuarioAction, ListaDTO<GrupoSimples>> {

	@Inject
	private IDaoPostagem daoPostagem;

	@Inject
	private IDaoGrupo daoGrupo;

	@Override
	public ListaDTO<GrupoSimples> execute(ConsultaGrupoPorUsuarioAction request)
			throws ClassManagerException {
		// TODO Auto-generated method stub
		return null;
	}

}
