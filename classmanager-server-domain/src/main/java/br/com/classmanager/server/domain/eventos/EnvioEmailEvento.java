package br.com.classmanager.server.domain.eventos;

import java.util.List;

import br.com.classmanager.client.eventos.IEvento;

public class EnvioEmailEvento implements IEvento {

	private static final long serialVersionUID = 7086687112101470442L;

	private Long idEmail;
	private List<Long> idUsuarios;

	public EnvioEmailEvento() {
	}

	public EnvioEmailEvento(Long idEmail, List<Long> idUsuarios) {
		this();
		this.idEmail = idEmail;
		this.idUsuarios = idUsuarios;
	}

	public Long getIdEmail() {
		return idEmail;
	}

	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	public List<Long> getIdUsuarios() {
		return idUsuarios;
	}

	public void setIdUsuarios(List<Long> idUsuarios) {
		this.idUsuarios = idUsuarios;
	}

}
