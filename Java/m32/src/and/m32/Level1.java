package and.m32;

import and.model.Gem;
import android.util.Log;
import android.view.MotionEvent;
import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import java.util.Random;

public class Level1 extends Level{
	Object3D cube;

	Level1(){
		super(6, 6);
		
		String tn = "cubo";
		MainActivity.loadTexture(R.drawable.icon, 64, 64, tn);
		
		/*cube = Primitives.getCube(10);
		cube.calcTextureWrapSpherical();
		cube.setTexture(tn);
		cube.strip();
		cube.build();
		//SimpleVector sv = cube.getTransformedCenter();
		
		
		world.addObject(cube);*/
		SimpleVector sv = new SimpleVector(0,0,0);
		Camera cam = world.getCamera();
		cam.moveCamera(Camera.CAMERA_MOVEOUT, 100);
		cam.lookAt(sv);
		
		sv.y -= 100;
		sv.z -= 100;
		sun.setPosition(sv);		
		setupGrid();
	}
	
	
	private void setupGrid() {		
		float x = -40;
		float y = -40;
		int cont = 0;//para evitar usar%
		for (int i= 0; i<gSize; i++ ){
			Gem g = new Gem(r.nextInt(Gem.maxType));
			
			g.setPos(x, y, 0);
			grid[i] = g;
			world.addObject(g.obj);
			
			x+=15;
			cont ++;
			
			if (cont==gridw) {
				cont = 0;
				y += 15;
				x = -40;
			}
		}
	}			
}
