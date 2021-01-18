package com.example.bancopreguntasweb.models.services.interfaces;

import java.util.List;

import com.example.bancopreguntasweb.models.entities.Carrera;

public interface ICarreraService {

	
	public List<Carrera> listAll();

	public void save(Carrera area);

	public Carrera get(Integer id);

	public void delete(Integer id);
}
