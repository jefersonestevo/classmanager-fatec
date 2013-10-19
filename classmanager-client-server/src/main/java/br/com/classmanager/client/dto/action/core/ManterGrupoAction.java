package br.com.classmanager.client.dto.action.core;

import br.com.classmanager.client.dto.def.impl.DTOManterActionBase;
import br.com.classmanager.client.entidades.core.Grupo;
import br.com.classmanager.client.enums.AcaoManter;

public class ManterGrupoAction extends DTOManterActionBase<Grupo, Long>{

	private static final long serialVersionUID = 1L;

	private String tituloPesquisa;

	public ManterGrupoAction(AcaoManter acao) {
		super(acao);
	}

	public String getTituloPesquisa() {
		return tituloPesquisa;
	}

	public void setTituloPesquisa(String tituloPesquisa) {
		this.tituloPesquisa = tituloPesquisa;
	}

}
