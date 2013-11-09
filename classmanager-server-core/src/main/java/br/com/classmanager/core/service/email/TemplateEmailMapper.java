package br.com.classmanager.core.service.email;

public enum TemplateEmailMapper {
	ESQUECI_MINHA_SENHA("esqueci_minha_senha"), //
	;

	private String nome;

	private TemplateEmailMapper(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
