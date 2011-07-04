package model;
/*Clase Carrera: nombre, cantidad de materias, cantidad de años, titulo final*/
public class Carrera {
	private String nombre, titulo;
	private Integer materias=0, años=0;
	@Override
	public String toString(){
		return String.format(
				"%s '%s' \n\tMaterias: %s\n\tAños: %s",
				nombre, titulo,  materias, años
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
	public Integer getAños() {
		return años;
	}
	public void setAños(Integer años) {
		this.años = años;
	}
}
