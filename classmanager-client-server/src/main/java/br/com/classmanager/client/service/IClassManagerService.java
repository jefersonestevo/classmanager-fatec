package br.com.classmanager.client.service;

import java.io.Serializable;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.dto.def.DTOManterAction;
import br.com.classmanager.client.dto.def.DTOPesquisarAction;
import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.usuario.Usuario;
import br.com.classmanager.client.enums.AcaoManter;
import br.com.classmanager.client.enums.AcaoPesquisar;
import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.ClassManagerSecurityException;

public interface IClassManagerService extends Serializable {

	/**
	 * 
	 * Executa um serviço de negócio. <br />
	 * Possui a responsabilidade de encontrar o serviço a ser executado através
	 * do DTOAction informado e converter qualquer exceção para uma
	 * ClassManagerException. <br />
	 * Irá delegar o serviço para os módulos responsáveis e validar a segurança
	 * das chamadas.
	 * 
	 * @param vo
	 *            - Action para ser executado. Este parâmetro será utilizado
	 *            para encontrar o serviço específico a ser executado.
	 * @return - VO de retorno da execução do serviço.
	 * @throws ClassManagerException
	 * 
	 */
	public DTO execute(DTOAction vo) throws ClassManagerException;

	/**
	 * 
	 * Verifica se o usuário atual possui as permissões, dentro do sistema, para
	 * executar este serviço.
	 * 
	 * @param classVO
	 *            - Classe de serviço para veriricacao
	 * @param usuario
	 *            - Classe representando o usuário
	 * @return
	 */
	public boolean hasPermissao(Class<DTOServicoAction> classVO, Usuario usuario)
			throws ClassManagerSecurityException;

	/**
	 * 
	 * Verifica se o usuário atual possui as permissões, dentro do sistema, para
	 * executar esta ação.
	 * 
	 * @param acao
	 *            - Acao de Manter a ser executada
	 * @param classVO
	 *            - Classe de serviço para veriricacao
	 * @param usuario
	 *            - Classe representando o usuário
	 * @return
	 */
	public boolean hasPermissao(AcaoManter acao,
			Class<DTOManterAction> classVO, Usuario usuario)
			throws ClassManagerSecurityException;

	/**
	 * 
	 * Verifica se o usuário atual possui as permissões, dentro do sistema, para
	 * executar esta ação.
	 * 
	 * @param acao
	 *            - Acao de Pesquisa a ser executada
	 * @param classVO
	 *            - Classe de serviço para veriricacao
	 * @param usuario
	 *            - Classe representando o usuário
	 * @return
	 */
	public boolean hasPermissao(AcaoPesquisar acao,
			Class<DTOPesquisarAction> classVO, Usuario usuario)
			throws ClassManagerSecurityException;

}
