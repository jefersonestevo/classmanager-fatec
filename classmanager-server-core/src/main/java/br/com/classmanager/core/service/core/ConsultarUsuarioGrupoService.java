package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ConsultarUsuarioGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.UsuarioGrupo;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoUsuarioGrupo;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConsultarUsuarioGrupoAction")
public class ConsultarUsuarioGrupoService extends
		Servico<ConsultarUsuarioGrupoAction, ListaDTO<UsuarioGrupo>> {

	@Inject
	@DAO
	private IDaoUsuarioGrupo daoUsuarioGrupo;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ListaDTO<UsuarioGrupo> execute(ConsultarUsuarioGrupoAction request)
			throws ClassManagerException {
		ListaDTO<UsuarioGrupo> lista = new ListaDTO<UsuarioGrupo>();
		lista.setLista(daoUsuarioGrupo.pesquisarPorUsuario(request.getIdUsuario()));
		return lista;
	}

}
