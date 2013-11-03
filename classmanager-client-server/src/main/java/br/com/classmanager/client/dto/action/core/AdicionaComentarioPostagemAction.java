package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.DTOServicoAction;
import br.com.classmanager.client.entidades.core.Postagem;

public class AdicionaComentarioPostagemAction extends DTOServicoAction {

	private static final long serialVersionUID = -1L;

	private Postagem postagem;
	private String comentarioPostagem;

	public String getComentarioPostagem() {
		return comentarioPostagem;
	}

	public void setComentarioPostagem(String comentarioPostagem) {
		this.comentarioPostagem = comentarioPostagem;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}

}
