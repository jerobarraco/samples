package and.m32;

import and.model.Gem;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

public class TaskCheckMatch  extends AsyncTask<Void, Integer, Integer>{
	@Override
	protected Integer doInBackground(Void... arg0) {
//		//devuelve el numero en la grilla que lanza un check
//		if (arg0.length>1 ) Log.e(this.getClass().getSimpleName(), "me pasaron dos parametros!");
//		l = arg0[0];
//		int limits[] = new int[6];
//		int w, h, s;
//		w= l.gridw;
//		h = l.gridh;
//		s = l.gSize;
//		Gem grid[] = l.grid;
//		for (int i = 0; i<w; i++){
//			for (int j = 0; j<h; j++){
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException ex) {
//				}
//				
//				synchronized(l){
//					l.checkHit(i, j, limits);
//				}
//				if (limits[0]>1 || limits[1]>1){
//					return i+(j*w);
//				}
//			}
//		}	
//		Looper.loop();
		return -1;//-1 means que no hay ningun elemento que haga hit
	}
/*
	 * //The three types used by an asynchronous task are the following:
//Params, the type of the parameters sent to the task upon execution.
//Progress, the type of the progress units published during the background computation.
//Result, the type of the result of the background computation.
	 * onPreExecute(), invoked on the UI thread immediately after the task is executed. This step is normally used to setup the task, for instance by showing a progress bar in the user interface.
doInBackground(Params...), invoked on the background thread immediately after 
	 * onPreExecute() finishes executing. This step is used to perform background computation that 
	 * can take a long time. The parameters of the asynchronous task are passed to this step. 
	 * The result of the computation must be returned by this step and will be passed back to the 
	 * last step. This step can also use publishProgress(Progress...) to publish one or more units of 
	 * progress. These values are published on the UI thread, in the onProgressUpdate(Progress...) step.
	 * 
onProgressUpdate(Progress...), invoked on the UI thread after a call to publishProgress(Progress...). The timing of the execution is undefined. 
onPostExecute(Result), invoked on the UI thread after the background computation finishes. The result of the background computation is passed to this step as a parameter.*/
     
}
//Regardless of whether or not you use AsyncTask, always remember these two rules about the single thread model: 
//Do not block the UI thread, and 
//Make sure that you access the Android UI toolkit only on the UI thread.

