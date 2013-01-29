
package com.moongate.casanova;

import com.moongate.common.AnimatedSprite;
import com.moongate.common.Atlas;

public class Boy extends AnimatedSprite{
	//current attitude
	//animation indexes
	int [] anims;
	//animation names
	//dance, skate, basket, dandy, smart, funny, romantic, heavy, music, drunk, walk, win, lose
	String [] anim_names = {"dance", "skate", "basket", "dandy", "smart", "funny", "romantic", "heavy", "music", "drunk", "walk", "win", "lose", "stand"};
	public int att;
	public Boy(Atlas a){
		super(a);
		anims = new int[anim_names.length];
		for (int i = 0; i<anims.length;i++){
			anims[i] = a.animationIndex(anim_names[i]);
		}
		this.play(anims[0]);
		this.setScale(1.1f);//se ve medio enano.. y esto es un juego machista, osbvio
	}
}
