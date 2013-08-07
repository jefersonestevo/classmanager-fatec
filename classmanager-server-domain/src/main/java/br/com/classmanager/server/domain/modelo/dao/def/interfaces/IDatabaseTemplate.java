package br.com.classmanager.server.domain.modelo.dao.def.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.classmanager.client.entidades.auditoria.Historico;
import br.com.classmanager.client.entidades.def.IEntidade;
import br.com.classmanager.client.exceptions.ClassManagerException;

/**
 * Template para manipulação de todas as persistências das entidades em uma base
 * de dados da aplicação.
 * 
 * @param <E>
 *            - Entidade de persistência
 * @param <ID>
 *            - Classe que representa o ID da entidade de persistência.
 */
public interface IDatabaseTemplate<E extends IEntidade<ID>, ID> {

	public void inserir(E entidade) throws ClassManagerException;

	public E alterar(E entidade) throws ClassManagerException;

	public List<E> inserirAlterarLista(List<E> listaEntidades)
			throws ClassManagerException;

	public void remover(E entidade) throws ClassManagerException;

	public void removerLista(List<E> listaEntidades)
			throws ClassManagerException;

	public E pesquisar(Class<E> entidade, ID id) throws ClassManagerException;

	public List<E> pesquisarQuery(Class<E> classeEntidade, String query)
			throws ClassManagerException;

	public List<E> pesquisarQuery(Class<E> classeEntidade, String queryStr,
			Object[] params) throws ClassManagerException;

	public List<E> pesquisarLista(Class<E> entidade)
			throws ClassManagerException;

	public List<E> pesquisarLista(Class<E> entidade, List<ID> listaIds)
			throws ClassManagerException;

	public List<Historico<E, ID>> pesquisarListaHistorico(Class<E> entidade,
			ID id) throws ClassManagerException;

	public EntityManager getEntityManager();

	public void setEntityManager(EntityManager entityManager);

}
