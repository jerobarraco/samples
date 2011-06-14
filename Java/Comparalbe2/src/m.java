import java.util.*;
class m {

	/**
	 * @param args
	 */
	public static void print(ArrayList<Emple> empleados){
		for( Emple e : empleados ){
			System.out.println(e);
		}
	}
	public static void main(String[] args) {

		ArrayList<Emple> v = new ArrayList<Emple>();
		v.add (new Emple(123213, "Juan", "Perez", new Date() ));
		v.add (new Emple(1345345, "Jorge", "Lopez", new Date() ));
		v.add (new Emple(18324324, "José", "Gomez", new Date() ));
		v.add (new Emple(94138, "Roberto", "Sanchez", new Date() ));
		v.add (new Emple(1983, "Cristian", "Caro", new Date() ));
		v.add (new Emple(87246, "Romina", "Lauman", new Date() ));
		v.add (new Emple(9509853, "Pedro", "Shoji", new Date() ));
		v.add (new Emple(208554, "Luis", "Barraco", new Date() ));
		v.add (new Emple(2034985034, "Manuel", "Artigas", new Date() ));
		v.add (new Emple(29485324, "Martin", "Buona Parte", new Date() ));
		
		
		System.out.println("Por apellido");
		Collections.sort(v, new ExApe());
		print(v);
		
		System.out.println("\n\nPor Nombre");
		Collections.sort(v, new ExNombre());
		print(v);
		
		System.out.println("\n\nPor DNI");
		Collections.sort(v, new ExDNI());
		print(v);
	}
}
