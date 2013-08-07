package br.com.classmanager.server.domain.modelo.dao.def.interfaces;

import java.util.List;

import br.com.classmanager.client.entidades.def.IEntidade;
import br.com.classmanager.client.exceptions.ClassManagerException;

/**
 * Interface para Daos que representam somente a Consulta de uma Entidade
 * específica da aplicação.
 * 
 * @param <E>
 *            - Entidade para ser gerado os métodos de pesquisa.
 * @param <ID>
 *            - Classe que representa o ID da entidade para geração do DAO.
 */
public interface IDaoPesquisa<E extends IEntidade<ID>, ID> extends IDao {

	/**
	 * 
	 * Realiza uma pesquisa na base de dados com o ID fornecido e retorna a
	 * entidade encontrada. <br />
	 * Retorna null caso a entidade não seja encontrada.
	 * 
	 * @param id
	 * @return
	 * @throws ClassManagerException
	 */
	public E pesquisar(ID id) throws ClassManagerException;

	/**
	 * 
	 * Retorna todas as entidades existentes na base de dados.
	 * 
	 * @return
	 * @throws ClassManagerException
	 */
	public List<E> pesquisarLista() throws ClassManagerException;

}
