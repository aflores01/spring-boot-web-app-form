package com.learning.spring.boot.web.form.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.learning.spring.boot.web.form.domain.Pais;
import com.learning.spring.boot.web.form.domain.Role;
import com.learning.spring.boot.web.form.domain.Usuario;
import com.learning.spring.boot.web.form.editors.NombreMayusculaEditor;
import com.learning.spring.boot.web.form.editors.PaisPropertyEditors;
import com.learning.spring.boot.web.form.editors.RolesEditor;
import com.learning.spring.boot.web.form.services.PaisService;
import com.learning.spring.boot.web.form.services.RoleServiceImpl;
import com.learning.spring.boot.web.form.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	public UsuarioValidador validador;

	@Autowired
	private PaisService paisService;

	@Autowired
	private PaisPropertyEditors paisEditor;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@Autowired
	private RolesEditor rolesEditor;
	
	// Se poblan los datos y se validan desde el inicio
	// Evento de ciclo de vida del controlador
	// Se registran todos los PropertyEditor para inicializarlos
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Forzar el formato estricto de fecha
		dateFormat.setLenient(false);
		//
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, false));
		// Si no se asigna en el segundo parametro el atributo, se toman todos los
		// campos.
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());

		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", rolesEditor);
	}
	
	@ModelAttribute("genero")
	public List<String> genero(){
		return Arrays.asList("Hombre","Mujer");
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles(){
		return this.roleService.listar();
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}

	// Forma básica
	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "Mexico", "Childe", "Peru", "Colombia");
	}

	// Mediante mapa
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("MX", "México");
		paises.put("CL", "Chile");
		paises.put("AR", "Argentina");
		paises.put("PE", "Perú");
		paises.put("CO", "Colombia");
		paises.put("VE", "Venezuela");
		return paises;
	}
	
	@ModelAttribute("rolesMap")
	public Map<String, String> rolesMap() {
		Map<String, String> role = new HashMap<String, String>();
		role.put("ROLE_ADMIN", "Administrador");
		role.put("ROLE_USER", "Usuario");
		role.put("ROLE_MODERATOR", "Moderador");
		return role;
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		usuario.setId("00.000.000-K");
		usuario.setHabilitar(true);
		usuario.setSecretVal("Secret Value");
		usuario.setPais(new Pais(2, "MX", "Mexico"));
		usuario.setRoles(Arrays.asList(new Role(2,"Moderador","ROLE_MODERATOR"),new Role(3,"Usuario","ROLE_USER")));
		
		model.addAttribute("titulo", "Crear Usuario");
		model.addAttribute("usuario", usuario);
		
		
		return ("form");
	}

	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {

		// validando desde init binder
		// validador.validate(usuario, result);

		

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Resultado de formulario");
			// Forma manual de control de errores
			//
			/*
			 * Map<String,String> errores = new HashMap<>();
			 * result.getFieldErrors().forEach(err -> {
			 * errores.put(err.getField(),"El campo".concat(err.getField().concat(" ").
			 * concat(err.getDefaultMessage()))); }); model.addAttribute("error", errores);
			 */
			return ("form");
		}

		return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required=false) Usuario usuario, Model model, SessionStatus status) {
		if(usuario == null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado de formulario");
		status.setComplete();
		return "resultado";
	}
	

}
