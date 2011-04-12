import java.util.GregorianCalendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EJDate {
	GregorianCalendar fecha;
	GregorianCalendar fecha_cumple;	
	public EJDate(){
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br= new BufferedReader(is);
		int dia = 0;
		int mes = 0;
		int year = 0;//pensaste que iba a poner ano no?
		
		this.fecha = new GregorianCalendar(); //Teoricamente inicializa en hoy
		year = this.fecha.get(GregorianCalendar.YEAR);
		try {
			System.out.println("De tu cumple:");
			System.out.println("Ingrese el dia");
			dia = Integer.parseInt(br.readLine());
			System.out.println("Ingrese el mes");
			mes = Integer.parseInt(br.readLine());
			System.out.println("Ingrese el año");
			year = Integer.parseInt(br.readLine());			
		} catch (Exception e){
			System.out.println(e);
		}
		fecha_cumple = new GregorianCalendar(year, mes, dia);	
	}
	public int getPos(int pPos){
		return fecha.get(pPos);
	}
	public int diasHastaMiCumple(){
		int dias = this.fecha.get(GregorianCalendar.DAY_OF_YEAR)
			- this.fecha_cumple.get(GregorianCalendar.DAY_OF_YEAR);
		if (dias<0){
			dias +=365;
		}
		return dias;
	}
}
