import java.util.*;


public class m {

	/**
	 * @param args
	 */
	public static void mostrarLista(Map<String, Obj> m){
		//Importante discriminar los tipos de clave/objeto en el Map
		//iterator style!
		Iterator<Obj> i = m.values().iterator();
		//importante el tipo de objeto del iterador
		
		System.out.println("Imprimiendo elementos");
		while (i.hasNext()){	
			System.out.println(i.next());
		}
		System.out.println("Fin lista \n-------------------------\n");
		/*
		//new for
		for (Obj o: m.values()){
			System.out.println(o);
		}*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Creamos u mapa
		Map < String, Obj> mapObjetos = new TreeMap<String, Obj>();
		
		mapObjetos.put("uno", new Obj("Juan", 2));
		mapObjetos.put("dos", new Obj("Pedro", 3));
		mapObjetos.put("tres", new Obj("Jorge", 5));
		
		mostrarLista(mapObjetos);
		
		mapObjetos.remove("uno");
		
		mostrarLista(mapObjetos);
		mapObjetos.clear();
		
	}

}
