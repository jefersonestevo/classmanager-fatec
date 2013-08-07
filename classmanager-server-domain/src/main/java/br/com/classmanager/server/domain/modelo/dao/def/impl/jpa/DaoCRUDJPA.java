package br.com.classmanager.server.domain.modelo.dao.def.impl.jpa;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.classmanager.client.entidades.def.IEntidade;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoCRUD;

public abstract class DaoCRUDJPA<ENT extends IEntidade<ID>, ID> extends
		DaoPesquisaJPA<ENT, ID> implements IDaoCRUD<ENT, ID> {

	private static final long serialVersionUID = -7281452032701439851L;

	protected abstract Class<ENT> getEntidadePersistente();

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserir(ENT entidade) throws ClassManagerException {
		getTemplate().inserir(entidade);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public ENT alterar(ENT entidade) throws ClassManagerException {
		return getTemplate().alterar(entidade);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remover(ENT entidade) throws ClassManagerException {
		getTemplate().remover(entidade);
	}

}
