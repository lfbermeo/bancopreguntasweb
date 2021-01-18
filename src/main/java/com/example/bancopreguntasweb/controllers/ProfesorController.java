package com.example.bancopreguntasweb.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bancopreguntasweb.models.entities.Area;
import com.example.bancopreguntasweb.models.entities.BancoPreguntas;
import com.example.bancopreguntasweb.models.entities.Carrera;
import com.example.bancopreguntasweb.models.entities.Pregunta;
import com.example.bancopreguntasweb.models.entities.Usuario;
import com.example.bancopreguntasweb.models.services.implementation.AreaServiceImpl;
import com.example.bancopreguntasweb.models.services.implementation.BancoPreguntasServiceImpl;
import com.example.bancopreguntasweb.models.services.implementation.CarreraServiceImpl;
import com.example.bancopreguntasweb.models.services.implementation.PreguntaServiceImpl;
import com.example.bancopreguntasweb.models.services.implementation.UsuarioServiceImpl;

import net.bytebuddy.utility.RandomString;



@Secured("ROLE_PROFESOR")
@Controller
@RequestMapping("/profesor")
@SessionAttributes("usuario")
public class ProfesorController {
	
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AreaServiceImpl areasService;
	
	@Autowired
	private CarreraServiceImpl carrerasService;
	
	@Autowired
	private BancoPreguntasServiceImpl bancoService;
	
	@Autowired
	private PreguntaServiceImpl preguntaService;
	
	@RequestMapping("inicio_profesor")
	public String inicio_profesor() {
		String vista = "profesor/inicio_profesor";
		return vista;
	}
	
	@RequestMapping("bancopreguntas")
	public String bancopreguntas(Model model) {
		String vista = "profesor/bancopreguntas";
		List<Area> lsareas = areasService.listAll();
		List<Carrera> lscarreras = carrerasService.listAll();
		
		model.addAttribute("lsareas", lsareas);
		model.addAttribute("lscarreras", lscarreras);
		return vista;
	}
	
	@RequestMapping("visualizar")
	public String visualizar(@ModelAttribute("usuario") Usuario usuario, 
			Map<String, Object> model) {
		String vista = "profesor/visualizar";
		
		Usuario usuarioObj = (Usuario) model.get("usuario");
		Usuario obj = usuarioService.findByUsuario(usuarioObj.getUsuario());
		List<BancoPreguntas> lsbancos = bancoService.findByUsuario(obj);
		
		model.put("lsbancos", lsbancos);
		return vista;
	}
	
	
	@RequestMapping(value = "guardar_bancopreguntas", method = RequestMethod.POST)
	public String guardarBancoPreguntas(@ModelAttribute("bancopreguntas") BancoPreguntas bancopreguntas, Model model) {
	
		
		if(bancopreguntas.getIdbancopreguntas()!=null) {
			bancoService.save(bancopreguntas);
		}else {
			String codigo_aleatorio = "";
			BancoPreguntas objCodigo = null;
			do {
				codigo_aleatorio = RandomString.make(5).toUpperCase();
				objCodigo = bancoService.findByCodigo(codigo_aleatorio);
			} while (objCodigo != null);
			// AQUI ACABA LA COMPARACION DE CODIGO ALEATORIO
			bancopreguntas.setCodigo(codigo_aleatorio);
			bancoService.save(bancopreguntas);
			
		}
		

		// objeto para visualizar en la vista
		model.addAttribute("bancopreguntas", bancopreguntas);
		List<Area> lsareas = areasService.listAll();
		List<Carrera> lscarreras = carrerasService.listAll();
		model.addAttribute("lsareas", lsareas);
		model.addAttribute("lscarreras", lscarreras);
		return "profesor/agregar_preguntas";
	}
	
	@RequestMapping(value = "editar_bancopreguntas", method = RequestMethod.POST  )
	public String editar_bancopreguntas(@ModelAttribute("bancopreguntas") BancoPreguntas bancopreguntas,
			@ModelAttribute("usuario") Usuario usuario,
			Map<String, Object> model) {
		
		Usuario usuarioObj = (Usuario) model.get("usuario");
		Usuario objUsuario = usuarioService.findByUsuario(usuarioObj.getUsuario());
	
		if(bancopreguntas.getIdbancopreguntas()!=null) {
			bancopreguntas = bancoService.get(bancopreguntas.getIdbancopreguntas());
		}else {
			//bancopreguntas = bancoService.lastBanco(objUsuario.getIdusuario());
		}
		
		// objeto para visualizar en la vista
		model.put("bancopreguntas", bancopreguntas);
		List<Area> lsareas = areasService.listAll();
		List<Carrera> lscarreras = carrerasService.listAll();
		
		model.put("lsareas", lsareas);
		model.put("lscarreras", lscarreras);
		return "profesor/agregar_preguntas";
	}
	
	
	// Guardar banco de preguntas
		@RequestMapping(value = "guardar_pregunta", method = RequestMethod.POST)
		public String guardarOpcionMultiple(@ModelAttribute("pregunta") Pregunta pregunta,
				BindingResult bindingResult, Model model) {
			
			pregunta.setRescorrecta(pregunta.getRes1());
			preguntaService.save(pregunta);
				
			
			BancoPreguntas objbanco = bancoService.get(pregunta.getBancopreguntas().getIdbancopreguntas());
			// objeto para visualizar en la vista
			model.addAttribute("pregunta", pregunta);
			model.addAttribute("bancopreguntas", objbanco);
			
			
			List<Area> lsareas = areasService.listAll();
			List<Carrera> lscarreras = carrerasService.listAll();
			model.addAttribute("lsareas", lsareas);
			model.addAttribute("lscarreras", lscarreras);
			return "profesor/agregar_preguntas";
		}
	
	
	
	
	@RequestMapping(value = "eliminar_bancopreguntas", method = RequestMethod.POST)
	public String eliminar_bancopreguntas(@ModelAttribute("bancopreguntas") BancoPreguntas bancopreguntas, Model model) {
		bancoService.delete(bancopreguntas.getIdbancopreguntas());
		return "redirect:/profesor/visualizar";
	}
	
	@RequestMapping(value = "eliminar_pregunta", method = RequestMethod.POST)
	public String eliminar_pregunta(@ModelAttribute("pregunta") Pregunta pregunta,
			Model model) {
		BancoPreguntas objBanco = (preguntaService.get(pregunta.getIdpregunta())).getBancopreguntas();
		preguntaService.delete(pregunta.getIdpregunta());
		// objeto para visualizar en la vista
		model.addAttribute("bancopreguntas", objBanco);
		
		List<Area> lsareas = areasService.listAll();
		List<Carrera> lscarreras = carrerasService.listAll();
		model.addAttribute("lsareas", lsareas);
		model.addAttribute("lscarreras", lscarreras);
		return "profesor/agregar_preguntas";
		
	}
	

}
