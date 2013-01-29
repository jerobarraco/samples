package com.moongate.common;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public final class Animation {
	public class Frame{
		public TextureRegion text;
		public int offX, offY, originX, originY, width, height;
		public double duration=0;
		public boolean rotate=false;
		public Frame(){};
		public Frame(TextureAtlas.AtlasRegion region){
			fromRegion(region);
			rotate = region.rotate;
			offX = (int) region.offsetX; //region.offset is CRAZY!!
			offY = (int) region.offsetY;
		}
		public Frame(TextureRegion reg){
			fromRegion(reg);
			offX = 0;
			offY = 0;
		}
		private void fromRegion(TextureRegion reg){
			text = reg;
			width = Math.abs(reg.getRegionWidth());
			height = Math.abs(reg.getRegionHeight());
			originX = width/2; //originalWidth returns somethingg crazy also! we can use packedWidth?
			originY = height/2;
		}
	}
	float time;
	int frame;
	public Array<Frame> frames;
	boolean looping;
	
	public Animation(final Atlas atlas, final int[] indexes, final int FPS, final boolean loop){
		//Create an animation from an tilemap and a list of indexes an fps and the flag for looping
		this.looping = loop;
		float dur;
		if (FPS==0){
			dur = 0;
		}else{
			dur = 1.0f/FPS;
		}
		frames = new Array<Frame>();
		for (int n: indexes){
			Frame f = new Frame( atlas.getTile(n));
			f.offX = 0;
			f.offY = 0;
			f.duration = dur;
			frames.add(f);
		}
	}	
	public Animation(final Atlas atlas, final String[] names, final int FPS, final boolean loop){
		//Create an animation from an atlas an a list of names and a fps (fps cant be 0)
		this.looping = loop;
		float dur;
		if (FPS==0){
			dur = 0;
		}else{
			dur = 1.0f/FPS;
		}
		frames = new Array<Frame>();
		for (String n: names){
			Frame f = new Frame( atlas.findRegion(n));
			f.offX = 0;
			f.offY = 0;
			f.duration = dur;
			frames.add(f);
		}
	}
	public Animation(final Atlas atlas, final Array<String> lines){
		//Default, loads an animation from a block of lines from the file
		time = 0;
		frame = 0;
		frames = new Array<Frame>();
		parseLines(atlas, lines);
	}
	private void parseLines(final Atlas atlas, Array<String> lines ){
		for (String l:lines){
			if (l.startsWith("looping")){
				this.looping = l.split(":", 2)[1].trim().equals("True");
			}else if (l.startsWith("frame")){
				l = l.split(":", 2)[1].trim();
				String[] v = l.split(",");
				Frame f = new Frame( atlas.findRegion(v[0]));
				f.offX = (int) (Integer.parseInt(v[1].trim()));//region.offset is CRAZY!
				f.offY = (int) (Integer.parseInt(v[2].trim()));		
				f.duration = (float)Integer.parseInt(v[3].trim()) / 1000.0f;
				frames.add(f);
			}
		}
	}
}
