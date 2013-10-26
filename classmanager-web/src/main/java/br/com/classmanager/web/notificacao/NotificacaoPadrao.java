package br.com.classmanager.web.notificacao;

import java.io.Serializable;

public class NotificacaoPadrao implements Serializable {

	private static final long serialVersionUID = -3666454089289782319L;

	private Long id;
	private String titulo;
	private Integer status;

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

}
