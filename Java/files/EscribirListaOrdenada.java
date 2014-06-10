import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JOptionPane;


public class main {

	public static void main(String[] args) {
		//almacen de NOMBRES Y APELLIDOS
		SortedSet<String> nombres = new TreeSet<String>();
		
		//carga
		String nombre = "";
		do{
			nombre = JOptionPane.showInputDialog("Ingrese un nombre y apellido. O deje vacio para terminar" );
			if (nombre == null){
				nombre = "";
			}
			if (!nombre.equals("")){
				nombres.add(nombre);
			}
		}while (!nombre.equals(""));
		
		//escritura
		File arch = new File("alumnos.txt");
		try {
			//Creamos un BufferedWriter para poder escribir 
			//Recibe como parametro un FileWriter
			BufferedWriter bw = new BufferedWriter(new FileWriter(arch));
			
			//Escribimos con .write
			/*for (String al :nombres){
				bw.write(al);
				bw.newLine();
			}*/
			Iterator<String> it = nombres.iterator(); 
			while(it.hasNext()){
				String nya = it.next();
				bw.write(nya);
				bw.newLine();
			}
			//Siempre hay que cerrar el archivo al terminar de usarlo
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
