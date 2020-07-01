package com.learning.spring.boot.web.form.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//StringUtils helper de spring para validar
		if(value == null || !StringUtils.hasText(value)) {
			return false;
		}
		return true;
	}

}
