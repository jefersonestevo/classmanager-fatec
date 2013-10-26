package br.com.classmanager.client.dto.action.core;

import java.util.List;

import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.entidades.core.Postagem;

public class AdicionaPostagemGrupoAction extends DTOServicoAction {

	private static final long serialVersionUID = -1L;

	private Grupo grupo;
	private Postagem postagem;
	private List<Long> servicosEnvio;

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Long> getServicosEnvio() {
		return servicosEnvio;
	}

	public void setServicosEnvio(List<Long> servicosEnvio) {
		this.servicosEnvio = servicosEnvio;
	}

}
