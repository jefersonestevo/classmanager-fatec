package br.com.classmanager.server.domain.modelo.dao.exceptions;

import javax.ejb.ApplicationException;

import br.com.classmanager.client.exceptions.ClassManagerException;
import br.com.classmanager.client.exceptions.CodigoExcecao;

@ApplicationException
public class ClassManagerBDException extends ClassManagerException {

	private static final long serialVersionUID = -538986674790049864L;

	public ClassManagerBDException(CodigoExcecao codigoExcecao) {
		super(codigoExcecao);
	}

	public ClassManagerBDException(CodigoExcecao codigo, Exception e) {
		super(codigo, e);
	}

}
