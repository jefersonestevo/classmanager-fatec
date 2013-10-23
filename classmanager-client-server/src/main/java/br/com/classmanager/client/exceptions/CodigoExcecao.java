package br.com.classmanager.client.exceptions;

public enum CodigoExcecao {

	// Erros 400005000+ p/ Grupo
	TENTATIVA_EXCLUSAO_USUARIO_CRIADOR_GRUPO(400005000), //

	// Erros 450000000+ p/ Erros de Servicos
	ERRO_DESCONHECIDO_ENVIO_EMAIL(450000001), //
	ERRO_ENVIO_EMAIL(450000002), //
	ESQUECI_MINHA_SENHA_USUARIO_NAO_ENCONTRADO(450000003), //

	// Alterar Senha
	ALTERAR_SENHA_SENHAS_NOVAS_DIFERENTES(450010001), //
	ALTERAR_SENHA_SENHA_INCORRETA(450010002), //

	// Erros 500000000+ p/ View
	PARAMETRO_OBRIGATORIO_NULO(500000001), //

	// Erros 600000000+ p/ Acesso ao BD
	BD_ENTIDADE_NAO_ENCONTRADA(600000001), //
	BD_RESULTADO_NAO_ENCONTRADO(600000002), //
	BD_INCONSISTENCIA_UNIQUE(600000003), //
	BD_TIMEOUT(600000004), //
	BD_ENTIDADE_EXISTE(600000005), //
	BD_ERRO_ACESSO_BASE(600000006), //
	BD_ERRO_CONSTRAINT(600001001), //

	// Erros 800000000+ p/ erros de segurança
	ACESSO_RESTRITO(800000001), //

	// Erros 900000000+ p/ erros inesperados
	ERRO_DESCONHECIDO(900000001) //
	;

	private Integer codigo;

	private CodigoExcecao(int codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String toString() {
		return getCodigo() + " > " + name();
	}
}
