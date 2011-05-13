import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class m {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ejercicio2(5);
	}
	public static void PrintDate(GregorianCalendar gc){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-hh:mm");
		System.out.println("Dia " + gc.get(Calendar.DAY_OF_MONTH));
		System.out.println("Mes " +  gc.get(Calendar.MONTH)+1);
		System.out.println("Año "+ gc.get(Calendar.YEAR));
		System.out.println(sdf.format(gc.getTime()));
	}
	public static void ejercicio2(Integer dias){
		GregorianCalendar  gc = new GregorianCalendar();
		gc.setTime(new Date());
		PrintDate(gc);
		System.out.println("-----");
		gc.add(Calendar.DAY_OF_MONTH, dias);
		PrintDate(gc);
	}

}
