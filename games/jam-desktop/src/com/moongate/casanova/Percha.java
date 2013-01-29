
package com.moongate.casanova;

import com.moongate.common.AnimatedSprite;
import com.moongate.common.Atlas;

public class Percha extends AnimatedSprite {
	int ACTIVADO, DESACTIVADO;
	//dance, skate, basket, dandy, smart, funny, romantic, heavy, music, drunk, walk, win, lose
	String [] names ={ "BOLICHERO", "SKATER", "DEPORTISTA", "DANDY", "INTELECTUAL", "CHISTOSO", "ROMANTICO", "HEAVY", "MUSICO",	"BORRACHO"};
	public Percha(Atlas a, int i){
		super(a);
		String n = names[i];
		String act=n+"+", des=n+"-";
		ACTIVADO = a.addAnimation(act, new String[]{act.toString()}, 0, false);
		DESACTIVADO = a.addAnimation(des, new String[]{des.toString()}, 0, false);
		
		play(ACTIVADO);
		float x=210;
		float y=58;
		//this.setY(60+(50*((int)i/2)));
		if (i%2==0){
			x+=114;
		}
		y += (i/2)*46;
		setScale(0.5f);
		this.setPosition(x, y);
	}
}
