import java.util.*;
public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// opcion 1
		//programa que determina el numero de palabras duplicadas en una oracion
		//y lista las palabras que no se repiten
		String o = "Corre corre mi caballito mi veloz caballo";
		ArrayList<String> palabras = new ArrayList<String>();
		ArrayList<Integer> cuentas = new ArrayList<Integer>();
		
		Integer j = 0;
		o = o.toLowerCase();
		
		for (String i: o.split(" ") ){
			if (!palabras.contains(i)){
				palabras.add(i);
				cuentas.add(1);
			}else{
				j  = palabras.indexOf(i);
				cuentas.set(j, cuentas.get(j)+1);
			}
		}
		System.out.println("Cuentas");
		for (j = 0; j< palabras.size(); j++){
			System.out.println(palabras.get(j)+ " : "+cuentas.get(j));
		}
		System.out.println("\nPalabras que no se repiten");
		for (j = 0; j< palabras.size(); j++){
			if (cuentas.get(j)==1){
				System.out.println(palabras.get(j));
			}
		}
		

	}

}
