/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moongate.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BaseSprite extends Sprite{
	
	public boolean alive=true;
	Texture text;
	private TextureRegion[] texts;
	int cols, rows;
	public BaseSprite(String texture, int sw, int sh){
		super();
		text = new Texture(Gdx.files.internal(texture));
		text.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		cols = text.getWidth() / sw;
		rows = text.getHeight() / sh;
		
		TextureRegion[][] tmp = TextureRegion.split(text, sw, sh);
		texts = new TextureRegion[cols*rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
							texts[index++] = tmp[i][j];
				}
		}
		this.setRegion(texts[0]);
		
		this.setSize(sw, sh);
		this.setOrigin(sw/2, sh/2);
		//sp.setOrigin(sw/2, sh/2);
		this.setPosition(0, 0);
		//sp.setPosition(-sp.getWidth()/2, -sp.getHeight()/2);
	}
	public void dispose(){
		text.dispose();
		
	}
	public void kill(){
		this.alive = false;
	}
	public void reset(float x, float y){
		this.setX(x);
		this.setY(y);
		this.alive = true;
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
	public void update(float d){
		
	}
	public boolean collide(BaseSprite other){
		return this.getBoundingRectangle().overlaps(other.getBoundingRectangle());
	}
}
