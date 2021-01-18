package com.example.bancopreguntasweb.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.bancopreguntasweb.models.entities.Respuestas;
import com.example.bancopreguntasweb.models.services.implementation.RespuestasServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api")
public class RespuestasRest {
	
	@Autowired
	private RespuestasServiceImpl service;
	
	// Listar 
		@RequestMapping("/listarrespuestas")
		public List<Respuestas> get() {
			List<Respuestas> objEnlazado = service.listAll();
			return objEnlazado;
		}
		
	// Obtener por id
	@RequestMapping("/respuestas/{id}")
	public ResponseEntity<Respuestas> getById(@PathVariable(value = "id") Integer id) {
		Respuestas objP = service.get(id);
		if (objP == null) {
			return new ResponseEntity<Respuestas>(objP, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Respuestas>(objP, HttpStatus.OK);
	}
	
	// Guardar y actualizar
		@PostMapping("/guardarrespuestas")
		public void guardar(@RequestBody Respuestas respuesta) {
			service.save(respuesta);
		}

		// Eliminar registro 
		@GetMapping("/eliminarrespuestas/{id}")
		public void eliminar(@PathVariable Integer id) {
			Respuestas obj = service.get(id);
			if (obj != null) {
				service.delete(id);
			}
		}

}
