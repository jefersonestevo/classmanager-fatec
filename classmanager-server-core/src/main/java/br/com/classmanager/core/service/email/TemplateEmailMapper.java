package br.com.classmanager.core.service.email;

public enum TemplateEmailMapper {
	ESQUECI_MINHA_SENHA("esqueci_minha_senha"), //
	CRIACAO_POST_NOVO("criacao_post_novo"), //
	;

	private String nome;

	private TemplateEmailMapper(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
