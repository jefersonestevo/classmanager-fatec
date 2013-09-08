package br.com.classmanager.client.dto.core;

import java.util.ArrayList;
import java.util.List;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.entidades.core.Grupo;

public class GrupoSimples implements DTO {

	private static final long serialVersionUID = 1L;

	private String titulo;
	private List<PostagemSimples> listaPostagem = new ArrayList<PostagemSimples>();

	public GrupoSimples() {
	}

	public GrupoSimples(Grupo grupo) {
		this.titulo = grupo.getTitulo();
		this.listaPostagem = new ArrayList<PostagemSimples>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<PostagemSimples> getListaPostagem() {
		return listaPostagem;
	}

	public void setListaPostagem(List<PostagemSimples> listaPostagem) {
		this.listaPostagem = listaPostagem;
	}

}
