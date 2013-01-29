package com.moongate.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Atlas extends TextureAtlas{
	public Array<String> names;
	public Array<Animation> animations;
	public boolean isAtlas;
	int tile_size, tile_cols;
	
	//tiles dont have name, lol, and i dont like maps
	///public Map<String, Animation> animations;
	public Texture tilemap;
	public TextureRegion[] tiles;
	
	public Atlas(final String dir, final String name){
		//for normal atlas
		super(Gdx.files.internal(dir+"/"+name+".sprites"), Gdx.files.internal(dir));
		isAtlas=true;
		loadAnimations(dir+"/"+name+".animations");
	}
	public Atlas(final String name, final int width){
		//For tiles
		tilemap = new Texture(Gdx.files.internal(name));
		isAtlas = false;
		tile_size = width;
		this.loadTiles();
	}
	public int animationIndex(final String name){
		return this.names.indexOf(name, false);
	}
	public Animation animation(final int index){
		return this.animations.get(index);
	}
	
	private void loadAnimations(final String file){
		animations = new Array<Animation>();
		names = new Array<String>();
		try {
			DataInputStream in = new DataInputStream(Gdx.files.internal(file).read());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String name= "";
			Array<String> lines = new Array<String>();
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				strLine = strLine.trim();
				if (!strLine.trim().equals("")){
					if (strLine.contains(":")){
						lines.add(strLine);
					}else if(strLine.contains("{")){
						lines = new Array<String>();
					}else if (strLine.contains("}")){
						animations.add(new Animation(this, lines));
						names.add(name);
					}else{
						name = strLine;
					}
				}
			}
			//Close the input stream
			in.close();
		} catch (Exception ex) {
			Logger.getLogger(Atlas.class.getName()).log(Level.SEVERE, "Can't load animations"+ex);	
		}
	}
	//tile animation
	public int addTileAnimation(final int[] indexes, final int FPS, final boolean loop){
		if (isAtlas){return -1;}
		
		int idx = this.animations.size;
		Animation a;
		a = new Animation(this, indexes, FPS, loop);
		this.animations.add(a);
		return idx;
		
	}
	public int addAnimation(final String name, final String[] spriteNames, final int FPS, final boolean loop){
		///Adds an animation using an array of sprite names and a constant fps, returns the index of the animation
		
		int idx;
		//create the animation only once... return it otherway
		idx = this.animationIndex(name);
		if (idx>=0){return idx;}
		idx = this.animations.size;
		Animation a;
		a = new Animation(this, spriteNames, FPS, loop);
		this.animations.add(a);
		this.names.add(name);
		return idx;
	}
	
	public AnimatedSprite createAnimatedSprite(final String spriteName){
		//from SpriteName
		if (!this.isAtlas) {
			return null;
		}
		AnimatedSprite res = new AnimatedSprite(this);
		int anim = this.addAnimation(spriteName, new String[] {spriteName}, 0, false);
		res.play(anim);
		return res;
	}
	public AnimatedSprite createAnimatedSprite(final int index){
		//From Tiles
		if(this.isAtlas){
			return null;
		}
		AnimatedSprite res = new AnimatedSprite(this);
		int anim = this.addTileAnimation(new int[]{index},0, false);
		res.play(anim);
		return res;
	}
	
	private void loadTiles() {
		animations = new Array<Animation>();
		names = new Array<String>();
		//due to power of two some people would like to have sizes that doesnt add up... 
		//also to avoid problems..
		//this will get the maximum colums (and rows) for the tile size in the width and convert it to INT on purpose
		this.tile_cols = this.tilemap.getWidth()/this.tile_size;
		int rows = this.tilemap.getHeight()/this.tile_size;
		//effective width and height for the loops
		int tw = tile_cols*this.tile_size;
		int th = rows*this.tile_size;
		
		this.tiles = new TextureRegion[tile_cols*rows];
		
		int c=0;
		for (int i = 0; i<th; i+=this.tile_size){
			for (int j = 0; j<tw; j+= this.tile_size){
				this.tiles[c++]= new TextureRegion(this.tilemap, j, i, this.tile_size, this.tile_size );
			}
		}
	}
	public Sprite createSprite(int index){
		return new Sprite(getTile(index));
	}
	public TextureRegion getTile(final int index){
		return this.tiles[index];
	}
	public TextureRegion getTile(final int row, final int col){
		//slower
		return getTile((row*this.tile_cols)+col);
	}
	@Override
	public void dispose(){
		super.dispose();
		if (this.animations != null){
			this.animations.clear();
		}
		if (this.names != null) {
			this.names.clear();
		}

		if (this.tilemap != null) {
			this.tilemap.dispose();
			this.tilemap = null;
		}		
	}
}


/* examples:

tiles = new Atlas("res/drawable/zelda-tiles.png", 16);
atlas = new Atlas("data", "spritesheet");

* Animation the normal way. The animation must be created with Predator
		int NORMAL = atlas.animationIndex("Blast");
		AnimatedSprite animated = new AnimatedSprite(atlas);
		animated.play(NORMAL);	

* Animations for a ghost, creating animation with code
    NORMAL = a.addAnimation("AGhostNormal", new String[]{"ghost"}, 0, false);
		DYING = a.addAnimation("AGhostDying", new String[]{"ghost"}, 0, false);
		this.play(NORMAL);

* Animated Tiles:
		t = new AnimatedSprite(tiles);
		int[] frames = {261,262,263,264,265,266,267,268};
		t.play(tiles.addTileAnimation(frames, 5, true));
		t.setPosition(50, 50);

* Simple Tiles (array of Sprites from Tiles)
		test = new Sprite[frames.length];
		int b = 5, bp= 10;
		for(int i=0; i< 8; i++){
			test[i] = new Sprite(tiles.getTile(8, b++ ));
			test[i].setPosition(bp, 30);
			bp+=16;
		}
* Sprite from a SpriteSheet
 AnimatedSprite sprite = atlas.createAnimatedSprite("spritename");
* Sprite from a tileSheet
 AnimatedSPrite sprite = tiles.createAnimatedSprite(200);

 */