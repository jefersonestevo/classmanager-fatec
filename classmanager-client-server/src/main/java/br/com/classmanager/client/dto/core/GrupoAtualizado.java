package br.com.classmanager.client.dto.core;

import java.util.Date;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.entidades.core.Grupo;

public class GrupoAtualizado implements DTO {

	private static final long serialVersionUID = 3013651985722442087L;

	private Grupo grupo;
	private Date ultimaAtualizacao;

	public GrupoAtualizado() {
	}

	public GrupoAtualizado(Grupo grupo, Date ultimaAtualizacao) {
		this.grupo = grupo;
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
