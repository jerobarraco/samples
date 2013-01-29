
package com.moongate.casanova;
import com.moongate.common.AnimatedSprite;
import com.moongate.common.Atlas;
public class Girl extends AnimatedSprite {
	//que le gusta
	int[] prefers;
	//cuanto le gusta
	int likes=0;
	//al pedo
	
	int [] anims;
	public float o_x, o_y;
	
	public enum states {STAND, DISLIKE, NEUTRAL, LIKES};
	String [] anim_names = {"parada", "dislikes", "neutral", "likes"};
	
	public void reset(Pantalla p){
		for (int j = 0; j<p.CATT; j++){
			prefers[j] = (int)(Math.random()*3.0d) -1;
		//	System.out.println(String.valueOf(prefers[j]));
		}
		this.setScale(1f);
		this.play(anims[states.STAND.ordinal()]);
		this.setPosition(o_x, o_y);
		this.likes = 0;
	}
	public Girl(Pantalla p , Atlas a, int i){
		super(a);
		
		prefers = new int[p.CATT];
		
		
		anims = new int[4];
		//String pre = String.valueOf((int)(Math.random()*2)).concat("-");
		String pre = String.valueOf(i%4).concat("-");
		for (int j = 0; j<4; j++){
			
			anim_names[j]= pre.concat(anim_names[j]);
			
			anims[j] = a.animationIndex(anim_names[j]);
		}
		//no usar setX porque se lo pasa por el ojete
		o_x = 130*(i+3);
		o_y = 40;
		this.reset(p);
	}

	void react(Boy boy) {
		//if state == normal then...
		int p = prefers[boy.att];
		likes += p;
		//-1 dislikes, 0 neutral, 1 likes
		//anims 0 stand, 1 disk, 2 neutral, 3 likes
		play(anims[p+2]);
	}
}
