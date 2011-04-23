import java.io.*;

public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = "";
		try {
			System.out.println("Ingrese la wea");
			input = br.readLine();
		} catch (Exception e){
			System.out.println(e);
			System.exit(1);
		}
		
		String[] parts = input.split(";");
		String nombre= parts[0];
		String cargo = parts[1];
		Float sueldo = Float.parseFloat(parts[2]) * 132.98f;
		System.out.println(String.format("Se leyó:\nNombre %s\nCargo %s\nSueldo %s", nombre, cargo, sueldo) );
		
		
	}

}
