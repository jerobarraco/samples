package model;
/*Clase Carrera: nombre, cantidad de materias, cantidad de a�os, titulo final*/
public class Carrera {
	private String nombre, titulo;
	private Integer materias, a�os, aprobadas;
	@Override
	public String toString(){
		return String.format(
				"%s '%s' (Materias: %s/%s = %s a�os)",
				nombre, titulo, aprobadas, materias, a�os
			); 
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Integer getMaterias() {
		return materias;
	}
	public void setMaterias(Integer materias) {
		this.materias = materias;
	}
	public Integer getA�os() {
		return a�os;
	}
	public void setA�os(Integer a�os) {
		this.a�os = a�os;
	}
	public Integer getAprobadas() {
		return aprobadas;
	}
	public void setAprobadas(Integer aprobadas) {
		this.aprobadas = aprobadas;
	}
}
