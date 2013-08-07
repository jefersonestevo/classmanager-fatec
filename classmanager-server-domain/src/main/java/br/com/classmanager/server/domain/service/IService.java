package br.com.classmanager.server.domain.service;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.dto.def.DTOAction;
import br.com.classmanager.client.exceptions.ClassManagerException;

/**
 * Representa um serviço do ClassManager a ser executado. Todos os serviços
 * serão chamados pela camada de fachada através desta interface. <br />
 * É necessário que o serviço seja anotado com a annotation Named e o nome seja
 * o mesmo nome do objeto REQ. <br />
 * 
 * Ex:<br />
 * <code>
 * 
 * @Named("ServicoActionEnvio") <br /> public class
 *                              ServicoTeste&lt;ServicoActionEnvio,
 *                              ServicoActionRecepcao&gt;{...}<br /> </code> <br />
 */
public interface IService<REQ extends DTOAction, RESP extends DTO> {

	/**
	 * Método que será executado pela camada de fachada quando for chamado um
	 * serviço identificado com o nome desta classe de serviço.
	 * 
	 * @param request
	 * @return
	 * @throws SisraException
	 */
	public RESP execute(REQ request) throws ClassManagerException;

}
