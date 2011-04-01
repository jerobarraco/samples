import java.sql.Date;
import java.sql.Time;


public class Turno {
	private Date fecha;
	private Time in, out;
	
	public Turno(Date f, Time i, Time o){
		fecha = f;
		in = i;
		out = o;
	}
	
	public String toString(){
		return fecha + " " + in + " " + out;
	}
	
	public double horas(){
		return (out.getTime() -in.getTime()) / (1000*60*60);
	}
}
