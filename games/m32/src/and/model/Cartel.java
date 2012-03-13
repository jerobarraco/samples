package and.model;

import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;

public class Cartel {
	public Object3D obj ;
	private static final Object3D molde = Primitives.getPlane(2, 4);
	Cartel(int text){
		obj = new Object3D(molde, true);
		obj.build();
		obj.strip();
	}
}
