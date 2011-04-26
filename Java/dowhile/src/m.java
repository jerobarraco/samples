
public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numero = 123;
		int i=1;
		do{
			if (numero%i== 0){
				System.out.println("El numero es divisible por "+i);
			}
			i++;
		} while (i<=numero);
		
		/*
		 * while (i<=numero){
		 * 	if(numero%i==0){
		 * 		system.out.println("el numero es divisible por " +i);
		 * 	}
		 * i++;
		 * }
		 */
	}
}
