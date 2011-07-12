package form;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import model.Alumno;
import model.Carrera;
import model.Universidad;

public class Menu {
	private static BufferedReader bi = new  BufferedReader( new InputStreamReader(System.in));
	private static Universidad uni = new Universidad();
	public static String read(String msg){
		System.out.println(msg);
		try{
			return bi.readLine();
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}	
	}
	public static void show(){
		int op = 1;
		while (op<7){
			System.out.println("\t1- Agregar un alumno");
			System.out.println("\t2- Eliminar un alumno");
			System.out.println("\t3- Imprimir Alumnos");
			System.out.println("\t4- Agregar un Carrera");
			System.out.println("\t5- Eliminar un Carrera");
			System.out.println("\t6- Imprimir Carreras");
			System.out.println("\t7- Salir");
			op = Integer.parseInt(read("Elija su opcion: "));
			switch(op){
				case 1: addAlumno(); break;
				case 2: removeAlumno(); break;
				case 3: printAlumnos(); break;
				case 4: addCarrera(); break;
				case 5: removeCarrera(); break;
				case 6: printCarreras(); break;
				case 7: System.exit(0); break;
			}
		}
	}
	public static void addAlumno(){
		int i = Integer.parseInt(read("Ingrese la libreta"));
		Alumno a = new Alumno(i);
		a.setApellido(read("Apellido"));
		a.setAprobadas(0);
		i = Integer.parseInt(read("Numero de carrera"));
		Carrera c = uni.getCarrera(i);
		a.setCarrera(c);
		uni.addAlumno(a);
	}
	public static void removeAlumno(){
		int i = Integer.parseInt(read("Ingrese la libreta"));
		uni.delAlumno(i);
	}
	public static void printAlumnos(){
		uni.printAlumnos();
	}
	public static void addCarrera(){
		Carrera c = new Carrera();
		c.setNombre(read("Nombre"));
		c.setTitulo(read("Titulo"));
		c.setMaterias(Integer.parseInt(read("Materias")));
		c.setAños(Integer.parseInt(read("Años")));
		uni.addCarrera(c);
	}
	public static void removeCarrera(){
		int n = Integer.parseInt(read("Numero de carrera"));
		uni.delCarrera(n);
	}
	public static void printCarreras(){
		uni.printCarreras();
	}
}