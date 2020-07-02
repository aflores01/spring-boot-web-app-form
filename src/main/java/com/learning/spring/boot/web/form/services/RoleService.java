package com.learning.spring.boot.web.form.services;

import java.util.List;

import com.learning.spring.boot.web.form.domain.Role;

public interface RoleService {
	
	public List<Role> listar();
	public Role obtenerPorId(Integer id);

}
