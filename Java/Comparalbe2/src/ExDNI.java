import java.util.Comparator;

public class ExDNI implements Comparator<Emple>{

	@Override
	public int compare(Emple o1, Emple o2) {
		return o1.getDni() - o2.getDni();
	}

	

}
