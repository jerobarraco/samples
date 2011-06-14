import java.util.Comparator;


public class VehiculoXColor implements Comparator<Vehiculo>{

	@Override
	public int compare(Vehiculo arg0, Vehiculo arg1) {
		return arg0.getColor().compareTo(arg1.getColor());
	}
	

}
