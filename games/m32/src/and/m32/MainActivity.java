package and.m32;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.threed.jpct.*;
import com.threed.jpct.util.BitmapHelper;
import com.threed.jpct.util.MemoryHelper;
import java.lang.reflect.Field;
import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity
{

	// Used to handle pause and resume...
	private static MainActivity master = null;

	private GLSurfaceView mGLView;
	private MyRenderer renderer = null;
	private FrameBuffer fb = null;
	private RGBColor back = new RGBColor(50, 50, 100);

	private int fps = 0;

	Level level;
	public static void loadTexture(int resid, int w, int h, String textname){
		Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(master.getResources().getDrawable(resid)), w, h ));
		TextureManager.getInstance().addTexture(textname, texture);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		Logger.log("onCreate");

		if (master != null) {
			copy(master);
		}

		super.onCreate(savedInstanceState);
		//los de google son TERRIBLE PILLOH si le dejamos la barra de titulos, en las coordenadas del onclic nos devuelve la putabarra
		requestWindowFeature(Window.FEATURE_NO_TITLE);
     // making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mGLView = new GLSurfaceView(getApplication());

		mGLView.setEGLConfigChooser(
			new GLSurfaceView.EGLConfigChooser() {
				public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
					// Ensure that we get a 16bit framebuffer. Otherwise, we'll fall
					// back to Pixelflinger on some device (read: Samsung I7500)
					int[] attributes = new int[] { EGL10.EGL_DEPTH_SIZE, 16, EGL10.EGL_NONE };
					EGLConfig[] configs = new EGLConfig[1];
					int[] result = new int[1];
					egl.eglChooseConfig(display, attributes, configs, 1, result);
					return configs[0];
				}
			}
		);

		renderer = new MyRenderer();
		mGLView.setRenderer(renderer);
		setContentView(mGLView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();//todo, matar threads
		try {
			level.check.cancel();
			level.check.join();
		} catch (InterruptedException ex) {			
		}
	}

	private void copy(Object src) {
		try {
			Logger.log("Copying data from master Activity!");
			Field[] fs = src.getClass().getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				f.set(this, f.get(src));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent me) {
		boolean rt = level.onTouchEvent(me);
		return rt || super.onTouchEvent(me); //pequeño truco, depende de la evaluacion rapida
	}

	protected boolean isFullscreenOpaque() {
		return true;
	}

	class MyRenderer implements GLSurfaceView.Renderer {

		private long time = System.currentTimeMillis();

		public MyRenderer() {
		}

		public void onSurfaceChanged(GL10 gl, int w, int h) {
			if (fb != null) {
				fb.dispose();
			}
			fb = new FrameBuffer(gl, w, h);

			if (master == null) {//supongo que es un create
				Logger.log("Saving master Activity!");
				master = MainActivity.this;
				level = new Level1();
				MemoryHelper.compact();				
			}
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		}

		public void onDrawFrame(GL10 gl) {
			fb.clear(back);
			level.onDrawFrame(fb);
			fb.display();
			/*long ctime = System.currentTimeMillis();
			if ( ctime- time >= 1000) {
				Logger.log(fps + "fps");
				fps = 0;
				time = ctime;
			}
			fps++;*/
		}
	}
	public static FrameBuffer getFb(){
			return master.fb;
	}

}
