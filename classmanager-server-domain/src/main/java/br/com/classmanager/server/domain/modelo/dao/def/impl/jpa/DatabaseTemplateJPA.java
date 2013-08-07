package br.com.classmanager.server.domain.modelo.dao.def.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import br.com.classmanager.client.entidades.auditoria.EntidadeRevisao;
import br.com.classmanager.client.entidades.auditoria.Historico;
import br.com.classmanager.client.entidades.def.IEntidade;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.server.domain.modelo.dao.def.interfaces.IDatabaseTemplate;

@Named(value = "template")
public class DatabaseTemplateJPA<E extends IEntidade<ID>, ID> implements
		IDatabaseTemplate<E, ID> {

	@PersistenceContext(unitName = "classmanager_modelo")
	private EntityManager entityManager;

	public void inserir(E entidade) throws ClassManagerException {
		try {
			getEntityManager().persist(entidade);
			getEntityManager().flush();
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	public E alterar(E entidade) throws ClassManagerException {
		try {
			entidade = getEntityManager().merge(entidade);
			getEntityManager().flush();
			return entidade;
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	public List<E> inserirAlterarLista(List<E> listaEntidades)
			throws ClassManagerException {
		try {
			List<E> listaEntidadesAtualizadas = new ArrayList<E>();
			for (E entidade : listaEntidades) {
				if (entidade.getId() == null) {
					getEntityManager().persist(entidade);
				} else {
					entidade = getEntityManager().merge(entidade);
				}
				listaEntidadesAtualizadas.add(entidade);
			}
			getEntityManager().flush();
			return listaEntidadesAtualizadas;
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	public void remover(E entidade) throws ClassManagerException {
		try {
			E entidadeRemocao = getEntityManager().merge(entidade);
			getEntityManager().remove(entidadeRemocao);
			getEntityManager().flush();
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	public void removerLista(List<E> listaEntidades)
			throws ClassManagerException {
		try {
			for (E entidade : listaEntidades) {
				E entidadeRemocao = getEntityManager().merge(entidade);
				getEntityManager().remove(entidadeRemocao);
			}
			getEntityManager().flush();
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	public E pesquisar(Class<E> entidade, ID id) throws ClassManagerException {
		try {
			return getEntityManager().find(entidade, id);
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	public List<E> pesquisarQuery(Class<E> classeEntidade, String query)
			throws ClassManagerException {
		try {
			return getEntityManager().createQuery(query, classeEntidade)
					.getResultList();
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> pesquisarQuery(Class<E> classeEntidade, String queryStr,
			Object[] params) throws ClassManagerException {
		try {
			Query query = getEntityManager().createQuery(queryStr,
					classeEntidade);
			if (params != null)
				for (int i = 0; i < params.length; i++) {
					query.setParameter((i + 1), params[i]);
				}
			return query.getResultList();
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	public List<E> pesquisarLista(Class<E> entidade)
			throws ClassManagerException {
		try {
			CriteriaQuery<E> crit = this.createCriteria(entidade);
			TypedQuery<E> q = entityManager.createQuery(crit);
			return q.getResultList();

		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	@Override
	public List<E> pesquisarLista(Class<E> entidade, List<ID> listaIds)
			throws ClassManagerException {
		try {
			CriteriaBuilder cb = entityManager.getCriteriaBuilder();
			CriteriaQuery<E> crit = cb.createQuery(entidade);
			Root<E> root = crit.from(entidade);
			crit.select(root).where(root.get("id").in(listaIds));
			TypedQuery<E> q = entityManager.createQuery(crit);
			return q.getResultList();
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	@Override
	public List<Historico<E, ID>> pesquisarListaHistorico(Class<E> entidade,
			ID id) throws ClassManagerException {
		try {
			AuditReader reader = AuditReaderFactory.get(getEntityManager());
			List<Number> versions = reader.getRevisions(entidade, id);
			List<Historico<E, ID>> lista = new ArrayList<Historico<E, ID>>();
			for (Number versao : versions) {
				EntidadeRevisao revisao = getEntityManager().find(
						EntidadeRevisao.class, versao);
				E entidadeHistorico = reader.find(entidade, id, versao);
				lista.add(new Historico<E, ID>(revisao, entidadeHistorico));
			}
			return lista;
		} catch (RuntimeException e) {
			throw TradutorExcecaoJPA.getExcecaoTraduzida(e);
		}
	}

	private CriteriaQuery<E> createCriteria(Class<E> classEntidade) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(classEntidade);
		Root<E> root = cq.from(classEntidade);
		cq.select(root);
		return cq;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
