package br.com.classmanager.client.exceptions;

public class ClassManagerException extends Exception {

	private static final long serialVersionUID = -6779179963776293498L;

	protected static final String QUEBRA_LINHA = "\n";
	protected static final String SEPARADOR_ERROS = "-----------------------------";

	private CodigoExcecao codigoExcecao = CodigoExcecao.ERRO_DESCONHECIDO;
	private String message;

	public ClassManagerException(CodigoExcecao codigoExcecao) {
		this.codigoExcecao = codigoExcecao;
	}

	public ClassManagerException(CodigoExcecao codigo, Exception e) {
		super(e);
		this.codigoExcecao = codigo;
	}

	protected ClassManagerException(CodigoExcecao codigo, Exception e,
			String message) {
		super(e);
		this.codigoExcecao = codigo;
		this.message = message;
	}

	public CodigoExcecao getCodigoExcecao() {
		return codigoExcecao;
	}

	public String getMessage() {
		return message;
	}

}
