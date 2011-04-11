
public class Alumno {
	private String apellido;
	private String nombre;
	private String nroDoc;
	private static int costo_matricula = 50;
	public int getMatricula(){
		return costo_matricula;
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
	public String getNroDoc() {
		return nroDoc;
	}
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}
	
	
}
