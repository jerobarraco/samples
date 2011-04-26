import java.util.ArrayList;

import javax.swing.JOptionPane;

public class m {

	/**
	 * @param args
	 */
	static String minuscula = "abcdefghijklmnopqrstuvwxyz";
	static String mayuscula = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static String numeros = "0123456789";
	static String varios = "_";
	public static int[] Contar(String s){
		int [] cants = {0, 0, 0, 0, 0};
		int tipo = 0;
		for (int i= 0; i<s.length();i++){
			
			//Uso substring porque convertir de char a string es muy molesto
			String ch = s.substring(i, i+1);
			
			if ( mayuscula.contains(ch) )
				tipo = 0;
			else if ( minuscula.contains( ch ) )
				tipo = 1;
			else
			if ( numeros.contains( ch ) )
				tipo = 2;
			else
			if ( varios.contains( ch ) )
				tipo = 3;
			else //ingresó fruta
				tipo = -1;
			
			// Uso la variable tipo solo poque le ejercicio requiere un switch
			// Podría haber usado la ténica del Dr Appelhans del switch inverso
			// pero java no puede convertir bool a int
			// Si se fijan pueden ver que tambien podria haber usado cants[tipo]++ pero hay que usar switch :B
			switch (tipo){
				case 0: cants[0]++; break;
				case 1: cants[1]++; break;
				case 2: cants[2]++; break;
				case 3: cants[3]++; break;
				default : cants[4]++; break;
			}
		}
		return cants;
		
	}
	public static boolean Segura(String s){
		if (s.length()<10){
			System.out.println("El password debe tener al menos 10 caracteres");
			return false;
		}
		int []cants = Contar(s);
		
		if (cants[4]>0){
			System.out.println("El password contiene al menos un caracter inválido");
			return false;
		}
		if (cants[0]<3){
			System.out.println("El password debe contener al menos 3 letras mayúsculas");
			return false;
		}
		if (cants[1]<3){
			System.out.println("El password debe contener al menos 3 letras minúsculas");
			return false;
		}
		if (cants[2]<4){
			System.out.println("El password debe contener al menos 4 dígitos numéricos.");
			return false;
		}
			
		return true;
	}
	/*
	public static int [] Contar(String s){
		
		int [] a = {0, 0, 0};
		//new int [3];//0:mayusculas, 1:minusculas, 2:numeros
		
		
		for (char c : s.toCharArray()){
			switch(c){
				case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
				case 'g': case 'h': case 'i': case 'j': case 'k': case 'l':
				case 'm': case 'n': case 'o': case 'p': case 'q': case 'r':
				case 's': case 't': case 'u': case 'v': case 'w': case 'x':
				case 'y': case 'z':
					a[0]++;
					break;
				case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
				case 'G': case 'H': case 'I': case 'J': case 'K': case 'L':
				case 'M': case 'N': case 'O': case 'P': case 'Q': case 'R':
				case 'S': case 'T': case 'U': case 'V': case 'W': case 'X':
				case 'Y': case 'Z':
					a[1]++;
					break;
				case '1': case '2': case '3': case '4': case '5': case '6':
				case '7': case '8': case '9': case '0':
					a[2]++;
					break;
			}
		}		
	}
	public static boolean Segura(String s){
		int [] res = Contar(s);
		if (s.length()<10) return false;
		if (a[0]>3) return false;
		if (a[1]>3) return false;
		
		return true;
	}*/
	public static void main(String[] args) {
		// tener al menos 10 caracteres
		// tres letras mayusculas al menos
		// trs letras minusculas al menos
		// cuadro digitos numericos
		// se aceptan letras numeros y guion bajo "_"
		//usar un array para guardar los subtotales
		
		JOptionPane jop = new JOptionPane();
		
		boolean sec = Segura(jop.showInputDialog("Ingrese su clave"));
		
		if (sec){
			System.out.println("Su contraseña es segura");
		} else{
			System.out.println("Su contraseña NO es segura");
		}
	}	

}
