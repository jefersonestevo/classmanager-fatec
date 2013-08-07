package br.com.classmanager.client.dto.def.impl;

import br.com.classmanager.client.dto.def.DTOPesquisarAction;
import br.com.classmanager.client.entidades.def.BeanJPA;
import br.com.classmanager.client.enums.AcaoPesquisar;

public abstract class DTOPesquisarActionBase<ENT extends BeanJPA<ID>, ID>
		extends DTOPesquisarAction {

	private static final long serialVersionUID = 2486770973876320784L;

	public DTOPesquisarActionBase(AcaoPesquisar acao) {
		super(acao);
	}

	protected ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

}
