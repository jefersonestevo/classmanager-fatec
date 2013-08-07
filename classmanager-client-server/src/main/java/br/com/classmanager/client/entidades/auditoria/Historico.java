package br.com.classmanager.client.entidades.auditoria;

import br.com.classmanager.client.dto.def.DTO;
import br.com.classmanager.client.entidades.def.IEntidade;

public class Historico<E extends IEntidade<ID>, ID> implements DTO {

	private static final long serialVersionUID = -7417028312067659866L;

	private EntidadeRevisao revisao;
	private E entidade;

	public Historico(EntidadeRevisao revisao, E entidade) {
		super();
		this.revisao = revisao;
		this.entidade = entidade;
	}

	public EntidadeRevisao getRevisao() {
		return revisao;
	}

	public void setRevisao(EntidadeRevisao revisao) {
		this.revisao = revisao;
	}

	public E getEntidade() {
		return entidade;
	}

	public void setEntidade(E entidade) {
		this.entidade = entidade;
	}

}
