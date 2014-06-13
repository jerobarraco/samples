//Escribir en un archivo de texto
import java.io.*;
public class Escribir {
	public static void main(String[] args) {
		//Primero se crea el nombre del archivo que elijamos
		File arch = new File("archivo.txt");
		try {
			//Creamos un BufferedWriter para poder escribir 
			//Recibe como parametro un FileWriter
			BufferedWriter bw = new BufferedWriter(new FileWriter(arch));
			//Escribimos con .write
			bw.write("Hola mundo!");
			//Escribimos un salto de linea
			bw.newLine(); //CASI IGUAL a bw.write("\n");//nueva linea
			bw.write("Otra linea");
			//Siempre hay que cerrar el archivo al terminar de usarlo
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
