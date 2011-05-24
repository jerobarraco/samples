
public abstract class Persona {
	private String nombre;
	private String apellido;
	private int DNI;
	public Persona(String n, String a, int d){
		nombre = n;
		apellido = a;
		DNI = d;
	}
	public String getNombre(){
		return nombre;
	}
}
