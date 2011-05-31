/**
 * • programa que determina el numero de palabras duplicadas en una oracion, trate igual a las letras mayusculas y 
 * • minusculas e ignore signos de puntuacion 
 * •Hacer un procedimiento  que se llame 
 * •private static int PalabrasDuplicadas(String cadena) que devuelva la cantidad de palabras
 * 
 * @author Barraco Mármol Jerónimo
 *
 */
import java.util.*;

public class m {

	/**Dada una cadena devuelve una lista de palabras y cuantas veces se ha repetido cada palabra
	 * @param args
	 */
	private static Map<String, Integer> PalabrasDuplicadas(String cadena){
		Map<String, Integer> res = new TreeMap<String, Integer>();
		String eliminar = "¿?!¡.,:;";
		String ns = cadena;
		//1º hay que pasar la cadena a lower
		ns = ns.toLowerCase();
		//2º quitamos la puntacion
		for (int i = 0; i< eliminar.length(); i++){
			String c = eliminar.substring(i, i+1);
			while (ns.contains(c)){
				ns = ns.replace(c, "");
			}
		}
		
		//3º los multiples espacios
		while (ns.contains("  ")){ ns = ns.replace("  ", " ");}
		//4º pequeña ayuda para dividir por palabras
		for (String key: ns.split(" ")){
			if (!res.containsKey(key)){
				res.put(key,0);
			}
			res.put(key, res.get(key)+1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(PalabrasDuplicadas("LA PAMPa,   TIENE EL OMBu   EL OMBU  LA LA CARRETERA LAS. 123 LINEAS:  LOS EMAILS LOS"));
	}

}
