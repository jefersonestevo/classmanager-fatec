package br.com.classmanager.core.service.core;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.classmanager.client.dto.action.core.ConsultarPostagemGrupoAction;
import br.com.classmanager.client.dto.geral.ListaDTO;
import br.com.classmanager.client.entidades.core.Postagem;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.DAO;
import br.com.classmanager.server.domain.modelo.dao.interfaces.core.IDaoPostagem;
import br.com.classmanager.server.domain.service.Servico;

@Named("ConsultarPostagemGrupoAction")
public class ConsultarPostagemGrupoService extends
		Servico<ConsultarPostagemGrupoAction, ListaDTO<Postagem>> {

	@Inject
	@DAO
	private IDaoPostagem daoPostagem;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ListaDTO<Postagem> execute(ConsultarPostagemGrupoAction request)
			throws ClassManagerException {
		ListaDTO<Postagem> lista = new ListaDTO<Postagem>();
		lista.setLista(daoPostagem.pesquisarPorGrupo(request.getIdGrupo()));
		return lista;
	}

}
