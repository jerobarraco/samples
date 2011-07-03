package model;
/*Clase Alumno (hereda de persona): nro libreta,  carrera*/
public class Alumno extends Persona {
	private Integer libreta;
	private Carrera carrera;
	public Alumno(Integer pLib){
		libreta = pLib;
		carrera = new Carrera();
	}
	@Override
	public String toString(){
		return String.format(
				"[%s] (%s) %s",
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
