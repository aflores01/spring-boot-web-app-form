package com.learning.spring.boot.web.form.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.learning.spring.boot.web.form.domain.Usuario;

@Component
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//Usuario usuario = (Usuario)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellido", "NotEmpty.usuario.apellido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "genero", "NotEmpty.usuario.genero");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pais", "NotEmpty.usuario.pais");
		
		/*if (usuario.getId().matches("[\\d]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}") == false) {
			errors.rejectValue("id", "Pattern.usuario.id");
		}*/

	}

}
