package com.learning.spring.boot.web.form.domain;

import java.util.Date;
import java.util.List;

//import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

//import org.springframework.format.annotation.DateTimeFormat;

import com.learning.spring.boot.web.form.validation.IdentificadorRegex;
import com.learning.spring.boot.web.form.validation.Requerido;

public class Usuario {

	// @Pattern(regexp="[\\d]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")
	@IdentificadorRegex
	private String id;
	@NotBlank
	@Size(min = 3, max = 8)
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	@Email
	private String email;
	// @NotEmpty
	private String nombre;
	// @NotBlank
	@Requerido
	private String apellido;

	@NotNull
	@Min(5)
	@Max(5000)
	private Integer cuenta;

	@NotNull
	@Past
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	
	@NotNull
	private Pais pais;
	
	@NotEmpty
	private List<Role> roles;
	

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Integer getCuenta() {
		return cuenta;
	}

	public void setCuenta(Integer cuenta) {
		this.cuenta = cuenta;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
