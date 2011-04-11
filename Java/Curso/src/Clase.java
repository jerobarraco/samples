import java.util.Vector;
public class Clase {
	private String nombreCurso;
	private Profesor profesor = new Profesor();
	private Vector<Alumno> alumnos = new Vector<Alumno>();
	public int obtenerMontoTotal(){
		int costo=0;
		for (Alumno a: alumnos){
			costo+= a.getMatricula();
		}
		
		return costo;
	}
	public void AgregarAlumno(Alumno pAlumno){
		alumnos.add(pAlumno);
	}
	//GS
	public void SetProfesor(Profesor pProfesor){
		profesor = pProfesor;
	} 
	public void setNombreCurso(String pNombreCurso){ nombreCurso = pNombreCurso;};
	public String getNombreCurso(){return nombreCurso;};
	
	public Profesor getProfesor(){return profesor;}
	
}
