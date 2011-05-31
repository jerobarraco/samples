import java.util.*;
public class m {
	/**
	 * Devuelve un ArrayList con los 5 numeros mas repetidos
	 * tiene la propiedad de devolver los numeros por orden de mayor ocurrencia
	 * 
	 */
	public static ArrayList<Integer> Busco5Max ( ArrayList<Integer> l){
	
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		//Creo un map para guardar la relacion Elemento-Cantidad de apariciones
		Map <Integer, Integer> m = new TreeMap<Integer, Integer>();
		//Recorro el AL
		for (Integer n: l){
			//Y voy creando el map
			//Si no está el elemento, lo creo
			if (!m.containsKey(n)){
				m.put(n, 0);
			}
			//E incremento las ocurrencias
			m.put(n, m.get(n)+1);
		}
		//Si hay menos de 5 elementos devolvemos todos
		if (m.values().size()<5 ){
			return new ArrayList<Integer>(m.keySet());
		}
		//si hay mas....
		
		//elijo los 5 primeros
		Integer maxk, maxv, v;//key maximo, valor maximo
		//recorro el map 5 veces
		for (int i = 0; i<5; i++){
			//Tomo el valor máximo
			maxv = Integer.MIN_VALUE;
			//java se queja de que puede no estar inicializad porque no entiende mi lógica foolproof
			maxk = 0;
			//recorro el map
			for (int k: m.keySet()){
				//si este número no está en la lista de resultados 
				if (!res.contains(k)){
					//buscamos el máximo
					v = m.get(k);
					if (v > maxv){
						maxv = v;
						maxk = k;
					}
				}
			}
			//agregamos el mayor al resultado
			res.add(maxk);
		}
		
		return res;
	}
	public static void main(String[] args) {
		//Dado un ArrayList donde se le cargan al menos 15 numeros (como minimo)
		//Hacer un procedimiento que se llame Busco5Max (Array list<integer> listaInt) que 
		//devuelva los 5 numeros mas repetidos de la lista (en un array List)
		
		//ej ingresado 1,2,2,2,2,2,2,3,4,5,5,6,7,7,7,7,7,10,10,10,10
		
		ArrayList <Integer> userentry = new ArrayList<Integer>();//Y por esta razon en c tenemos typedef y macros :D 
		
		Integer [] nums = {1,2,2,2,2,2,2,3,4,5,6, 7,7,7,7,7,7,10,10,10,10,10};
		for (int i: nums ){
			userentry.add(i);
		}
		
		ArrayList <Integer> xxx = Busco5Max(userentry);
		System.out.println(xxx);
	}

}
