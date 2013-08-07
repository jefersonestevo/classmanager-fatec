package br.com.classmanager.server.domain.modelo.dao.def.interfaces;

import br.com.classmanager.client.entidades.def.IEntidade;
import br.com.classmanager.client.exceptions.ClassManagerException;

/**
 * Interface para Daos que representam CRUD's para uma entidade específica. Irá
 * possuir os métodos básicos de um CRUD da entidade.
 * 
 * @param <E>
 *            - Entidade para ser gerado o CRUD.
 * @param <ID>
 *            - Classe que representa o ID da entidade para geração do CRUD.
 */
public interface IDaoCRUD<E extends IEntidade<ID>, ID> extends
		IDaoPesquisa<E, ID>, IDao {

	/**
	 * Insere a entidade passada como parâmetro na base de dados.
	 * 
	 * @param entidade
	 * @throws ClassManagerException
	 */
	public void inserir(E entidade) throws ClassManagerException;

	/**
	 * 
	 * Altera a entidade passada no parâmetro na base de dados e retorna a
	 * entidade alterada
	 * 
	 * @param entidade
	 * @return
	 * @throws ClassManagerException
	 */
	public E alterar(E entidade) throws ClassManagerException;

	/**
	 * 
	 * Remove a entidade passada no parâmetro da base de dados
	 * 
	 * @param entidade
	 * @throws ClassManagerException
	 */
	public void remover(E entidade) throws ClassManagerException;

}
