package br.com.classmanager.client;

import br.com.classmanager.client.eventos.IEvento;

/**
 * Hello world!
 * 
 */
public class EventoTeste implements IEvento {

	private static final long serialVersionUID = 4422488318504870900L;

	private String texto;

	public EventoTeste(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
}
