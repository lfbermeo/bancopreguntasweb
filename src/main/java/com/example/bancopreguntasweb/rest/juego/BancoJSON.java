package com.example.bancopreguntasweb.rest.juego;

import java.util.List;

public class BancoJSON {
	
	private String codigo;
	private String tema;
	private String area;
	private String carrera;
	private List<PreguntaJSON> lspreguntas;
	
	
	public BancoJSON() {

	}
	
	public BancoJSON(String codigo, String tema, String area, String carrera, List<PreguntaJSON> lspreguntas) {
		super();
		this.codigo = codigo;
		this.tema = tema;
		this.area = area;
		this.carrera = carrera;
		this.lspreguntas = lspreguntas;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getTema() {
		return tema;
	}
	public String getArea() {
		return area;
	}
	public String getCarrera() {
		return carrera;
	}
	public List<PreguntaJSON> getLspreguntas() {
		return lspreguntas;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public void setLspreguntas(List<PreguntaJSON> lspreguntas) {
		this.lspreguntas = lspreguntas;
	}
	
	
	

}
