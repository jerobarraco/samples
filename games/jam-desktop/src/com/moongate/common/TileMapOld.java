
package com.moongate.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TileMapOld {
	Sprite[][] map;
	Camera cam;
	int tile_size;
	Vector3 offset;
	public TileMapOld(Atlas tiles, String mapfile, Camera cam, Vector3 offset ){
		if(tiles.isAtlas){
			map = new Sprite[0][0];
		}
		this.tile_size = tiles.tile_size;
		this.cam = cam;
		this.offset = offset;
		Array<String> lines = new Array<String>();
		try {
			DataInputStream in = new DataInputStream(Gdx.files.internal(mapfile).read());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String name = "";

			//Read File Line By Line
			
			while ((strLine = br.readLine()) != null)   {
				strLine = strLine.trim();
				if (!strLine.equals("")){
					lines.add(strLine);
				}
			}
			
			//Close the input stream
			in.close();	
		} catch (Exception ex) {
			Logger.getLogger(TileMapOld.class.getName()).log(Level.SEVERE, "Can't load animations"+ex);	
		}
		//get the row size
		int r, c;
		float x, y;
		r = lines.size;
		this.map = new Sprite[0][0];

		//get the column size
		c = lines.get(0).split(",").length;
		//create a new map
		map = new Sprite[r][c];
		//create the sprites
		///y = Gdx.graphics.getHeight();
		//coordinates are inverted.
		lines.reverse();
		y = offset.y;
		for (int i = 0; i<r;i++){
			//get the line for each row and splits it
			String[] row_idxs = lines.get(i).split(",");
			x = offset.x;
			for (int j= 0; j< c; j++){
				Sprite s = tiles.createSprite(Integer.valueOf(row_idxs[j]));
				s.setPosition(x, y);
				map[i][j] = s;
				x += tiles.tile_size;
			}
			y += tiles.tile_size;
		}
	}
	public void draw(SpriteBatch sp){
		//great, the cam is centered.. the only thing centered in the whole gdx lib
		Vector3 origin = new Vector3(0, 0, 0);
		//create a vector representing the viewport
		//unproject it to see the "world coordinates"
		cam.unproject(origin);
		//convert to tilemap coordinates.
		origin.sub( offset);
		//transform world coordinates to sprite index
		int ji = (int) ((origin.x)/tile_size);
		int je = (int) ((origin.x+cam.viewportWidth)/tile_size)+1;
		//fucking camara tiene las y invertidos!
		int ii = (int) ((origin.y-cam.viewportHeight)/tile_size);
		int ie = (int) ((origin.y)/tile_size);
		
		//clamp
		if (ii<0){ii=0;}
		if (ie>=map.length){ie = map.length;}
		if (ji<0){ji=0;}
		if (je>=map[0].length){je=map[0].length;}
		//draw only visible stuff
		for (int i = ii; i< ie; i++){
			for (int j = ji;j<je; j++){
				map[i][j].draw(sp);
			}
		}
	}
}
