package com.learning.spring.boot.web.form.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.learning.spring.boot.web.form.domain.Usuario;
import com.learning.spring.boot.web.form.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	public UsuarioValidador validador;
	
	//Se poblan los datos y se validan desde el inicio
	//Evento de ciclo de vida del controlador
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		usuario.setId("00.000.000-K");
		model.addAttribute("titulo", "Crear Usuario");
		model.addAttribute("usuario", usuario);
		return ("form");
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		
		//validando desde init binder
		//validador.validate(usuario, result);

		model.addAttribute("titulo", "Resultado de formulario");

		if (result.hasErrors()) {
			//Forma manual de control de errores
			//
			/*Map<String,String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errores.put(err.getField(),"El campo".concat(err.getField().concat(" ").concat(err.getDefaultMessage())));
			});
			model.addAttribute("error", errores);*/
			return ("form");
		}

		model.addAttribute("usuario", usuario);
		status.setComplete();

		return "resultado";
	}

}
