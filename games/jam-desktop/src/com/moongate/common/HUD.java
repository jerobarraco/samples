
package com.moongate.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD{
	Bar hp, mp;
	Sprite bg;
	AnimatedSprite pu ;
	String sTime, sScore;
	public float time;
	public int score;
	public BitmapFont bmf;
	public HUD(Atlas a){
		hp = new Bar(a, 20, 295, true );
		mp = new Bar(a, 20, 270, false );
		bg = a.createSprite("hud_bg");
		pu = a.createAnimatedSprite("power_up");
		pu.setPosition(428, 266);
		bg.setY(263);
		mp.setValue(0);
		this.time = 256;
		this.score = 0;
		sTime = "Time 256";
		sScore = "Score 0";
		bmf = new BitmapFont(Gdx.files.internal("data/fonts/font1.fnt"), false);
	}
	public void draw(SpriteBatch b){
		bg.draw(b);
		hp.draw(b);
		mp.draw(b);
		pu.draw(b, this.mp.getValue()==1 ? 1.0f: 0.5f);
		//slow as crap rolling off of a cliff
		bmf.draw(b, sTime,  168, 320);
		bmf.draw(b, sScore,  168, 300);
	}
	public void update(float d){
		hp.update(d);
		mp.update(d);
		int ptime = (int) this.time;
		this.time -= d;
		int ntime = (int) this.time;
		
		if (ptime > ntime){
			this.sTime = "Time " + String.valueOf(ntime);
		}
		
	}
	public void addScore(int p){
		score += p;
		this.sScore = "Score " + String.valueOf(score);
		
	}
	
	public void setEnergy(float f) {
		hp.setValue(f);
	}
	public void setKarma(float f){
		mp.setValue(f);
	}
}
