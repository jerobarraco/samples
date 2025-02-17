package model; 
/*Clase Persona: con atributos m�nimos  Nombre, Apellido, Nro Documento, telefono*/
public class Persona {
	private String nombre, apellido, telefono, direccion;
	private Long dni;
	@Override
	public String toString() {
		return String.format(
				"Datos: %s %s (%s) Tel: %s , Direccion: %s",
				nombre, apellido, dni, telefono, direccion
				);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getDni() {
		return dni;
	}
	public void setDni(Long dni) {
		this.dni = dni;
	}
	
}
