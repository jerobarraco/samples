
public class Alumno extends Persona{
	private Integer nLibreta;
	private Integer aIngreso;
	private boolean regular;
	public Alumno ( String name, String lname, int dni, int libreta, int ingreso, boolean reg){
		super(name, lname, dni);
		nLibreta = libreta;
		aIngreso = ingreso;
		regular = reg;
	}
}
