
package com.moongate.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.tiled.TileSet;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLayer;
import com.badlogic.gdx.graphics.g2d.tiled.TiledLoader;
import com.badlogic.gdx.graphics.g2d.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import java.util.HashMap;

public class TileMap {
	Camera cam;
	Vector3 offset;
	public TiledMap map;
	private TiledLayer layer;
	private int cache;
	private Atlas atlas;
	public float max_y;
	SpriteCache spc;
	HashMap<Integer, HashMap<String, String>> props = new HashMap<Integer, HashMap<String, String>>();
	String [] prop_names = {"collide", "pit"};
	private final HashMap<String, Array<Rectangle>> on_screen;
	//TODO layers
	public TileMap(String dir, String mapfile, Camera cam, Vector3 offset){	
		
		this.cam = cam;
		this.offset = offset;
		this.map = TiledLoader.createMap(Gdx.files.internal(dir+"/"+mapfile));
		
		//todo multiple layers
		layer = map.layers.get(0);
		//todo multiple tilesets
		TileSet ts = this.map.tileSets.get(0);
		
		this.atlas = new Atlas(dir+"/"+ts.imageName, ts.tileWidth);
		
		int fgid = map.tileSets.get(0).firstgid;
		
		spc = new SpriteCache();
		spc.beginCache();
		int r = layer.tiles.length;
		int c = layer.tiles[0].length;
		float y = offset.y;
	
			//yeah, the code sucks
		
		for (int i=r-1; i>=0; i--){
			float x = offset.x;
			for (int j= 0; j<c; j++ ){
				//todo get the correct tileset
				int iid = layer.tiles[i][j];
					if (iid>0){
						spc.add(atlas.getTile(iid-fgid), x, y);
					}
				
				//  crashes TextureRegion reg = tileAtlas.getRegion(layer.tiles[i][j]);
				//if (reg!= null){
				//	spc.add(reg, x, y);
				//}
				
				x += map.tileWidth;
			}
			y += map.tileHeight;
		}
		cache = spc.endCache();
		max_y = y- map.tileHeight;
		//OnScreenObjects
		on_screen = new HashMap<String, Array<Rectangle>>();
		for (String k: prop_names){
			on_screen.put(k, new Array<Rectangle>());
		}
	}
	
	public void draw(){
		spc.setProjectionMatrix(this.cam.combined);
		spc.begin();
		spc.draw(cache);
		spc.end();
	}
	
	public float getBaseY(){
		return this.cam.viewportHeight - offset.y;
	}
	public Rectangle getOnScreenMapCoords(){
		int tile_size = this.map.tileWidth;
		//TODO layers
		int [][] tiles = this.map.layers.get(0).tiles;
		Rectangle box = new Rectangle();
		Vector3 origin = new Vector3(0, 0, 0);
		//create a vector representing the viewport
		//unproject it to see the "world coordinates"
		cam.unproject(origin);
		//convert to tilemap coordinates.
		origin.sub(offset);
		//transform world coordinates to sprite index
		int ji = (int) ((origin.x)/tile_size);
		int je = (int) ((origin.x+cam.viewportWidth)/tile_size);
		//fucking camara tiene las y invertidos!
		int ii = (int) ((origin.y-cam.viewportHeight)/tile_size);
		int ie = (int) ((origin.y)/tile_size);
		
		//clamp
		if (ii<0){ii=0;}
		if (ie>=tiles.length){ie = tiles.length;}
		if (ji<0){ji=0;}
		if (je>=tiles[0].length){je=tiles[0].length;}
		
		
		box.x = ji;
		//no, its not width, is actually x2 , width would be x2-x1 but i would have to make the addition later so sucks
		box.width = je;
		box.y = ii;
		box.height = ie;
		return box;
	}
	public HashMap<String, String> getIdProperties(int id, int fgid){
		HashMap<String, String> res = props.get(id);
		if(res == null){
			//libgdx totally sucks on properties, they made it private, the internal class is also private
			//and there's no way to actually iterate it, so it sucks
			//and calling this function will iterate the whole properties, and i'm not going to do that on each frame

			//creating a new hashmap consumes more ram, but will speed up the iteration
			res = new HashMap<String, String> ();
			for (String k : prop_names){
				String v = map.getTileProperty(id+fgid, k);
				if (v!=null){
					res.put(k, v);
				}
			}
			props.put(id, res);
		}
		return res;
	}
	public Rectangle boundsFromIndex(int i, int j, int tile_size){
		Rectangle box = new Rectangle();
		box.x = offset.x + ((j+1)*tile_size);//+1? //todo WHY?
		box.y = offset.y + cam.viewportHeight - (i*tile_size);
		box.width = tile_size;
		box.height = tile_size;
		return box;
	}
	public HashMap<String, Array<Rectangle>> getOnScreenItems(Sprite cat){
		int tile_size = this.map.tileWidth;
		//todo layers
		int fgid = this.map.tileSets.get(0).firstgid;
		
		Rectangle bounds = getOnScreenMapCoords();
		int [][] tiles = this.map.layers.get(0).tiles;
		
		//clear the coso from the previous values (really performant yes)
			
		for (String k: prop_names){
			on_screen.get(k).clear();
		}
		
		for (int i = (int) bounds.y; i<bounds.height; i++){
			for (int j = (int) bounds.x; j<bounds.width; j++){
				//tiled has a nice "feature" tiles ids are shifted per tilesheet, but properties hold 0-bazed id for each tilesheet, FUCKING NICE >:[
				int id = tiles[i][j] ;
				//crappy dynamic loading of properties
				HashMap<String, String> tile_props = getIdProperties(id, fgid);
				if (tile_props!=null){
					Rectangle obj = boundsFromIndex(i, j, tile_size);
					for (String k: tile_props.keySet()){
						//i could check if the key is == true, but that's unnecesary, better dont set anything if you dont need it	
						on_screen.get(k).add(obj);
					}
				}
			}
		}
		return on_screen;
	}
}
