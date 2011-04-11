import java.io.BufferedReader;
import java.io.InputStreamReader;

public class programa {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		// TODO Auto-generated method stub
		
		Clase curso  = new Clase();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			System.out.println("ingrese el nombre del curso");
			String i = br.readLine();
			curso.setNombreCurso(i);
			
			System.out.println("Ingrese el nombre del profesor");
			
			Profesor p = curso.getProfesor();
			p.setApellido(br.readLine());
			
			System.out.println("Ingrese el legajo el profesor");
			p.setLegajo(br.readLine());
			
			System.out.println("Ingrese la cantidad de alumnos");
			for (int c = Integer.parseInt(br.readLine());c>0;c--){
				Alumno a = new Alumno();
				System.out.println("Ingrese el nombre");
				a.setNombre(br.readLine());
				System.out.println("Ingrese el apellido");
				a.setApellido(br.readLine());
				System.out.println("Ingrese el dni");
				a.setNroDoc(br.readLine());
				curso.AgregarAlumno(a);
			}
			
			System.out.println(curso.obtenerMontoTotal());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
