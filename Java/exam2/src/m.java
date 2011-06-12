import java.util.*;
public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//opcion2:
		//Con map determinar el numero de palabras duplicadas en una oracion
		//y la lista de palabras que no se repiten
		
		String oracion = args[0];
		Map<String, Integer> mapa = new HashMap<String, Integer>();
		//sacamos los espacios repetidos
		while (oracion.contains("  ")){ oracion = oracion.replace("  ", " ");}
		//convertimos a minuscula para que tome como iguales palabras en mayuscula y minuscula
		oracion = oracion.toLowerCase();
		//por cada palabra
		for (String i: oracion.split(" ")){
			if (!mapa.containsKey(i)){
				//si la palabra no está en el mapa
				//la metemos y ponemos su contador en 1
				mapa.put(i, 1);
			}else{
				//si ya esta la incrementamos
				mapa.put(i, mapa.get(i)+1 );
			}
		}
		
		System.out.println("Cuenta");
		for (String i: mapa.keySet()){
			Integer val = mapa.get(i);
			System.out.println(i+" : " + val);
		}
		System.out.println("\nPalabras que no se repiten:");
		for (String i: mapa.keySet()){
			if (mapa.get(i)==1){
				System.out.println(i);
			}
		}
	}	

}

