package br.com.classmanager.client.componentes.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidoConstraintValidator implements
		ConstraintValidator<EmailValido, String> {

	private static final Pattern pattern = Pattern
			.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
					+ "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
					+ "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");

	@Override
	public void initialize(EmailValido arg0) {
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		Matcher matcher = pattern.matcher(email);
		return matcher.find();
	}

}
