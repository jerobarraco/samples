import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
public class m {

	/**
	 * @param args
	 */
	public static void mostrarLista(List l){
		if (l.isEmpty()){
			System.out.println("no hay nada"); 
		}else{
			for (int i=0; i< l.size(); i++){
				System.out.println("El elemento "+i+" es: "+l.get(i));
			}
		}
		/* forma corta pero no enseña ni get ni size
		for (Object o: l){
			System.out.println("El elemento "+ o);
		}*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List lista = new ArrayList();
		List lista = new LinkedList();
		lista.add("hola");
		lista.add(1);
		lista.add("mundo");
		lista.add(new asd());
		System.out.println(lista.toString());
		System.out.println(lista.size());
		System.out.println(lista.contains("hola"));
		mostrarLista(lista);
		lista.remove("hola");
		mostrarLista(lista);
		lista.clear();
		mostrarLista(lista);
		
	}

}
