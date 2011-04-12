import java.io.BufferedReader;
import java.io.InputStreamReader;

/* hace de todo */
public class Ejercicio {
	private String linea;
	
	/* lee de la consola
	 * @param preface el texto que se muestra al user antes de pedir la entrada
	 * @return un texto ingresado por el user
	 */
	private String Read(String preface){
		System.out.println(preface);
		String input ="";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try{
			input = br.readLine();
		}
		catch (Exception e){
			System.out.println(e);
		}
		return input;
	}
	public Ejercicio(){
		this.linea = this.Read("Ingrese la palabra:");
	}
	/* @return Devuelve una copia de la linea pero en mayuscula */
	public String Mayuscula(){
		return this.linea.toUpperCase();
	}
	/* @return Devuelve una copia de la linea, pero en minusucla */
	public String Minuscula(){
		return this.linea.toLowerCase();
	}
	/* @return Devuelve la tercer palabra si es que existe*/
	public String TercerPalabra(){	
		String res = new String();
		char[] arr = this.linea.toCharArray();
		int space_count = 0;
		
		for (char c : arr){
			if(c==' '){
				space_count++;
				if (space_count>2){
					break;
				}
			}else if (space_count==2){
				res += c;
			}			
		}
		return res;
		/* esto es mas facil pero el profe nos esta enseñando arrays
		 * return this.linea.split(" ")[2];
		 * 
		 *  esta es ooootra forma pero no quiere
		 *
		 *  int primer_espacio = this.linea.indexOf(" ",0);
		int segundo_espacio = this.linea.indexOf(" ", primer_espacio);
		int tercer_espacio = this.linea.indexOf(" ", segundo_espacio);
		return this.linea.substring(segundo_espacio, tercer_espacio);
		 */
	}
	/* Demuestra lo estupidos que son los nombres muy largos y descriptivos
	 * @return un entero con lo que dice el nombre de la funcion muy larga
	 */
	public int CantidadDeLetrasDeLaTercerPalabra(){
		return this.TercerPalabra().length();
	}
}
