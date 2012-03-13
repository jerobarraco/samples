package and.m32;

import and.model.Gem;
import android.util.Log;
import android.view.MotionEvent;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Interact2D;
import com.threed.jpct.Light;
import com.threed.jpct.Logger;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;
import java.util.Random;

public class Level {
	protected World world; 
	protected Light sun;
	protected Object3D selected;
	public Gem grid[]; //do not modiy plz
	public int tgrid[];
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
		tgrid = new int[gSize];
		check = new Checker(this);		
	}
	public Object[] getObjectAt(float x, float y){
		FrameBuffer fb = MainActivity.getFb();
		SimpleVector dir= Interact2D.reproject2D3D(world.getCamera(), fb, Math.round(x), Math.round(y)).normalize();
		return world.calcMinDistanceAndObject3D(world.getCamera().getPosition(), dir, 10000);
	}
	
	public boolean onTouchEvent(MotionEvent me) { //especie de mainloop :D
		float mx, my;
		mx = me.getX();
		my = me.getY();
		if (me.getAction() == MotionEvent.ACTION_DOWN) {			
			Object3D o = (Object3D) getObjectAt(mx, my)[1];
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
						g.deselect();
					//} else {
						//obtenemos el tipo del que selecciono antes, y hacemos checkhit imaginando que esta 
						//en la coord donde hizo el 2º click (entonces nos ahorramos de intercambiar 2veces en caso de error
						Gem g1=null;
						int ni;
						for (ni = 0 ; ni< gSize; ni++){
							g1= grid[ni];
							if (g1.obj==selected) break;
						}
						g1.deselect();//deselecciona visualmente
						//logica
						selected = null;
						int nx= ni%gridw;
						int ny= ni/gridw;
						if (swapable(x, y, nx, ny )){
							Logger.log("WE HAVE A WINNER!");//todo check distancia
							int typeaux = g.type;
							g.reset(g1.type);
							g1.reset(typeaux);
							
							tgrid[i] = tgrid[ni];
							tgrid[ni]= typeaux;
							check.restart();//dejamos que lo encuentre el checker para tener un feedback visual
						}else{
							Logger.log("Eso no se puede cambiar"); 
						}
						
					//}	
				}
			}
			return true;
		}//fin move
		return false;
	}
	
	public void onDrawFrame(FrameBuffer fb) {
		world.renderScene(fb);
		world.draw(fb);
		
		synchronized(check){
			//no se si el sycnh este sirve pa  algo o alerda las cosas nomas
			//pero mejor porque el kilmatches cambia los grids. igual no importaria porque el estado no es working
			int status = check.status;
			if (status == check.STOPPED)
				check.start();
			else
				if (check.matchFound){
					Log.d(tag, "Hay matches! A matarlos!");
					killMatches();
				}  //esperamos luego del 1º render para checkear porque sino hace cualquier cosa...
		}
		return;
	}
	@Deprecated
	public void killH(int x1, int x2, int y){
		for (int i= x1; i<=x2; i++)
			killV(i, y, y);
	}
	@Deprecated
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
	@Deprecated
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
	public boolean swapable(int x1, int y1, int x2, int y2){
		//devuelve true si los items al intercambiarlos da exito
		int lim[] = new int[6];
		boolean hit = false;
		//esto es peligroso , sobretodo para el checker, hay que cambiar temporalmente la matriz de tipos porque sino nos pisamos solos
		int i1, i2, taux;
		i1 = x1+(y1*gridw);
		i2 = x2+(y2*gridw);
		
		taux = tgrid[i1];
		tgrid[i1]= tgrid[i2];
		tgrid[i2]= taux;
		
		checkHit(x1, y1, lim);
		if ((lim[0]>1) || (lim[1]>1)){
			Log.d(tag, String.format("Swapable A (%s,%s)<->(%s,%s)", x1, y1, x2, y2));
			//Log.d(tag, String.format("Hit (%s,%s), (%s,%s) (%s,%s)", lim[0], lim[1], lim[2], lim[3], lim[4], lim[5] ));
			hit= true;
		}
		
		if (!hit){
			checkHit(x2, y2, lim);
			if ((lim[0]>1) || (lim[1]>1)){
				Log.d(tag, String.format("Swapable B (%s,%s)<->(%s,%s)", x1, y1, x2, y2));
				//Log.d(tag, String.format("Hit (%s,%s), (%s,%s) (%s,%s)", lim[0], lim[1], lim[2], lim[3], lim[4], lim[5] ));
				hit= true;
			}
		}
		//swap back
		taux = tgrid[i1];
		tgrid[i1]= tgrid[i2];
		tgrid[i2]= taux;
		
		
		return hit;
		
	}
	public void checkHit(int x , int y, int limits[]){
		//actua sobre la grid de tipos asi que no le puedo pasar un tipo fake o se pisa
		//todo manejar nulls para grids amorfas
			//devuelve en limits : canthoriz, cantvert, x1, x2, y1, y2, en caso de error solo esta definidas las cants 
			int j, pyi, pyf, pxi, pxf, type;
			int off  = (y*gridw);//lo ponemos aca para no multiplicar tanto
			type = tgrid[x+off];
			limits[0]=0;
			limits[1]=0;
						
			//now well check horizontally
			//to the left
			pxi = x;
			for (j=x-1; j>=0; j--){
				if (tgrid [j + off] != type) {
					break;
				}
				pxi = j;
			}
			
			//right
			pxf = x;
			for (j=x+1; j<gridw; j++){
				if (tgrid [j +off] != type) {
					break;
				}
				pxf = j;
			}
			
			//top
			pyi=y;
			for (j=y-1; j>=0; j--){
				if (tgrid[x +(j*gridw)] != type) {
					break;
				}
				pyi=j;
			}
			
			//down
			pyf=y;
			for (j=y+1; j<gridh; j++){
				if (tgrid[x +(j*gridw)] != type) {
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

	private void killMatches() {
		check.cancel();//frozamos check y evitamos que nos vuelva a dar un matchfound positivo
		int matchs[] = check.matchs;
		int nj, ntipo;
		//este conviene recorrerlo bien, sino nos pisamos
		
		for (int i = 0;i<gSize; i++){
			if (matchs[i]==1){
				Logger.log(String.format("Matando %s (%s)", i, tgrid[i]));
				for (int j = i; j>=0; j-=gridw){
					nj = j - gridw;//-1*w o sea la fila superior
					if(nj<0)
						ntipo = r.nextInt(Gem.maxType);
					else
						ntipo =  tgrid[nj];
					tgrid[j] = ntipo;
					grid[j].reset(ntipo);
				}
			}
		}
		check.restart();
	}
}
