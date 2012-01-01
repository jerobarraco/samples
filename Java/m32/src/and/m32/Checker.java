package and.m32;

import and.model.Gem;
import android.util.Log;

public class Checker extends Thread {
	private Level l;
	
	public int status = 0;//0 nada, 1 working, 2 found
	public boolean matchFound;
	public boolean possibleMove = true; //siempre se puede seguir hasta que se diga lo contrario
	public int x, y, w, h , s, limits[], tgrid[], matchs[];
	public static final int STOPPED=0;
	public static final int WORKING=1;
	public static final int CANCELLED=2;
	public static final int RESTART=3;
	private static final String tag="checker";
	public Checker (Level owner){
		super();
		this.l = owner;
		status = STOPPED;
		limits = new int[6];
		tgrid = owner.tgrid;//ojo que mantiene la referencia!
		w = l.gridw;
		h = l.gridh;
		s = l.gSize;
		matchs = new int[s];
		matchFound= false;
	}
	
	@Override
	public void run(){
		status = WORKING;
		while (true){
			if (status == WORKING){
				if (checkMatchs() && (status ==WORKING)){
								//ponemos el matchFound aca para q no tomen el true a medio de camino
						matchFound =true;
				}else{
					//una vez que terminamos con los matches verificamos si es valido
					//si hay matches no nos gastamos porque los van a matar
					if(checkSwap()){
						Log.d(tag, "aun queda");
					}else{
						Log.d(tag, "ya no queda");
					}
				}
				if (status == WORKING){ //si nos resetearon tenemos que permitirlo //suele pasar porque el checkswap suele ser lento
					status=CANCELLED;
				}
			}
			else if (status == RESTART){
				status = WORKING;
			}
			//Encontrado, no encontrado, pausado, detenido, todo menos cancelado y working
			try {
				sleep(500);
			} catch (InterruptedException ex) {
			}
		}
	}
	public void search(){
		Log.d(tag, "Working!");
		matchFound= false;
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
				synchronized(this){
					Log.d(tag, String.format("checking %s, %s", x, y));
					//comparamos contra el tipo actual en la celda
					l.checkHit( x, y, limits);
				}
				if (limits[0]>1 || limits[1]>1){
					Log.d(tag, String.format("ENCONTRADO EN %s, %s", x, y));
					return ;
				}
			}
		}
		Log.d(tag, "No encontre nada, me voy a dormir un rato");
	}
	public void cancel(){
		matchFound = false;
		status = CANCELLED;
	}
	public void work(){
		matchFound = false;
		status = WORKING;
	}
	public void restart(){
		matchFound = false;
		if(status==WORKING)
			status = RESTART;
		else
			status = WORKING;
	}
	private boolean checkMatchs(){
		boolean hit = false;
		int i;
		Log.d(tag, "Buscando matches");
		//tgrid = l.tgrid;//ojo que mantiene la referencia!
		for (i = 0; i<matchs.length; i++) matchs[i]=0;
		
		//por como caen las fichas es mejor empezar desde abajo :)
		for (y = h-1; y>=0; y--){		//notar que escaneamos horizontalmente
			for (x = w-1; x>=0; x--){
				try {
					sleep(10);
				} catch (InterruptedException ex) {
				}
				if (status !=WORKING) {	
					return hit;
				}
				if(checkMatch(x, y, tgrid[x+(y*w)])){
					Log.d(tag, String.format("Found match at %s %s", x, y));
					hit = true;
				}
			}
		}
		return hit;
	}
	private boolean checkMatch(int x, int y, int type){
		//esto checkea desde atras para adelante, asi que mejor recorrer de atras al principio
		matchFound = false;//para que no haya un falso positivo hasta que terminemos
		int yd=0;
		int xd=0;
		int y2, x2;
		int yoff = y*w;
		boolean hit=false;
		//checkeamos para la izq y arriba nomas
		//Log.d(tag, String.format("Check %s, %s", x, y));
		for (y2 = y-1; y2>=0; y2--){
			if (tgrid[x + y2*w] != type)
				break;
			yd++;
		}
		
		for (x2 = x-1; x2>=0; x2--){
			if (tgrid[x2 + yoff] != type)
				break;
			xd++;
		}
		
		//ponemos los hits
		if (yd>1){
			hit = true;
			for (y2 = y; y2>=y-yd; y2--){
				matchs[x + y2*w] =1;
			}
		}
		
		if (xd>1){
			hit =true;
			for (x2 = x; x2>=x-xd; x2--){
				matchs[x2 + yoff] =1;
			}
		}
		return hit;
	}

	private boolean checkSwap() {
		//Log.d(tag, "Viendo si se puede seguir");
		int nx, ny;
		for ( y = 0; y<h; y++){
			for ( x = 0; x < w; x++  ){
				//checkeamos si es swapeable solo para la derecha y abajo
				//porque si cambiamos a <-> b es al pedo ver b<->a
				try {
						sleep(100);
					} catch (InterruptedException ex) {
				}
				if (status !=WORKING) {
					Log.d(tag, "NOT WORKNIG ME Voy");
						return true;
				}
				
				synchronized(this){
					nx = x+1;
					ny = y+1;
					
					if (nx<w){
						if (l.swapable(x, y,  nx, y)){
							//Log.d(tag, String.format("Swapable 1 (%s,%s)<->(%s,%s)", x, y,  nx, y));
							return true;
						}					
					}
					if (ny<h){
						if (l.swapable(x, y, x, ny)){
								//Log.d(tag, String.format("Swapable 2 (%s,%s)<->(%s,%s)", x, y, x, ny));
								return true;
						}		
					}
				}
			}//fr x
		}//for y
	return false;
	}
}
