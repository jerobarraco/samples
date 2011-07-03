package model;
/*Clase manejadora*/
import java.util.*;
public class Universidad {
	private Map<Integer, Alumno> alumnos;
	public Universidad(){
		alumnos = new TreeMap<Integer, Alumno>();
	}
	public Alumno add(Integer pLib){
		Alumno a = new Alumno(pLib);
		alumnos.put(pLib, a);
		return a;
	}
	public void printList(){
		for (Integer l: alumnos.keySet()){
			System.out.println(alumnos.get(l));
		}
	}
	public Alumno addOrGet(Integer plib){
		if (alumnos.keySet().contains(plib)){
			return alumnos.get(plib);
		} else{
			return add(plib);
		}
	}
}
