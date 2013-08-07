package br.com.classmanager.client.entidades.def;

import br.com.classmanager.client.dto.def.DTO;

@SuppressWarnings("serial")
public abstract class BeanJPA<ID> implements DTO, IEntidade<ID> {

	public BeanJPA() {
	}

	public BeanJPA(ID id) {
		this();
		setId(id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BeanJPA))
			return false;

		BeanJPA other = (BeanJPA) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
