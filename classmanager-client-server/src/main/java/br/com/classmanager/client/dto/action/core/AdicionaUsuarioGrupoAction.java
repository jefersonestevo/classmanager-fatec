package br.com.classmanager.client.dto.action.core;

import java.util.List;

import br.com.classmanager.client.dto.core.UsuarioGrupoStatus;
import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.core.Grupo;

public class AdicionaUsuarioGrupoAction extends DTOServicoAction {

	private static final long serialVersionUID = 1L;

	private Grupo grupo;
	private List<UsuarioGrupoStatus> listaUsuarioInsercao;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<UsuarioGrupoStatus> getListaUsuarioInsercao() {
		return listaUsuarioInsercao;
	}

	public void setListaUsuarioInsercao(
			List<UsuarioGrupoStatus> listaUsuarioInsercao) {
		this.listaUsuarioInsercao = listaUsuarioInsercao;
	}

}
