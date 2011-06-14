import java.util.*;
public class ExApe implements Comparator<Emple>{
	@Override
	public int compare(Emple o1, Emple o2){
		return o1.getApellido().compareTo(o2.getApellido());
	}
}
