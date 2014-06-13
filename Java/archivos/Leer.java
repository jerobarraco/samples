//Leer de un archivo de texto 
import java.io.*;


public class Leer {
	public static void main(String[] args) {
		//nombre de archivo
		File archivo = new File ("archivo.txt");          
		
		try {
			//buffer de lectura
			BufferedReader br = new BufferedReader(new FileReader (archivo));
			String linea;
			//leemos linea por linea
			linea = br.readLine();
			while(linea != null){
				System.out.println(linea);
				linea = br.readLine();
			}
			//cerramos
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
