package br.com.classmanager.web.notificacao;

import java.io.Serializable;
import java.util.Date;

public class NotificacaoPadrao implements Serializable {

	private static final long serialVersionUID = -3666454089289782319L;

	private Long id;
	private String titulo;
	private Integer status;
	private Date ultimaAtualizacao;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUltimaAtualizacao() {
		return ultimaAtualizacao;
	}

	public void setUltimaAtualizacao(Date ultimaAtualizacao) {
		this.ultimaAtualizacao = ultimaAtualizacao;
	}

}
