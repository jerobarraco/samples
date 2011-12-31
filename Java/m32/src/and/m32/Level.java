package and.m32;

import and.model.Gem;
import android.util.Log;
import android.view.MotionEvent;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.Light;
import com.threed.jpct.Logger;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;
import java.util.Random;

public class Level {
	protected World world; 
	protected Light sun;
	protected Object3D selected;
	public Gem grid[]; //do not modiy plz
	public int gridw;
	public int gridh;
	public int gSize;
	protected Checker check;
	protected Random r; 
	private static final String tag = "level";
	public Level(int gw, int gh){
		r = new Random(System.currentTimeMillis());
		world = new World();
		world.setAmbientLight(20, 20, 20);

		sun = new Light(world);
		sun.setIntensity(250, 250, 250);
		gridw = gw;
		gridh = gh;
		gSize = gridw*gridh;
		grid = new Gem[gSize];
		check = new Checker(this);		
	}
	public Object[] getObjectAt(float x, float y){
		FrameBuffer fb = MainActivity.getFb();
		SimpleVector dir= Interact2D.reproject2D3D(world.getCamera(), fb, Math.round(x), Math.round(y)).normalize();
		return world.calcMinDistanceAndObject3D(world.getCamera().getPosition(), dir, 10000);
	}
	
	public boolean onTouchEvent(MotionEvent me) { //especie de mainloop :D
		float nx, ny;
		nx = me.getX();
		ny = me.getY();
		if (me.getAction() == MotionEvent.ACTION_DOWN) {			
			Object3D o = (Object3D) getObjectAt(nx, ny)[1];
			if (o!=null){
				//esto se puede optimizar poniendolo en el else del if de selected
				Gem g=null;
				int i;
				for (i = 0 ; i< gSize; i++){
					g= grid[i];
					if (g.obj==o) break;
				}
				Logger.log("Click en la gema nº" + String.valueOf(i));

				int x, y;

				x = i% gridw;
				y = i/gridw;
				Log.d("Gema en coords", String.format("%s, %s", x, y));
			
				if (selected ==  null){
					selected = o;
					o.setAdditionalColor(Gem.selColor);
				}else{
					//si el 2º click es en el mismo obj que estaba seleccionado antes
					//if(selected == o){
						//de-seleccionamos
					
						//tengo que conseguir la gema que contiene ste obj, trabajo dificl
						o.setAdditionalColor(g.mycolor);
					//} else {
						//obtenemos el tipo del que selecciono antes, y hacemos checkhit imaginando que esta 
						//en la coord donde hizo el 2º click (entonces nos ahorramos de intercambiar 2veces en caso de error
						Gem g1=null;
						for (Gem gt: grid){
							if (gt.obj == selected){
								g1 = gt;
								break;
							}
						}
						
						int limits[] = new int[6];
						checkHit(x, y, g1.type, limits);
						if (limits[0]>1 || limits[1]>1 ){
							Logger.log("WE HAVE A WINNER!");
							int typeaux = g.type;
							g.reset(g1.type);
							g1.reset(typeaux);
							check.restart();//dejamos que lo encuentre el checker para tener un feedback visual
						}
						//logica deintercambio
							selected = null;
					//}	
				}
			}
			return true;
		}//fin move
		return false;
	}
	
	public void onDrawFrame(FrameBuffer fb) {
		if ( check.status == check.FOUND){
			Log.d(tag, "Encontre uno! lo voy a matar");
			handleHit(check.x, check.y, check.limits);
			//killV(check.i, check.j, check.j);//esto es mas eficiente porque no reemplaza todas las linesa, pero hay que sacarlo para permitir eliminacion en cadena
			check.restart();
		}
		/*if (stat==check.NOT_FOUND)
			Log.d("level", "no se encontro nada, lo dejamos pausado");*/
		
		world.renderScene(fb);
		world.draw(fb);
		if (check.status == check.STOPPED)
			check.start(); //esperamos luego del 1º render para checkear porque sino hace cualquier cosa...
		return;
	}
	public boolean checkChange(int x1, int y1, int x2, int y2){
		return true;
	}
	public void killH(int x1, int x2, int y){
		for (int i= x1; i<=x2; i++)
			killV(i, y, y);
	}
	public void killV(int x, int y1, int y2){
		int nj = y1- (y2-y1)-1;//truco copiar para arriba salteandose los que si o si mueren
		int ntipo;
		for (int j= y2; j>=0 ; j--){
			
			if(nj<0)
				ntipo = r.nextInt(Gem.maxType);
			else
				ntipo = grid[x+(nj*gridw)].type;

			grid[x+(j*gridw)].reset(ntipo);
			
			nj--;
		}
	}
	public void handleHit(int x, int y, int limits[]){
		//mata todas las gemas que coincidan
		//x, y, donde se da la coincidencia, limits los limits que da el checkhit
		//hay un bug con las cruzadas
		if (limits[0]>1){
			Logger.log(String.format("Hit horizontal %sx%s (%s, %s), (%s, %s)", limits[0], limits[1], limits[2], limits[3], limits[4], limits[5]));		
			killH(limits[2], limits[3], y);
		}
		if (limits[1]>1){
			Logger.log(String.format("Hit vertical %sx%s (%s, %s), (%s, %s)", limits[0], limits[1], limits[2], limits[3], limits[4], limits[5]));		
			killV(x, limits[4], limits[5] );
		}
	}
	public void checkHit(int x , int y, int type, int limits[]){
		//todo recibir tipo por parametro
		//todo manejar nulls para grids amorfas
			//devuelve en limits : canthoriz, cantvert, x1, x2, y1, y2, en caso de error solo esta definidas las cants 
			int j, pyi, pyf, pxi, pxf;
			int off  = (y*gridw);//lo ponemos aca para no multiplicar tanto
			
			int i = x +off;
			
			limits[0]=0;
			limits[1]=0;
			
			if (i>=grid.length) return;
			
			Gem g = grid[i];
			
			//now well check horizontally
			//to the left
			Gem g2; //temp
			pxi = x;
			for (j=x-1; j>=0; j--){
				g2 = grid [j + off];
				if (g2.type != type) {
					break;
				}
				pxi = j;
			}
			
			//right
			pxf = x;
			for (j=x+1; j<gridw; j++){
				g2 = grid [j +off];
				if (g2.type != type) {
					break;
				}
				pxf = j;
			}
			
			//top
			pyi=y;
			for (j=y-1; j>=0; j--){
				g2 = grid [x +(j*gridw)];
				if (g2.type != type) {
					break;
				}
				pyi=j;
			}
			
			//down
			pyf=y;
			for (j=y+1; j<gridh; j++){
				g2 = grid [x +(j*gridw)];
				if (g2.type != type) {
					break;
				}
				pyf=j;
			}
			
			//Log.d("hitc", String.format("H(%s, %s), (%s, %s)", pxi, pxf, pyi, pyf));
			limits[0] = pxf-pxi;
			limits[1] = pyf-pyi;
			
			limits[2] = pxi;
			limits[3] = pxf;
			
			limits[4] = pyi;
			limits[5] = pyf;
			return;
		}
		
}
