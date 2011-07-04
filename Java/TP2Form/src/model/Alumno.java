package model;
/*Clase Alumno (hereda de persona): nro libreta,  carrera*/
public class Alumno extends Persona {
	private Integer libreta;
	private Carrera carrera;//guardo la carrera como una referencia a propósito
	//porque al borrar la carrera puede quedar mal. y si borro una carrera en uso igual deberia cambiar al user
	private Integer aprobadas=0;
	public Integer getAprobadas() {
		return aprobadas;
	}
	public void setAprobadas(Integer aprobadas) {
		this.aprobadas = aprobadas;
	}
	
	public Alumno(Integer pLib){
		super();
		libreta = pLib;
	}
	@Override
	public String toString(){
		return String.format(
				"%s : \nCarrera: %s\n\nDatos:\n%s",
				libreta, carrera, super.toString() );
	}
	public Integer getLibreta() {
		return libreta;
	}
	public void setLibreta(Integer libreta) {
		this.libreta = libreta;
	}
	public Carrera getCarrera() {
		return carrera;
	}
	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
}
