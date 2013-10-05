package br.com.classmanager.client.entidades.enums;

public enum PerfilUsuario {
	ADM("ADM"), //
	MEMBRO("MEMBRO"), //
	;

	private String nome;

	private PerfilUsuario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}
