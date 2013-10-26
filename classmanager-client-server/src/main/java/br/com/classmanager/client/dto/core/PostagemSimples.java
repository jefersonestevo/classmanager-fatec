package br.com.classmanager.client.dto.core;

import java.util.Date;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.entidades.core.Postagem;

public class PostagemSimples implements DTO {

	private static final long serialVersionUID = 1L;

	private String nomeUsuario;
	private Date dataPostagem;

	public PostagemSimples() {
	}

	public PostagemSimples(Postagem postagem) {
		this.dataPostagem = postagem.getDataGeracao();
		this.nomeUsuario = postagem.getUsuario().getNome();
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

}
