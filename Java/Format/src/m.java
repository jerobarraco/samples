import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.NumberFormat;


public class m {

	/**
	 * @param args
	 * Hacer ejercicio donde dado un valor ingresado por pantalla convertirlo a float
	 * y mostrarlo como currency ($) con 7 digitos y 2 decimales
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader(isr);
		Float f = null;
		System.out.println("ingrese un numero flotante:\n");
		try{
			f = Float.parseFloat(bf.readLine());
		}catch (Exception e){
			System.out.println(e);
			System.exit(1);
		}
		
		
		NumberFormat nfc = NumberFormat.getCurrencyInstance();
		NumberFormat nfcp = NumberFormat.getPercentInstance();
		NumberFormat nfcn = NumberFormat.getNumberInstance();
		nfc.setMinimumIntegerDigits(7);
		nfc.setMaximumFractionDigits(2);
		nfc.setMaximumFractionDigits(2);
		
		//nfcp.setMaximumFractionDigits(0);
		nfcp.setMaximumFractionDigits(0);
		
		nfcn.setMinimumIntegerDigits(5);
		nfcn.setMaximumFractionDigits(2);
		nfcn.setMaximumFractionDigits(2);
		
		System.out.println("el numero formateado como plata: " + nfc.format(f));
		System.out.println("el numero formateado como plporcentaje: " + nfcp.format(f));
		System.out.println("el numero formateado como numero: " + nfcn.format(f));
		
		
	}

}
