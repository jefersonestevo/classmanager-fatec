package br.com.classmanager.client.dto.def;

import br.com.classmanager.client.entidades.usuario.Usuario;

/**
 * Classe marcadora de um objeto de valor que executará alguma ação na
 * aplicação.
 * 
 * @author Jeferson Estevo
 * 
 */
public interface DTOAction extends DTO {

	public Usuario getUsuarioAtual();

	public void setUsuarioAtual(Usuario usuario);

}
