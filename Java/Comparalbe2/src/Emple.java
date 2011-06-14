import java.util.*;
public class Emple {
	private Integer dni = null;
	private String apellido = null;
	private String nombre = null;
	private Date fechaIngreso;
	public Emple(){
		dni = 0;
		apellido = "";
		nombre = "";
		fechaIngreso = new Date();
	}
	public Emple(int pDni, String pNombre,  String pApellido, Date pIngreso){
		dni = pDni;
		apellido = pApellido;
		nombre = pNombre;
		fechaIngreso = pIngreso;
	}
	@Override
	public String toString(){
		return dni + " " + apellido + " " + nombre + " (" + fechaIngreso +")"; 
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
}
