import java.util.Comparator;


public class VehicXPasajeros implements Comparator<Vehiculo> {

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		// TODO Auto-generated method stub
		return o1.getCantPasajeros() - o1.getCantPAsajeros();
	}

}
