package br.com.classmanager.server.domain.modelo.dao.def.impl.jpa;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.com.classmanager.client.exceptions.ClassManagerConstraintException;
import br.com.classmanager.client.exceptions.CodigoExcecao;
import br.com.classmanager.server.domain.modelo.dao.exceptions.ClassManagerBDException;
import br.com.classmanager.server.domain.modelo.dao.exceptions.ClassManagerConstraintBDException;

public class TradutorExcecaoJPA {

	public static ClassManagerBDException getExcecaoTraduzida(RuntimeException r) {

		CodigoExcecao codigo = CodigoExcecao.ERRO_DESCONHECIDO;

		if (r instanceof EntityNotFoundException) {
			codigo = CodigoExcecao.BD_ENTIDADE_NAO_ENCONTRADA;
		} else if (r instanceof NoResultException) {
			codigo = CodigoExcecao.BD_RESULTADO_NAO_ENCONTRADO;
		} else if (r instanceof NonUniqueResultException) {
			codigo = CodigoExcecao.BD_INCONSISTENCIA_UNIQUE;
		} else if (r instanceof QueryTimeoutException) {
			codigo = CodigoExcecao.BD_TIMEOUT;
		} else if (r instanceof EntityExistsException) {
			codigo = CodigoExcecao.BD_ENTIDADE_EXISTE;
		} else if (r instanceof PersistenceException) {
			codigo = CodigoExcecao.BD_ERRO_ACESSO_BASE;
		} else if (r instanceof ConstraintViolationException) {
			ConstraintViolationException e = (ConstraintViolationException) r;
			ClassManagerConstraintException exception = new ClassManagerConstraintException(
					CodigoExcecao.BD_ERRO_CONSTRAINT);
			for (ConstraintViolation<?> constraint : e
					.getConstraintViolations()) {
				exception.addConstraintViolation(constraint);
			}
			return new ClassManagerConstraintBDException(
					CodigoExcecao.BD_ERRO_CONSTRAINT, exception);
		}
		return new ClassManagerBDException(codigo, r);
	}
}
