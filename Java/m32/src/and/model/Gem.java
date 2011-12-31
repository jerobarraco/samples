package and.model;

import and.m32.MainActivity;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.Object3D;
import com.threed.jpct.Primitives;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;

public class Gem{
	public boolean alive;
	public int type;
	public Object3D obj;
	public static final RGBColor colores[] = {
		RGBColor.BLUE, RGBColor.GREEN, RGBColor.RED, 
		new RGBColor(75, 0, 130),//indigo
		new RGBColor(255, 127, 0), //naranja
		new RGBColor(255, 255, 0)//amarillo
		
	};
	public static final RGBColor selColor= new RGBColor(245,	222,	179);
	public static final int maxType = 4;
	public RGBColor mycolor = null;
	public static Object3D molde = null;
	public Gem(int type){
		if(maxType>5)type=0;
		if (molde == null){
			molde = Primitives.getSphere(5);
			molde.build();
			molde.strip();
		}

		obj = new Object3D(molde, true);
		mycolor = colores[type];
		obj.setAdditionalColor(mycolor);
		obj.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);//para poder elegirlo
		//obj.build();
		//obj.strip();
		
		this.alive = true;
		this.type = type;
	}
	public void setPos(float x, float y , float z){
		obj.translate(x, y, z);
	}
	public void reset(int type){
		if(maxType>5) type=0;
		this.type = type;
		mycolor = colores[type];
		obj.setAdditionalColor(mycolor);
	}
}
