
package com.moongate.casanova;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.moongate.common.Atlas;
class MyGdxGame  extends Game implements ApplicationListener{
	
	public MyGdxGame() {
		
	}

	@Override
	public void create() {
		//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	/*	boysheet = new Atlas("data", "boysheet");
		girlsheet = new Atlas("data", "girlsheet");*/
	
		setScreen(new Pantalla(this));
	}

}
