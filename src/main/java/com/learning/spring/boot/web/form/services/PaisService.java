package com.learning.spring.boot.web.form.services;

import java.util.List;

import com.learning.spring.boot.web.form.domain.Pais;

public interface PaisService {
	
	public List<Pais> listar();
	public Pais obtenerPorId(Integer id);

}
