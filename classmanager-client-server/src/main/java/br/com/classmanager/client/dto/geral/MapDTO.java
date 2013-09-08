package br.com.classmanager.client.dto.geral;

import java.util.HashMap;
import java.util.Map;

import br.com.classmanager.client.dto.def.DTO;

public class MapDTO<CHAVE, VALOR> implements DTO {

	private static final long serialVersionUID = 6595489439846226411L;

	private Map<CHAVE, VALOR> map = new HashMap<CHAVE, VALOR>();

	public MapDTO() {
		super();
	}

	public MapDTO(Map<CHAVE, VALOR> map) {
		this();
		this.map = map;
	}

	public Map<CHAVE, VALOR> getMap() {
		return map;
	}

	public void setMap(Map<CHAVE, VALOR> map) {
		this.map = map;
	}

}
