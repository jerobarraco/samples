import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Console {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try{
			String texto = in.readLine();
			int nro = Integer.parseInt(texto);
			System.out.println("Nro ingresado con readline es "+ nro);
		}catch(Exception error){
			System.out.println(error);
		}
		
	}

}
