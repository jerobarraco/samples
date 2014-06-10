import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;



//Leer lista de un archivo
public class main {
	public static void main(String[] args) {
		//Donde almacenar los datos
		List<String> nombres = new ArrayList<String>();
		//manejador del archivo
		File archivo = new File ("alumnos.txt");          
		
		//lectura de archivo
		try {
			BufferedReader br = new BufferedReader(new FileReader (archivo));
			String linea;
			//leemos del archivo linea por linea
			linea = br.readLine();
			while(linea != null){
				nombres.add(linea);
				linea = br.readLine();
			}
			
			 /*do{
			 		linea = br.readLine();
			 		if(linea != null){
			 			nombres.add(linea);
			 		}
			 }while(linea != null);*/
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//iteramos datos cargados
		Iterator<String> it = nombres.iterator();
		String mensaje = "";
		while (it.hasNext()){
			mensaje += it.next();
			mensaje += "\n";
		}
		JOptionPane.showMessageDialog(null, mensaje);
	}

}
