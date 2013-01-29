
package com.moongate.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
	Atlas tiles;
	Sprite [][] stiles ;
	TileMap tm; 
	public Level(Atlas tiles, String mapfile){
		this.tiles = tiles;
		//tm = new TileMap(tiles, mapfile, cam);
	}
	public void draw(SpriteBatch sp){
	}
}
