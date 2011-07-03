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
		String tmp;
		while (true){
			tmp = read("libreta o nada:");
			
			if (tmp.trim().equals("")){ break;}
			
			Integer libreta = Integer.parseInt(tmp);
			Alumno a = uni.add(libreta);
			
			a.setNombre(read("Nombre: "));
			a.setApellido(read("Apellido: "));			
			a.setDireccion(read("Direccion:"));
			a.setTelefono(read("Telefono"));
			a.setDni(Long.parseLong(read("Dni")));
			
			System.out.println("Ahora la carrera");
			Carrera c = a.getCarrera();
			c.setNombre(read("Nombre"));
			c.setTitulo(read("Titulo"));
			c.setMaterias(Integer.parseInt(read("Materias")));
			c.setAprobadas(Integer.parseInt(read("Materias Aprobadas")));
			c.setAños(Integer.parseInt(read("Años")));
		};
		uni.printList();
	}
}
