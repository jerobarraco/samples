import java.util.GregorianCalendar;


public class m {

	/**
	 * Hacer un pgromama que utilizando gregorian clanedar con la fecha actual,
	 * muestre por pantalla los contenidosdelas posiciones 1, 2 y 5
	 * (que int java.util.calendar.get(int field))
	 * 
	 * 2 hacer un programa que al ingersar la fecha, calcule los meses que
	 * faltan para su nuevo cumplea�os (dentro del a�o).
	 * (todos los meses tienen 30 dias, y use el constructor)
	 * @param args
	 */
	public static int[] Range(int to){
		int[] res = new int[to];
		
		for (int i=0; i<to; i++){
			res[i] =  i;
		}
		return res;
	}
	public static void main(String[] args) {
		EJDate inst = new EJDate();
		System.out.println("Faltan %s d�as para su cumplea�os.".format( String.valueOf(inst.diasHastaMiCumple())) );
	}
}