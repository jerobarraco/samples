package model;
/*Clase manejadora*/
import java.util.*;

public class Universidad {
	private Map<Integer, Alumno> alumnos;
	private List<Carrera> carreras;
	public Universidad(){
		alumnos = new TreeMap<Integer, Alumno>();
		carreras = new ArrayList<Carrera>();
	}
	public void addAlumno(Alumno pa){
		alumnos.put(pa.getLibreta(), pa);
	}
	public void printAlumnos(){
		for (Integer l: alumnos.keySet()){
			System.out.println(alumnos.get(l));
		}
	}
	public void printCarreras(){
		for (Carrera c: carreras){
			System.out.println(c);
		}
	}
	public Alumno getAlumno(Integer plib){
		return alumnos.get(plib);
	}
	public void delAlumno(Integer plib){
		alumnos.remove(plib);
	}
	public Set<Integer> getAlumnosLibs(){
		return alumnos.keySet();
	}
	
	public Carrera getCarrera(Integer i){
		return carreras.get(i);
	}
	public Integer getCantCarreras(){
		return carreras.size();
	}
	public void addCarrera(Carrera carrera){
		//por el tema de referencias, modificar sale gratis
		carreras.add(carrera);	
	}
	public void delCarrera(int i){
		carreras.remove(i);
	}
}