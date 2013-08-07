package br.com.classmanager.client.exceptions;

public class ClassManagerSecurityException extends ClassManagerException {

	private static final long serialVersionUID = -5781311365540489260L;

	public ClassManagerSecurityException(CodigoExcecao codigoExcecao) {
		super(codigoExcecao);
	}

	public ClassManagerSecurityException(CodigoExcecao codigoExcecao,
			Exception e) {
		super(codigoExcecao, e);
	}

}
