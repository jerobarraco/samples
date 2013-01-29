
package com.moongate.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bar {
	AnimatedSprite fg, bar, bg;
	private float shown=1, value=1, diff=0.005f;
	private float ow;
	public float getValue(){
		return value;
	}
	public Bar(final Atlas a, final int x, final int y, final boolean red){
		bg = a.createAnimatedSprite("bar_bg");
		bg.centered = true;
		bg.setPosition(x, y);
		if (red){
			bar = a.createAnimatedSprite("bar_red");
			fg = a.createAnimatedSprite("bar_fg1");
		}else{
			bar = a.createAnimatedSprite("bar_blue");
			fg = a.createAnimatedSprite("bar_fg2");
		}
		bar.centered = true;
		fg.centered = true;
		
		bar.setOX(bg.getOX());
		bar.setOY(bg.getOY());
		fg.setOX(bg.getOX());
		fg.setOY(bg.getOY());
		this.ow = bar.getWidth();
	}
	public void update(final float d){
		float nval = this.shown;
		if (this.shown > value){
			nval = this.shown-0.005f;
			//i'll do it this way until i find a cleaner way to avoid "flickering" due to non-divisible values
			if (nval < this.value){
				nval = this.value;
			}
			if (nval < 0){
				nval = 0;
			}
		}
		if (this.shown < value){
			nval = this.shown+0.005f;
			if (nval > this.value){
				nval = this.value;
			}
			if (nval>1){nval = 1;}
		}
		if (nval != this.shown){
			this.setShownValue(nval);
		}
	}
	public void draw(SpriteBatch sb){
		bg.draw(sb);
		bar.draw(sb);
		fg.draw(sb);
	}
	public void setValue(float percent){
		if(percent<0){percent =0;}
		if (percent>1){percent=1;}
		this.value = percent;
	}
	private void setShownValue(float percent){
		this.shown = percent;
		float x, y, w, h;
		Rectangle r = this.bar.getBoundingRectangle();
		r.setWidth(ow* percent);
		this.bar.setBounds(r.x, r.y, r.width, r.height );
	}
	
}
