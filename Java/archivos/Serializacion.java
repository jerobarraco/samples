import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JOptionPane;
public class main {
	public static String getString(String msg){
		String r = JOptionPane.showInputDialog(msg);
		if (r==null){
			r = "";
		}
		return r.trim();
	}
	public static void leerDeConsola(Map<String, CPU> pcpus){
		CPU ncpu = new CPU();
		String tmp;
		while(true) {
			tmp = getString("Ingrese el codigo o nada para salir");
			if (tmp.isEmpty()){
				break;
			}
			ncpu.codigo = tmp;
			ncpu.modelo = getString("Modelo?");
			ncpu.fabricante = getString("Fabricante?");
			ncpu.nucleos = Integer.parseInt(getString("Nucleos?"));
			ncpu.velocidad = Float.parseFloat(getString("Velocidad?"));
			ncpu.precio = Double.parseDouble(getString("Precio?"));
			ncpu.stock = Integer.parseInt(getString("Stock?"));
			pcpus.put(ncpu.codigo, ncpu);
		}
	}
	public static void leerDeArchivo(Map<String, CPU> pcpus){
		File archivo = new File ("cpus.csv");
		try {
			//buffer de lectura
			BufferedReader br = new BufferedReader(new FileReader (archivo));
			String linea;
			//leemos linea por linea
			while(true){
				linea = br.readLine();
				if(linea == null){ break;}
				CPU ncpu = new CPU(linea);
				pcpus.put(ncpu.codigo, ncpu);
			}
			//cerramos
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void escribirAConsola(Map<String, CPU> pcpus){
		Iterator<CPU> it = pcpus.values().iterator();
		while(it.hasNext()){
			CPU cpu = it.next();
			System.out.println(cpu.toString());
		}
	}
	public static void escribirAArchivo(Map<String, CPU> pcpus){
		//escritura
		File arch = new File("cpus.csv");
		try {
			//Creamos un BufferedWriter para poder escribir 
			//Recibe como parametro un FileWriter
			BufferedWriter bw = new BufferedWriter(new FileWriter(arch));
			
			//Escribimos con .write
			/*for (String al :nombres){
				bw.write(al);
				bw.newLine();
			}*/
			Iterator<CPU> it = pcpus.values().iterator(); 
			while(it.hasNext()){
				CPU cpu = it.next();
				bw.write(cpu.toCSV());
				bw.newLine();
			}
			//Siempre hay que cerrar el archivo al terminar de usarlo
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		//almacen de CPUS
		Map<String, CPU> cpus = new TreeMap<String, CPU>();
		String opcion;
		while(true){
			opcion = JOptionPane.showInputDialog("Opciones:\n 1- Leer de consola\n 2- Leer de archivo\n 3- Escribir a consola\n 4- Escribir a archivo\n O nada para salir");
			if(opcion == null){
				opcion = "";
			}
			if (opcion.isEmpty()){
				break;
			}
			if(opcion.equals("1")){
				leerDeConsola(cpus);
			}
			if(opcion.equals("2")){
				leerDeArchivo(cpus);
			}
			if(opcion.equals("3")){
				escribirAConsola(cpus);
			}
			if(opcion.equals("4")){
				escribirAArchivo(cpus);
			}
		};
		
	}

}
