package br.com.classmanager.client.dto.geral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.classmanager.client.dto.def.DTO;

public class ListaDTO<VALOR> implements DTO {

	private static final long serialVersionUID = 6630051865492843634L;

	private List<VALOR> lista = new ArrayList<VALOR>();

	public ListaDTO() {
		super();
	}

	public ListaDTO(List<VALOR> lista) {
		this();
		this.lista = lista;
	}

	public ListaDTO(VALOR... dtos) {
		this();
		if (dtos != null) {
			this.lista = Arrays.asList(dtos);
		}
	}

	public List<VALOR> getLista() {
		return lista;
	}

	public void setLista(List<VALOR> lista) {
		this.lista = lista;
	}

}
