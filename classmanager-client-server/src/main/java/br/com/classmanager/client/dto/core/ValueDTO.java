package br.com.classmanager.client.dto.core;

import br.com.classmanager.client.dto.def.DTO;

public class ValueDTO<VALOR> implements DTO {

	private static final long serialVersionUID = 693559511535671274L;

	private VALOR valor;

	public ValueDTO() {
	}

	public ValueDTO(VALOR valor) {
		this();
		this.valor = valor;
	}

	public VALOR getValor() {
		return valor;
	}

	public void setValor(VALOR valor) {
		this.valor = valor;
	}

}
