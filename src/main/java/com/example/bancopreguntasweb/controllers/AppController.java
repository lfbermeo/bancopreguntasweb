package com.example.bancopreguntasweb.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bancopreguntasweb.models.entities.Usuario;


@Controller
@SessionAttributes("usuario")
public class AppController {

	@RequestMapping("/")
	public String inicioSistema(Model model, @ModelAttribute("usuario") Usuario usuario) {
		String vista ="login";
		System.out.println(usuario.getRol());
		if(usuario.getIdusuario()!=null) {
			if(usuario.getRol().equals("PROFESOR")) {
				vista="profesor/inicio_profesor";
			}else if (usuario.getRol().equals("ADMINISTRADOR")) {
				vista="administrador/inicio_admin";
				
			}
			
		}
		
		
		return vista;
	}

	
	
	

	

	
	
	

	
	
	


}
