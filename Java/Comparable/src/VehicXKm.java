import java.util.Comparator;


public class VehicXKm implements Comparator<Vehiculo>{

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		// TODO Auto-generated method stub
		return (int) (o1.getKmRecorridos()-o2.getKmRecorridos());
	}

}
