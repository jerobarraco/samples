import java.util.*;


public class m {

	public static void main(String[] args) {
		//Generar una lista (ArrayList) de 5 vehiculos.
		List cosos = new ArrayList();
		List menores = new ArrayList();
		
		cosos.add(new Auto(102000));
		cosos.add(new Auto(100000));
		cosos.add(new Auto(2103));
		cosos.add(new Auto(300));

		//Utilizar iterator para recorrer la lista.
		//Listar ambas listas con el mensaje
		System.out.println("Vehículos con más de 100.000km recorridos");
		Iterator i = cosos.iterator();
		while (i.hasNext()){
			Auto a = (Auto)i.next();
			//Generar otra lista ArrayList con los vehiculos con menos de 100000km recorridos 
			
			if (a.getKms()>100000){
				System.out.println(a);
			}
		}
		//Eliminar estos vehiculos de la lista principal.
		//Nota2: como último paso eliminar los elementos de la lista con mas de 100.000km recorridos 
		i = cosos.iterator();
		while (i.hasNext()){
			Auto a = (Auto)i.next();
			if (a.getKms()<=100000){
				menores.add(a);
				i.remove();
			}
		}
		System.out.println("Vehículos con menos de 100.000km recorridos");
		for (Object o: menores){ System.out.println((Auto)o);}
/*prefiero
 * for (Object o: cosos){ system.out.println((Auto)o);}
 */
		
	
	}

}
