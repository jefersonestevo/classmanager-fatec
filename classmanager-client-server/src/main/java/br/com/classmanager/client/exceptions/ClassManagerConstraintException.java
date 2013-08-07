package br.com.classmanager.client.exceptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolation;

public class ClassManagerConstraintException extends ClassManagerException {

	private static final long serialVersionUID = 3818588053039794313L;

	private List<ConstraintViolation<?>> constraintViolations = new ArrayList<ConstraintViolation<?>>();

	public ClassManagerConstraintException(CodigoExcecao codigoExcecao) {
		super(codigoExcecao);
	}

	public ClassManagerConstraintException(CodigoExcecao codigo,
			ConstraintViolation<?>... constraints) {
		super(codigo);
		this.constraintViolations = Arrays.asList(constraints);
	}

	public List<ConstraintViolation<?>> getConstraintViolations() {
		return new ArrayList<ConstraintViolation<?>>(constraintViolations);
	}

	public void addConstraintViolation(ConstraintViolation<?> constraint) {
		this.constraintViolations.add(constraint);
	}

}
