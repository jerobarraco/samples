
package com.moongate.common;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.math.Vector3;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Stage implements Screen, InputProcessor{
	public Game parent;
	public SpriteBatch batch, hudBatch;
	public OrthographicCamera camera;
	float camspeed = 0.1f;
	//locking position of the camera, in fact is an offset
	public Vector3 offset = new Vector3(200, 100, 0);
	AnimatedSprite following = null;
	public SpriteCache cache;
	public Stage(Game parent){
		this.parent = parent;
		camera = new OrthographicCamera();
	  camera.setToOrtho(false, 480, 320);
		//center the camera, to see "everything"
		//camera.translate(240, 160);
		
		batch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		//set the initial projection of the hudbatch to the normal viewport
		hudBatch.setProjectionMatrix(camera.combined);
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCatchBackKey(true);
	}
	
	public void setStage(Class s){		
		try {
			//desnt work
			//Constructor[] construct = s.getConstructors();
			Constructor c = s.getConstructor(Game.class);
			System.out.println(c.toString()); //public test2.server.Outer$Inner(test2.server.Outer,java.lang.Integer)             Object i = construct[0].newInstance(null, new Integer(43));
			//System.out.println(construct[0].toString()); //public test2.server.Outer$Inner(test2.server.Outer,java.lang.Integer)             Object i = construct[0].newInstance(null, new Integer(43));
			//Stage ns = (Stage) construct[0].newInstance(this.parent);
			Stage ns = (Stage) c.newInstance(this.parent);
			this.parent.setScreen(ns);
		} catch (NoSuchMethodException ex) {
			Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InvocationTargetException ex) {
			Logger.getLogger(Stage.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	//final will disable overriding (could be an overkill!)
	@Override
	public final void render(float d){
		//update..
		this.update(d);
		
		if (this.following != null){
			camera.position.lerp(following.getOVector().add(offset), camspeed);
			//we have to update the camera to set the changes.
			//i assume that updating takes time, so we must do it only once (and whenever we've chaned it
			
		}
		camera.update();
		//render (skipframes if necesary)
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		
		
		this.render();
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		this.render(batch);
		batch.end();	
		//hud
		//we need to call them in separated functions
		hudBatch.begin();
		this.renderHUD(hudBatch);
		hudBatch.end();	
	}
	public void follow(AnimatedSprite sprite){
		this.following = sprite;
	}

	@Override
	public boolean keyDown(int keycode){
		return false;
		//asdf
	}
	//TODO allow skipframes
	public abstract void update(float d);
	public abstract void render();
	public abstract void render(SpriteBatch sb);
	public abstract void renderHUD(SpriteBatch hsb);
	
}
