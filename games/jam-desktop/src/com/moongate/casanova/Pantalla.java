
package com.moongate.casanova;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.moongate.common.AnimatedSprite;
import com.moongate.common.Atlas;
import com.moongate.common.Stage;

class Pantalla extends Stage{
	AnimatedSprite okbtn, fondo, fondo_disco1, fondo_disco2, fondo_cred, fondo_start, b_start, b_retry;
	AnimatedSprite [] esferas;
	Luz [] luces;
	Boy boy;
	
	Percha [] perchas;
	
	int passes=0, sel_girl=-1, max_p=0, a_start, a_start_on, a_retry, a_retry_on;
	float timer;
	private boolean win=false;
	private final Atlas sprites, fondos;
	private final Atlas s_boy;

	public enum States {start, decide, pass, choose, result, credits};
	public 	States state;
	public enum ATTS  {dance, skate, basket, dandy, smart, funny, romantic, heavy, music, drunk};
	public int CATT = 10;
	public Girl[] girls;
	Music music;
	public Pantalla(MyGdxGame parent) {
		super(parent);
		Gdx.input.setCatchBackKey(false);
		
		s_boy = new Atlas("data", "boy");
		sprites = new Atlas("data", "sprites");
		fondos = new Atlas("data", "fondos");
		fondo_start = fondos.createAnimatedSprite("start");
		
		music = new Music();
		
		b_start = new AnimatedSprite(sprites);
		a_start = sprites.addAnimation("start", new String[]{"start_off"},0, false);
		a_start_on = sprites.addAnimation("start_on", new String[]{"start_on"},0, false);
		b_start.play(a_start);
		b_start.setX(270);
		b_start.setY(10);
		
		b_retry = new AnimatedSprite(sprites);
		a_retry = sprites.addAnimation("retry", new String[]{"retry_off"}, 0, false);
		a_retry_on = sprites.addAnimation("retry_on", new String[]{"retry_on"}, 0, false);
		b_retry.play(a_retry_on);
		b_retry.setX(20);
		b_retry.setY(10);
		
		girls = new Girl[4];
		for (int i = 0; i<4; i++){
			girls[i] = new Girl(this, sprites,  i);
		}
				
		boy = new Boy(s_boy);
		okbtn = new AnimatedSprite(sprites);
		okbtn.play(sprites.animationIndex("go"));
		okbtn.setY(200);
		okbtn.setX(60);
		okbtn.setScale(0.5f);
		fondo = fondos.createAnimatedSprite("cuarto");
		fondo.setX(-10);
		fondo_disco1 = fondos.createAnimatedSprite("disco");
		fondo_disco2 = fondos.createAnimatedSprite("disco");
		fondo_disco1.setY(-25);
		fondo_disco2.setY(-25);
		fondo_disco2.setX(fondo_disco1.getX()+fondo_disco1.getWidth()-2);//-2 flicker*/
		
		fondo_cred = fondos.createAnimatedSprite("credits");
		
		esferas = new AnimatedSprite[3];
		for (int i = 0; i<3; i++){
			AnimatedSprite esfera = sprites.createAnimatedSprite("ball");	
			esfera.setX((i+1)*300);
			esfera.setY(250);
			esferas[i] = esfera;
		}
		luces = new Luz[10];
		for (int i = 0; i<luces.length; i++){
			Luz luz = new Luz(sprites, i)	;
			luces[i] = luz;
		}		
		perchas = new Percha[10];
		for (int i = 0 ; i<10;i++){
			perchas[i] = new Percha(sprites, i);
		}
		
		this.setState(States.start);
		
	}
	public void reset(){
		passes=0;
		boy.setScale(1f);
		b_retry.play(a_retry_on);
		b_start.play(a_start_on);
		for (Girl g: girls){
			g.reset(this);			
		}
		camera.zoom = 1f;
		//music.stopSounds();
		music.stop_music();
		for(Percha p: perchas){
			p.play(p.ACTIVADO);
		}
	}
	public final void setState(States s){
		this.state = s;
		switch(state){
			case start:
				reset();
				music.play_music(3);
				b_start.play(a_start);
				timer = 1f;
				break;
			case decide:
				music.play_music(0);
				
				boy.setX(20);
				boy.setY(20);
				boy.play(boy.anims[13]);
				camera.position.x = 240;
				camera.position.y = 160;
				camera.zoom=1f;
				break;
			case pass:
				music.play_music(1);
				fondo_disco1.setScale(1f);
				fondo_disco2.setScale(1f);
				boy.setPosition(20, 20);
				camera.position.x = 240;
				for (Girl g: girls){
					g.play(g.anims[0]);
				}
				break;
			case choose:
				music.play_music(2);
				for (Girl g: girls){
					g.play(g.anims[Girl.states.STAND.ordinal()]);
				}
				boy.play(boy.anims[13]);
				boy.setPosition(240, -10);
				fondo_disco1.setScale(1.5f);
				fondo_disco2.setScale(1.5f);
				camera.zoom = 1.5f;
				camera.position.x = 570;
				camera.position.y = 150;
				timer = 8;
				break;
			case result:
				timer = 5;
				fondo_disco1.setScale(1.5f);
				fondo_disco2.setScale(1.5f);
				camera.zoom = 1.5f;
				//camera.position.x = 0;
				camera.position.x = 570;
				camera.position.y = 150;
				boy.setX(400);
				boy.setScale(2f);
				boy.setY(30);
				if (win){
					boy.play(boy.anims[11]);
				}else{
					boy.play(boy.anims[12]);
				}
				for(int i=0; i<girls.length;i++){
					Girl g = girls[i];
					g.play(g.anims[0]);
					if(i==sel_girl){
						g.setScale(2f);
						g.setX(600);
						g.setY(boy.getY());
						if (win){
							g.play(g.anims[3]);
						}else{
							g.play(g.anims[1]);
						}
					}else{
						if (g.likes==max_p){
							//if(!win)
							g.play(g.anims[1]);
						}
					}
				}
				

				break;
			case credits:
				music.play_music(1);
				camera.position.x = 240;
				camera.position.y = 160;
				camera.zoom = 1f;
				break;
		}
	}
	public void update_start(float d){
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if (b_start.getBoundingRectangle().contains(touchPos.x, touchPos.y)	){
				b_start.play(a_start_on);
				music.stop_music();
			}
		}
		b_start.update(d);
		if (b_start.state == a_start_on){
			timer -=d;
		}
		if (timer<0){
			setState(States.decide);
		}
	}
	public void update_decide(float d ){
		okbtn.update(d);
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			for (int i = 0; i<perchas.length; i++){
				Percha p = perchas[i];
				if ((p.state == p.ACTIVADO) && p.getBoundingRectangle().contains(touchPos.x, touchPos.y)){
					/*if (cajon>-1){
						Percha p2 = perchas[cajon];
						p2.play(p2.ACTIVADO);
					}
					*/
					p.play(p.DESACTIVADO);
					boy.play(boy.anims[i]);
					boy.att = i;
					setState(States.pass);
					break;
				}
			}
			/*if (okbtn.getBoundingRectangle().contains(touchPos.x, touchPos.y)){
				setState(States.pass);
			}*/
		}
	}
	public void update_pass(float d){
		//esfera.update(d);
		float tx = 99.999f*d;//ah re diabolico
		for (AnimatedSprite esfera: esferas){
			esfera.update(d);
		}
		for (Luz l: luces){
			l.update(d);
		}
		boy.update(d);
		boy.translateX(tx);
		this.camera.translate(tx, 0);
		
		Rectangle br = boy.getBoundingRectangle();
		for (Girl g: girls){
			if(br.overlaps(g.getBoundingRectangle())){
				g.react(boy);
			}
			g.update(d);
		}
		if(boy.getX() > girls[girls.length-1].getX()+100){
			//3 pasadas contand desde 0
			if (passes>1){
				setState(States.choose);
			}
			else{
				passes+=1;
				setState(States.decide);
			}
		}
	}
	public void update_choose(float d){
		boy.update(d);
		for (Girl g: girls){
			g.update(d);
		}
		for(Luz l: luces){
			l.update(d);
		}
		boolean touched = false;
		if(Gdx.input.isTouched()){
			max_p=-10;
			int cur_p=-10;
			
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			for (int i = 0; i< girls.length; i++){
				Girl g = girls[i];
				if (g.likes > max_p){
					max_p = g.likes;
				}
				if(g.getBoundingRectangle().contains(touchPos.x, touchPos.y)){
					//check if it is the one with best points, and win
					cur_p = g.likes;
					sel_girl = i;
					touched = true;
				}
			}
			if (touched){
				win = cur_p >= max_p;		
				setState(States.result);	
			}
		}
		timer -= d;
		if (timer<0){
			win = false;
			sel_girl = -1;
			setState(States.result);
		}
	}
	public void update_result(float d){
		boy.update(d);
		for (Girl g: girls){
			g.update(d);	
		}
		if (sel_girl>-1){
			Girl g = girls[sel_girl];
			if (!win){
				g.translate(60*d, 0);
			}else{
				g.translate(-6*d, 0);
			}
		}
		timer -= d;
		if (timer<0){
			setState(States.credits);
		}
	}
	public void update_credits(float d){
		b_retry.update(d);
		
		if (b_retry.state == a_retry_on){
			Vector3 tp = getTouch();	
			if (b_retry.getBoundingRectangle().contains(tp.x, tp.y)){
				b_retry.play(a_retry);
				timer = 1;
			}
		}else{
			timer -= d;
			if (timer<0){
				setState(States.start);
			}
		}
	}
	
	@Override
	public void update(float d) {
		switch(state){
			case start:
				update_start(d); break;
			case decide:
				update_decide(d);break;
			case pass:
				update_pass(d); break;
			case choose:
				update_choose(d);break;
			case result:
				update_result(d);break;
			case credits:
				update_credits(d);break;
		}
	}


	public void draw_start(SpriteBatch sb){
		fondo_start.draw(sb);
		b_start.draw(sb);
	}
	
	public void draw_decide(SpriteBatch sb){
		fondo.draw(sb);
		boy.draw(sb);
		for (Percha p: perchas){
			p.draw(sb);
		}	
		okbtn.draw(sb);
	}
	
	private void draw_pass(SpriteBatch sb) {
		fondo_disco1.draw(sb);
		fondo_disco2.draw(sb);
		for (AnimatedSprite esfera: esferas){
			esfera.draw(sb);
		}
		for (Luz l: luces){
			l.draw(sb);
		}
		for (Girl g: girls){
			g.draw(sb);
		}
		boy.draw(sb);
	}
	private void draw_choose(SpriteBatch sb){
		draw_pass(sb);
	}
	private void draw_result(SpriteBatch sb){
		fondo_disco1.draw(sb);
		fondo_disco2.draw(sb);
		//esfera.draw(sb);
		for (int i = 0; i< girls.length;i++){
			Girl g= girls[i];
			if (i != sel_girl){
				g.draw(sb);
			}
		}
		if (sel_girl>-1){
			girls[sel_girl].draw(sb);
		}
		boy.draw(sb);
	}
	
	private void draw_credits(SpriteBatch sb){
		fondo_cred.draw(sb);
		b_retry.draw(sb);
	}
	public Vector3 getTouch(){
		if(Gdx.input.isTouched()){
			Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			return touchPos;
		}else{
			return new Vector3(-1,-1,-1);
		}
	}
	@Override
	public void render(SpriteBatch sb) {
		switch(state){
			case start:
				draw_start(sb); break;
			case decide:
				draw_decide(sb); break;
			case pass:
				draw_pass(sb); break;
			case choose:
				draw_choose(sb); break;
			case result: 
				draw_result(sb); break;
			case credits: 
				draw_credits(sb); break;
		}
	}

	@Override
	public void renderHUD(SpriteBatch hsb) {
	}

	@Override
	public boolean keyUp(int i) {
		return false;
	}

	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	@Override
	public boolean touchDown(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchUp(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchDragged(int i, int i1, int i2) {
		return false;
	}

	@Override
	public boolean mouseMoved(int i, int i1) {
		return false;
	}

	@Override
	public boolean scrolled(int i) {
		return false;
	}

	@Override
	public void resize(int i, int i1) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		fondos.dispose();
		s_boy.dispose();
		sprites.dispose();
		
	}
	@Override
	public void render() {
		
	}
}
