import java.util.*;
import javax.swing.JOptionPane;

public class m {

	public static void p(String s){System.out.println(s);}
	public static void main(String[] args) {
		// Opcion 3
		/*Con map/array de alumnoas (nombre y nota) 
		 * listar los alumnoscon notas menor a 4
		 * y los alumnos con la (misma) mayor calificacion
		 */
		Map<String, Integer> al = new HashMap<String, Integer>();
		Integer may = Integer.MIN_VALUE;
		String in = "asd";
		Integer ini = 0;
		while (true){
			in = JOptionPane.showInputDialog("Ingrese nombre del alumno o nada para terminar");
			if ((in == null) || (in.isEmpty())){break;}
			ini = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nota del alumno"));
			al.put(in, ini);
		}
		/*
		al.put("Juan Perez", 0);
		al.put("Luis Garcia", 7);
		al.put("Jorge Rodriguez", 4);
		al.put("Roberto Algo", 3);
		al.put("Otro", 6);
		al.put("Otro Otro", 7);*/
		
		p("Alumnos con nota menor a 4");
		for (String i : al.keySet()){
			Integer n = al.get(i);
			if (n<4){
				p(i+":"+n);
			}
			if(n>may){may=n;}
		}
		p("\nMayor nota: "+may);
		p("\nAlumnos con la mayor nota:");
		for (String i: al.keySet()){
			if (al.get(i)== may){
				p(i);
			}
		}
		
	}

}
