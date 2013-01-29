
package com.moongate.casanova;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.moongate.common.AnimatedSprite;
import com.moongate.common.Atlas;

public class Luz extends AnimatedSprite{
	float timer=1, scale=1;
	Color c2;
	int an;
	public Luz(Atlas a, int i){
		super (a);
		an = a.addAnimation("luz", new String[] {"luz2"}, 0, false);
		this.play(an);
		
		c2 = new Color((float) Math.random(), (float)Math.random(),(float)Math.random(), 1f);
		boolean f= i%2==0;
		
		this.setOrigin(f?168:45, 196);
		
		this.setPosition((i+1)*200, 150);
		this.flip( f, false);
		
	}
	
	@Override
	public void update(float d){
			super.update(d);
			
			timer -=d;
			if (timer <0){
				timer = 1;
				scale = -scale;
				
				c2 = new Color(1f,(float) Math.random(), (float)Math.random(),(float)Math.random());
		}
		this.setRotation(this.getRotation()+(scale*10*d));
	}
	
	@Override
	public void draw(SpriteBatch s){
		
		//Color c = s.getColor();
		this.setColor(c2);
		super.draw(s);
		//s.setColor(c);
	}
}
