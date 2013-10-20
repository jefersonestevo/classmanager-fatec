package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.usuario.Usuario;

public class AdicionaUsuarioGrupoAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private Grupo grupo;
	private Usuario usuario;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
