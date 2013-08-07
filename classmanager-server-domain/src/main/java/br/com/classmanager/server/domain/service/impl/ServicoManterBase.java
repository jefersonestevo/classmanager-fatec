package br.com.classmanager.server.domain.service.impl;

import br.com.classmanager.client.dto.core.ListaDTO;
import br.com.classmanager.client.dto.def.impl.DTOManterActionBase;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoCRUD;
import br.com.classmanager.server.domain.service.ServicoManter;

public abstract class ServicoManterBase<REQ extends DTOManterActionBase<RESP, ID>, RESP extends BeanJPA<ID>, ID>
		extends ServicoManter<REQ, RESP> {

	protected abstract IDaoCRUD<RESP, ID> getDao();

	@Override
	protected RESP inserir(REQ request) throws ClassManagerException {
		getDao().inserir(request.getEntidade());
		return request.getEntidade();
	}

	@Override
	protected RESP alterar(REQ request) throws ClassManagerException {
		return getDao().alterar(request.getEntidade());
	}

	@Override
	protected void remover(REQ request) throws ClassManagerException {
		getDao().remover(request.getEntidade());
	}

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
	protected boolean hasPermissaoInserir(Usuario usuario)
			throws ClassManagerSecurityException {
		return true;
	}

	@Override
	protected boolean hasPermissaoAlterar(Usuario usuario)
			throws ClassManagerSecurityException {
		return true;
	}

	@Override
	protected boolean hasPermissaoRemover(Usuario usuario)
			throws ClassManagerSecurityException {
		return true;
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
