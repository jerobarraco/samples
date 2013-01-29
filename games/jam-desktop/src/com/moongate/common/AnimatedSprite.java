package com.moongate.common;
/* Inspired by the library made by Dennis van Haazel (info@dvh-productions.nl)
 * Created by Jerónimo Barraco Mármol (jerobarraco at yahoo dot com dot ar) @ MoonGate.com.ar
 * GPL 3
 */
//TODO onStop abstract
//TODO stop on duration == 0
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.moongate.common.Animation.Frame;

public class AnimatedSprite extends Sprite
{
	public enum PlayType {NORMAL, REVERSED, RANDOM}
	public enum LoopType {NORMAL, PINGPONG}
	protected TextureRegion region;
	
	Animation animation;
	Frame frame;
	Atlas atlas;
	
	double time;//avoid changing the time in the animation
	protected boolean isPlaying;
	protected boolean isLooping;
	protected boolean isReversed;

	protected PlayType playType;
	protected LoopType loopType;
	public boolean centered;
	public boolean alive = true;
	public int cur_frame, state;
	
	public final boolean isLooping(){
		 return isLooping;
	}
	public final void loop(final boolean loop){
		this.isLooping = loop;
	}
	
	public void dispose(){
		
		//todo dispose textures
	}
	
	public void kill(){
		this.alive = false;
	}
	public boolean isAlive(){
		return this.alive;
	}
	//overrideable
	public boolean isActive(){
		//returns true if the sprite is hittable (ie, not dying)
		//this should be 
		return this.alive;
	}
	//overrideable
	public void die(){
		//tell the enemy to die, this is not the same as killing it. 
		//it sets the state to an animation that is dying, the sprite shouldnt interact with other sprites
		//and finally it should kill himself
		this.kill();
	}

	public void reset(float x, float y){
		this.setOX(x);
		this.setOY(y);
		this.alive = true; 
		this.play(this.state);
	}
	
	@Override
	public void draw(SpriteBatch b){
		if (alive){
			super.draw(b);
		}
	}

	@Override 
	public void draw(SpriteBatch b, float a){
		if (!alive){
			return;
		}
		Color color = getColor();
		float oldAlpha = color.a;
		color.a *= a;
		setColor(color);
		//calling super.draw is the only way to avoid circular reference
		super.draw(b);
		color.a = oldAlpha;
		setColor(color);
	}
	public boolean collide(AnimatedSprite other){
		return this.getBoundingRectangle().overlaps(other.getBoundingRectangle());
	}
   /* Initialize */ 
   private void Init(){
			isPlaying = false;
			isLooping = false;
			loopType = LoopType.NORMAL;
			isReversed = false;
   }
   public AnimatedSprite(Atlas atlas){
		 this.atlas = atlas;
		 Init();
	 }
	 public void play(final int animation){
		 this.state = animation;
		 this.animation = this.atlas.animations.get(state);
		 this.time = 0;
		 this.isPlaying = true;
		 setFrame(0);
	 }
	public void pause(){
		this.isPlaying = ! this.isPlaying;
	}
	
	public void setFrame(final int fn){
		this.cur_frame = fn;
		//undo the previous offset
		float x=0, y=0;
		if (this.frame!= null){
			 x = this.getOX();
			 y = this.getOY();
		}
		//todo pause if duration == 0
		this.frame = this.animation.frames.get(fn);
		this.time += this.frame.duration;
		
		this.setRegion(this.frame.text);
		if (this.frame.rotate){
			rotate90(true);
		}
		setOrigin(this.frame.originX, this.frame.originY);
		//set bounds also sets position.... 
		super.setSize(this.frame.width, this.frame.height);
		setOX(x);
		setOY(y);
		this.setColor(1, 1, 1, 1);	 
	 }
	//overriding the sets is an error.
	//two consecutive calls to set will add the offset... 
	//the only real way is to set the value with the offset once, and get from there.
	
	//returns the position of the left bottom
	public float getOX(){
		float r = this.getX()-this.frame.offX;
		if (centered) {r+=this.frame.originX;}
		return r;
	}
	
	public float getOY(){
		float r = this.getY()-this.frame.offY;
		if (centered) {r+=this.frame.originY;}
		return r;
	}
	
	public void setOX(final float x){
		float r = x+this.frame.offX;
		if (centered) {r-=this.frame.originX;}
		super.setX(r);
	}
	public void setOY(final float y){
		float r = y+this.frame.offY;
		if (centered) {r-=this.frame.originY;}
		super.setY(r);
	}
	
	
	public Vector3 getOVector(){
		return new Vector3(this.getOX(), this.getOY(), 0);
	}
	public void setOVector(final Vector3 o){
		this.setOX(o.x);
		this.setOY(o.y);
	}
	public void update(final float deltaTime){
		if( isPlaying ){
				this.time -= deltaTime;
				if (this.time<0){
					int nf = this.cur_frame+1;
					if (nf <  this.animation.frames.size){
						this.setFrame(nf);
					}else if (this.animation.looping){
						this.setFrame(0);
					}
					else{
						this.pause();
					}
				}
      }
   }
   /*
   
	
    
	@Override
   public void flip (boolean x, boolean y) {
			// Flip texture.
			super.flip(x, y);

			if (isAtlasSprite)
			{
				float oldOffsetX = ((AtlasRegion)region).offsetX;
				float oldOffsetY = ((AtlasRegion)region).offsetY;
				// Update x and y offsets.
				((AtlasRegion)region).flip(x, y);

				// Update position with new offsets.
				translate(((AtlasRegion)region).offsetX - oldOffsetX, ((AtlasRegion)region).offsetY - oldOffsetY);
			}
   }
  
	@Override
   public float getX () {
		return isAtlasSprite ? super.getX() - ((AtlasRegion)region).offsetX : super.getX();
   }
   
	@Override
   public float getY () {
		return isAtlasSprite ? super.getY() - ((AtlasRegion)region).offsetY : super.getY();
   }*/
}