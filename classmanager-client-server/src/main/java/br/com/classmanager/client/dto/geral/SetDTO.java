package br.com.classmanager.client.dto.geral;

import java.util.HashSet;
import java.util.Set;

import br.com.classmanager.client.dto.def.DTO;

public class SetDTO<VALOR> implements DTO {

	private static final long serialVersionUID = 6630051865492843634L;

	private Set<VALOR> set = new HashSet<VALOR>();

	public SetDTO() {
		super();
	}

	public SetDTO(Set<VALOR> set) {
		this();
		this.set = set;
	}

	public Set<VALOR> getSet() {
		return set;
	}

	public void setSet(Set<VALOR> set) {
		this.set = set;
	}

}
