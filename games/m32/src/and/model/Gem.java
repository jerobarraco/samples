package and.model;

import and.m32.MainActivity;
import android.util.Log;
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
		new RGBColor(0,	168,	107), //Verde jade
		new RGBColor(0,	71,	171), //azul cobalto
		new RGBColor(150,	0	,24), //carmin
		new RGBColor(75, 0, 130),//indigo
		new RGBColor(255, 127, 0), //naranja
		//new RGBColor(255, 255, 0),//amarillo//odio el amarillo
		RGBColor.BLUE, RGBColor.GREEN, RGBColor.RED
	};
	public static final RGBColor selColor= new RGBColor(255	,127,	80	); //en honor a mi bb//new RGBColor(245,	222,	179);no se cual es
	public static final int maxType = 4;
	public static Object3D molde = null;
	
	public RGBColor mycolor = null;
	
	
	public Gem(int type){
		Log.d("gema", "Tipo "+String.valueOf(type));
		mycolor = colores[type];
		if (molde == null){
			molde = Primitives.getSphere(8);
			molde.build();
			molde.strip();
		}

		obj = new Object3D(molde, true);
		
		obj.setAdditionalColor(mycolor);
		obj.setCollisionMode(Object3D.COLLISION_CHECK_OTHERS);//para poder elegirlo
		obj.build();
		obj.strip();
		
		this.alive = true;
		this.type = type;
	}
	public void setPos(float x, float y , float z){
		obj.translate(x, y, z);
	}
	public void reset(int type){
		this.type = type;
		mycolor = colores[type];
		obj.setAdditionalColor(mycolor);
	}
	public void select(){
		obj.setAdditionalColor(selColor);
	}
	public void deselect(){
		obj.setAdditionalColor(mycolor);
	}
}
