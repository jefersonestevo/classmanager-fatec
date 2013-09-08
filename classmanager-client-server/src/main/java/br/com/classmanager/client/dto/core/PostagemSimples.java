package br.com.classmanager.client.dto.core;

import java.util.Date;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.entidades.core.Postagem;

public class PostagemSimples implements DTO {

	private static final long serialVersionUID = 1L;

	private String titulo;
	private String nomeUsuario;
	private Date dataPostagem;

	public PostagemSimples() {
	}

	public PostagemSimples(Postagem postagem) {
		this.titulo = postagem.getTitulo();
		this.dataPostagem = postagem.getDataGeracao();
		this.nomeUsuario = postagem.getUsuarioGrupo().getUsuario().getNome();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
