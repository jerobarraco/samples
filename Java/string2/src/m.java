public class m {

	/*
	 * Consignas : 
	 * hacer un programa que reciba un string por pantalla de por lo menos
	 * cuatro palabras.
	 * Y muestre:
	 * a - el texto en minuscula
	 * b - el texto en mayuscula
	 * c - la tercera palabra
	 * e - la cantidad de letras de la tercer palabra
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ejercicio inst = new Ejercicio();
		System.out.println("Mayúsculas: " + inst.Mayuscula());
		System.out.println("Minúsculas: " + inst.Minuscula());
		System.out.println("La tercer palabra es: " + inst.TercerPalabra());
		System.out.println(
				String.format("La tercer palabra tiene %s caracteres.", 
						inst.CantidadDeLetrasDeLaTercerPalabra()
						)
		);
		
	}

}
