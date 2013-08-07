package br.com.classmanager.server.domain.service.impl;

import br.com.classmanager.client.dto.core.ListaDTO;
import br.com.classmanager.client.dto.def.impl.DTOPesquisarActionBase;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoPesquisa;
import br.com.classmanager.server.domain.service.ServicoPesquisar;

public abstract class ServicoPesquisarBase<REQ extends DTOPesquisarActionBase<RESP, ID>, RESP extends BeanJPA<ID>, ID>
		extends ServicoPesquisar<REQ, RESP> {

	protected abstract IDaoPesquisa<RESP, ID> getDao();

	@Override
	protected RESP pesquisar(REQ request) throws ClassManagerException {
		if (request.getId() == null) {
			return null;
		}
		return getDao().pesquisar(request.getId());
	}

	@Override
	protected ListaDTO<RESP> pesquisarLista(REQ request)
			throws ClassManagerException {
		return new ListaDTO<RESP>(getDao().pesquisarLista());
	}

	@Override
	protected boolean hasPermissaoPesquisar(Usuario usuario)
			throws ClassManagerSecurityException {
		return true;
	}

	@Override
	protected boolean hasPermissaoPesquisarLista(Usuario usuario)
			throws ClassManagerSecurityException {
		return true;
	}

}
