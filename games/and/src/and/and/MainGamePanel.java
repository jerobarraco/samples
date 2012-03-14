package and.and;

import and.and.model.Droid;
import and.and.model.ElaineAnimated;
import and.and.model.Explosion;
import and.and.model.components.Speed;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {
	
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	private MainThread thread;
	
	private Droid droid;
	// the fps to be displayed
	private String avgFps="";
	private final ElaineAnimated elaine;
	private Explosion[] explosions;
	private static final int EXPLOSION_SIZE = 200;
	public void setAvgFps(String avgFps) {
	    this.avgFps = avgFps;
	}

	public MainGamePanel(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);
		
		// create droid and load bitmap
		droid = new Droid(BitmapFactory.decodeResource(getResources(), R.drawable.droid_1), 50, 50);
		elaine = new ElaineAnimated(
				BitmapFactory.decodeResource(getResources(), R.drawable.walk_elaine)
				, 10, 50	// initial position
				, 30, 47	// width and height of sprite
				, 5, 5);	// FPS and number of frames in the animation
		thread = new MainThread(getHolder(), this);
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
 }

 @Override
 public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	 
 }

 @Override
 public void surfaceCreated(SurfaceHolder holder) {
	 explosions = new Explosion[10];
	 //todo debug si se puede hacer new con el nuevo for
		for (int i = 0; i < explosions.length; i++) {
			//explosions[i] = null;
			explosions[i] = new Explosion(EXPLOSION_SIZE, 0, 0);
		}
	 thread.setRunning(true);
	 thread.start();
 }

 @Override
 public void surfaceDestroyed(SurfaceHolder holder) {
	boolean retry = true;
  while (retry) {
   try {
    thread.join();
    retry = false;
   } catch (InterruptedException e) {
    // try again shutting down the thread
   }
  }
 }

 @Override
 public boolean onTouchEvent(MotionEvent event) {
  if (event.getAction() == MotionEvent.ACTION_DOWN) {
   // delegating event handling to the droid
   droid.handleActionDown((int)event.getX(), (int)event.getY());

   // check if in the lower part of the screen we exit
   if (event.getY() > getHeight() - 50) {
    thread.setRunning(false);
    ((Activity)getContext()).finish();
   } else {
    Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
   }
	 
	 for(Explosion ex: explosions){
		 if (ex.isDead()){
			 ex.reset( (int)event.getX(), (int)event.getY());
			 break;
		 }
	 }
	 
  } if (event.getAction() == MotionEvent.ACTION_MOVE) {
		 // the gestures
		 if (droid.isTouched()) {
			// the droid was picked up and is being dragged
			droid.setX((int)event.getX());
			droid.setY((int)event.getY());
		 }
  } 
	if (event.getAction() == MotionEvent.ACTION_UP) {
   // touch was released
   if (droid.isTouched()) {
    droid.setTouched(false);
   }
  }
	
  return true;
 }

 @Override
	protected void onDraw(Canvas canvas) {
		 canvas.drawColor(Color.BLACK);
		droid.draw(canvas);
		elaine.draw(canvas);
		for(Explosion e: explosions){
			if (e.isAlive()) e.draw(canvas);
		}
		// display border
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);
		canvas.drawLines(new float[]{
				0,0, canvas.getWidth()-1,0, 
				canvas.getWidth()-1,0, canvas.getWidth()-1,canvas.getHeight()-1, 
				canvas.getWidth()-1,canvas.getHeight()-1, 0,canvas.getHeight()-1,
				0,canvas.getHeight()-1, 0,0
		}, paint);
		displayFps(canvas, avgFps); 
	}

	public void update() {

		elaine.update(System.currentTimeMillis());

		// check collision with right wall if heading right
		if (droid.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
				&& droid.getX() + droid.getBitmap().getWidth() / 2 >= getWidth()) {
			droid.getSpeed().toggleXDirection();
		}
		// check collision with left wall if heading left
		if (droid.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
				&& droid.getX() - droid.getBitmap().getWidth() / 2 <= 0) {
			droid.getSpeed().toggleXDirection();
		}
		// check collision with bottom wall if heading down
		if (droid.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
				&& droid.getY() + droid.getBitmap().getHeight() / 2 >= getHeight()) {
			droid.getSpeed().toggleYDirection();
		}
		// check collision with top wall if heading up
		if (droid.getSpeed().getyDirection() == Speed.DIRECTION_UP
				&& droid.getY() - droid.getBitmap().getHeight() / 2 <= 0) {
			droid.getSpeed().toggleYDirection();
		}
		// Update the lone droid
		droid.update();
		
		for (Explosion e: explosions){
			if(e.isAlive()) e.update(getHolder().getSurfaceFrame());
		}
		
		
	}
	private void displayFps(Canvas canvas, String fps) {
	    if (canvas != null && fps != null) {
	        Paint paint = new Paint();
	        paint.setARGB(255, 255, 255, 255);
	        canvas.drawText(fps, this.getWidth() - 50, 20, paint);
	    }
	}
}
