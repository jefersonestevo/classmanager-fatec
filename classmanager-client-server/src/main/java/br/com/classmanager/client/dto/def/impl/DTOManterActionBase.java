package br.com.classmanager.client.dto.def.impl;

import br.com.classmanager.client.dto.def.DTOManterAction;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.enums.AcaoManter;

public abstract class DTOManterActionBase<ENT extends BeanJPA<ID>, ID> extends
		DTOManterAction {

	private static final long serialVersionUID = 2486770973876320784L;

	public DTOManterActionBase(AcaoManter acao) {
		super(acao);
	}

	protected ID id;
	protected ENT entidade;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ENT getEntidade() {
		return entidade;
	}

	public void setEntidade(ENT entidade) {
		this.entidade = entidade;
	}

}
