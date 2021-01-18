package com.example.bancopreguntasweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("usuario")
public class AppController {

	@RequestMapping("/")
	public String inicioSistema(Model model) {
		//model.addAttribute("titulo", "Gamificación juego 3D");
		return "login";
	}

	
	
	

	

	
	
	

	
	
	


}
