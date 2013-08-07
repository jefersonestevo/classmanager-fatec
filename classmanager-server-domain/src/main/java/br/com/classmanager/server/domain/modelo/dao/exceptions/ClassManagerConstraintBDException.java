package br.com.classmanager.server.domain.modelo.dao.exceptions;

import javax.ejb.ApplicationException;

import br.com.classmanager.client.exceptions.ClassManagerConstraintException;
import br.com.classmanager.client.exceptions.CodigoExcecao;

@ApplicationException
public class ClassManagerConstraintBDException extends ClassManagerBDException {

	private static final long serialVersionUID = -538986674790049864L;
	private ClassManagerConstraintException constraintException;

	public ClassManagerConstraintBDException(CodigoExcecao codigoExcecao,
			ClassManagerConstraintException constraintException) {
		super(codigoExcecao);
		this.constraintException = constraintException;
	}

	public ClassManagerConstraintException getConstraintException() {
		return constraintException;
	}

}
