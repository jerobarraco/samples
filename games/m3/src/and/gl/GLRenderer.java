package and.gl;

import and.model.Cube;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.opengl.GLUtils;

public class GLRenderer implements Renderer {
	private Triangle    triangle;   // the triangle to be drawn		
	private Square      square;     // the square
	private Stage stage;
	private Cube cube = new Cube(1, 1, 1);
	
	private static Context     context;
	private static GL10 gl;

	public GLRenderer(Context ctx) {
				this.context = ctx;
				stage = new Stage1();
				cube = new Cube(1, 1, 1);
				//cube.rx = 45;
				//cube.ry = 45;
  }
	public static int loadTexture (int id){
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.kafx);
		int textures[]= new int[1];//ineficiente al mango
		// generate one texture pointer
		gl.glGenTextures(1, textures, 0);
		// ...and bind it to our array
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		// create nearest filtered texture
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

		// Use Android GLUtils to specify a two-dimensional texture image from our bitmap
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		// Clean up
		bitmap.recycle();
		return textures[0];
	}
					
	@Override
	public void onDrawFrame(GL10 gl) {
		// clear Screen and Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
 
		// Reset the Modelview Matrix
		gl.glLoadIdentity();
 
		// Drawing
		gl.glTranslatef(0.0f, 0.0f, -5.0f);		// move 5 units INTO the screen
												// is the same as moving the camera 5 units away
		//triangle.draw(gl);						// Draw the triangle
		//gl.glRotatef(rotate, 1f, 0.5f, 0f);
		//rotate+=0.2;
		square.draw(gl);
		cube.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		this.gl = gl;
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}
 
		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix
 
		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
 
		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
		
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		this.gl = gl;
		
		gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do

		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		
		this.square = new Square();
	}
}