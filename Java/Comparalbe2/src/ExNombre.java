import java.util.*;
public class ExNombre implements Comparator<Emple>{

	@Override
	public int compare(Emple o1, Emple o2) {
		// TODO Auto-generated method stub
		return o1.getNombre().compareTo( o2.getNombre() );
	}
}
