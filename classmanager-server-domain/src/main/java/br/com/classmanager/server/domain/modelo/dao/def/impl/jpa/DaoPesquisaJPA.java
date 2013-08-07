package br.com.classmanager.server.domain.modelo.dao.def.impl.jpa;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.classmanager.client.entidades.def.IEntidade;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDaoPesquisa;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDatabaseTemplate;

public abstract class DaoPesquisaJPA<ENT extends IEntidade<ID>, ID> implements
		IDaoPesquisa<ENT, ID> {

	private static final long serialVersionUID = 226567064674655943L;

	@Inject
	private IDatabaseTemplate<ENT, ID> template;

	protected abstract Class<ENT> getEntidadePersistente();

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public ENT pesquisar(ID id) throws ClassManagerException {
		return getTemplate().pesquisar(getEntidadePersistente(), id);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<ENT> pesquisarLista() throws ClassManagerException {
		return getTemplate().pesquisarLista(getEntidadePersistente());
	}

	protected CriteriaBuilder getCriteriaBuilder() {
		return getTemplate().getEntityManager().getCriteriaBuilder();
	}

	protected TypedQuery<ENT> createQuery(CriteriaQuery<ENT> cq) {
		return getTemplate().getEntityManager().createQuery(cq);
	}

	protected IDatabaseTemplate<ENT, ID> getTemplate() {
		return template;
	}

	protected void setTemplate(IDatabaseTemplate<ENT, ID> template) {
		this.template = template;
	}

}
