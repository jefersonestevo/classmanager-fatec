package br.com.classmanager.client.componentes.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyConstraintValidator implements
		ConstraintValidator<NotEmpty, String> {

	@Override
	public void initialize(NotEmpty arg0) {
	}

	@Override
	public boolean isValid(String str, ConstraintValidatorContext arg1) {
		return str != null && !"".equals(str);
	}

}
