
public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer numero = Integer.parseInt(args[0]);
		
		if ((1000<numero) | (numero<10000)){
			System.out.println("El numero ingresado es incorrecto, debe estar entre 1000 y 10000");
		}
		char x =  args[1].charAt(0);
		if (!( x== 'P' | x=='I')){
			System.out.println("El codigo es incorrecto, tiene que ser P o I");
		}
		String m="";
		if (numero %2 == 0){
			if (x=='P'){
				m = "Correcto: Numero Par: %s. Código P";
			}else{
				m = "Error: Numero Par: %s. Código I";
			}
		}else{
			if (x=='I'){
				m = "Correcto: Numero Impar: %s. Código I";
			}else{
				m = "Incorrecto: Numero Impar: %s. Código P";
			}
		}
		System.out.println(String.format(m, numero));
	}

}
