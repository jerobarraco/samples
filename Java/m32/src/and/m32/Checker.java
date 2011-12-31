package and.m32;

import and.model.Gem;
import android.util.Log;

public class Checker extends Thread {
	private Level l;
	
	public int status = 0;//0 nada, 1 working, 2 found
	public int findex;
	public int x, y, limits[];
	public static final int STOPPED=0;
	public static final int WORKING=1;
	public static final int FOUND=2;
	public static final int NOT_FOUND=3;
	public static final int CANCELLED=4;
	public static final int RESTART=4;
	private static final String tag="checker";
	public Checker (Level owner){
		super();
		this.l = owner;
		findex = -1;
		status = STOPPED;
		limits = new int[6];
	}
	
	@Override
	public void run(){
		status = WORKING;
		while (status != CANCELLED){
			if (status == WORKING)
				search();
			else if (status == CANCELLED) {
				Log.d(tag, "ABORTADO");
				return;
			}
			//Encontrado, no encontrado, pausado, detenido, todo menos cancelado y working
			try {
				sleep(1000);
			} catch (InterruptedException ex) {
			}
		}
	}
	public void search(){
		Log.d(tag, "Working!");
		int w, h;
		w = l.gridw;
		h = l.gridh;
		Gem grid[] = l.grid;//cacheamos
		//por como caen las fichas es mejor empezar desde abajo :)
		for (y = h-1; y>=0; y--){		//notar que escaneamos horizontalmente
			for (x = w-1; x>=0; x--){
					//todo que cuando encuentre uno, mueva los indices abajo a la derecha (todo, evaluar si la logica estabien)
				try {
					sleep(25);//en una matriz 5*5 tardaria 25 segundos en recorrerla
				} catch (InterruptedException ex) {
				}
				if (status == CANCELLED) {
					Log.d("checker", "ABORITNG");
					return;
				}
				if (status == RESTART){
					Log.d("checker", "RESTARTING");
					status = WORKING;
					x = 0;
					y = 0;
				}
				synchronized(l){
					Log.d(tag, String.format("checking %s, %s", x, y));
					//comparamos contra el tipo actual en la celda
					l.checkHit( x, y, grid[x + (y*w)].type, limits);
				}
				if (limits[0]>1 || limits[1]>1){
					Log.d(tag, String.format("ENCONTRADO EN %s, %s", x, y));
					findex = x+(y*w);
					status = FOUND;
					return ;
				}
			}
		}
		Log.d(tag, "No encontre nada, me voy a dormir un rato");
		status = NOT_FOUND;
	}
	public void cancel(){
		status = CANCELLED;
	}
	public void work(){
		status = WORKING;
	}
	public void restart(){
		if (status == WORKING)
			status = RESTART;
		else
			status = WORKING;
	}
}
