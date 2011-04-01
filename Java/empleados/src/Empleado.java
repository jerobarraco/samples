import java.sql.Date;
import java.sql.Time;
import java.util.Vector;


public class Empleado {
	public float sueldo;
	public Vector<Turno> turnos;
	public String name, ape;
	public Integer dni;
	public Empleado (float hora, String name, String ape, Integer dni){
		this.name = name;
		this.ape = ape;
		this.dni = dni;
		this.sueldo = hora;  
		turnos = new Vector<Turno>();
	}
	public void AgregarTurno(int d, int m, int a, int hor, int min, int h2, int m2){
		Date date = new Date(a,m,d);
		Time in = new Time(hor,min,0);
		Time out = new Time(h2, m2, 0);
		turnos.add(new Turno(date, in, out));
		
	}
	public String toString(){
		String s= new String();
		for (Turno t : turnos){
			s = s+ t + "\n";
		}
		return s;
	}
	public double CalcularHonorarios(){
		double h =0;
		for (Turno t: turnos){
			h += t.horas()*sueldo;
		}
		return h;
	}
}

