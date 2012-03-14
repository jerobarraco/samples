package and.and.model;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Explosion {

	public static final int STATE_ALIVE 	= 0;	// at least 1 particle is alive
	public static final int STATE_DEAD 		= 1;	// all particles are dead

	private Particle[] particles;			// particles in the explosion
	private int x, y;						// the explosion's origin
	private int size;						// number of particles
	private int state;						// whether it's still active or not
	private static final String TAG=Explosion.class.getSimpleName();
	public Explosion(int particleNr, int x, int y) {
		Log.d(TAG, "Explosion created at " + x + "," + y);
		this.state = STATE_DEAD;
		this.particles = new Particle[particleNr];
		//Extremely slow use new for
		
		for (int i = 0; i < this.particles.length; i++) {
			Particle p = new Particle(x, y);
			this.particles[i] = p;
		}
		this.size = particleNr;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	// helper methods -------------------------
	public boolean isAlive() {
		return this.state == STATE_ALIVE;
	}
	public boolean isDead() {
		return this.state == STATE_DEAD;
	}

	public void update() {
		if (this.state != STATE_DEAD) {
			boolean isDead = true;
			for (int i = 0; i < this.particles.length; i++) {
				if (this.particles[i].isAlive()) {
					this.particles[i].update();
					isDead = false;
				}
			}
			if (isDead)
				this.state = STATE_DEAD; 
		}
	}
	
	public void update(Rect container) {
		if (this.state != STATE_DEAD) {
			boolean isDead = true;
			for (int i = 0; i < this.particles.length; i++) {
				if (this.particles[i].isAlive()) {
					this.particles[i].update(container);
//					this.particles[i].update();
					isDead = false;
				}
			}
			if (isDead)
				this.state = STATE_DEAD; 
		}
	}

	public void draw(Canvas canvas) {
		for (Particle p : this.particles){
			if (p.isAlive()) p.draw(canvas);
		}
	}
	public void reset(int x, int y) {
		this.state = Particle.STATE_ALIVE;
		this.x = x;
		this.y = y;
		for (Particle p : this.particles){
			p.reset(x, y);
		}
	}
}