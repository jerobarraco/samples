
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Empleado e = new Empleado(5, "", "", 3);
		e.AgregarTurno(0, 0, 0, 0, 0, 1, 0);
		e.AgregarTurno(1, 1, 1, 1, 1, 5, 1);
		System.out.println(e.CalcularHonorarios());
		System.out.println (e);
		
	}

}
