package com.game
{
	import flash.display.*;
	import flash.filters.BevelFilter;
	import flash.filters.BlurFilter;
	import flash.filters.GlowFilter;
	import flash.geom.*;
	
	import org.flixel.*;
	import org.flixel.data.FlxConsole;
	
	import utils.Colors;
	
	public class Lazer extends FlxSprite
	{
		//Original: http://flixel.org/forums/index.php?topic=1596.0
		
		//important vars
		public var LaserOrigin:FlxPoint;		//Point where the laser originally fires from
		public var speed:Number = 20;			//distance per tick
		public var laser_length:Number = 0;		//length of the laser
		
		public var tippoint:Point;
		
		//for variable laser width
		public var widthDir:int = 1;
		public var widthMax:int;
		public var widthMin:int;
		public var laser_width:int;
		
		//for variable spark diameter
		public var sparkDir:int = 1;
		public var sparkMax:int ;
		public var sparkMin:int;
		public var sparkCur:int;
		
		//for variable glow
		public var glowDir:int = 1;
		public var glowMax:int = 16;
		public var glowMin:int = 3;
		public var glowCur:int = glowMin;
		
		public function Lazer(X:Number=0, Y:Number=0):void
		{
			LaserOrigin = new FlxPoint(X, Y)
			
			super(X,Y);
		}
		
		override public function reset(X:Number, Y:Number):void
		{
			LaserOrigin.set(X, Y);
			laser_length = 0;
			
			//for variable laser width
			widthDir = 1;
			widthMin = widthMax - 2;
			laser_width = widthMin;
			
			//for variable spark diameter
			sparkDir = 1;
			sparkMax = laser_width *1.2 ;
			sparkMin = laser_width*1.2 - 2;
			sparkCur = sparkMin;
			
			super.reset(X,Y);
		}
		
		override public function render():void
		{
			var canvas:Shape = new Shape();
			
			laser_width += widthDir;
			if (laser_width == widthMax || laser_width == widthMin)	widthDir = -widthDir;
			
			//spark
			tippoint = new Point (LaserOrigin.x + laser_length , LaserOrigin.y + laser_width /4 );
			
			//limite derecho
			if(tippoint.x < FlxG.width)
			{
				laser_length += speed;
			}
			
			//Create the Beam's Fill,
			canvas.graphics.lineStyle(laser_width,Colors.C_AWHITE);
			canvas.graphics.moveTo(LaserOrigin.x, LaserOrigin.y + laser_width /4);
			canvas.graphics.lineTo(tippoint.x,tippoint.y);
			
			canvas.graphics.lineStyle(0,Colors.C_AWHITE);
			
			//Add the spark at the end
			sparkCur += sparkDir;
			if (sparkCur == sparkMax || sparkCur == sparkMin)	sparkDir = -sparkDir;
			canvas.graphics.beginFill(Colors.C_WHITE);
			canvas.graphics.drawCircle(tippoint.x, tippoint.y, sparkCur);
			canvas.graphics.endFill();
			
			//Add the Glow
			glowCur += glowDir;
			if (glowCur == glowMax || glowCur == glowMin)	glowDir = -glowDir;
			var glow:GlowFilter = new GlowFilter;
			glow.color = 0x7CC2FF;
			glow.strength = 4;
			glow.blurX = glowCur;
			glow.blurY = glowCur;
			glow.alpha = glowCur / glowMax;
			canvas.filters = [glow];
			//Add the blur
			var blur:BlurFilter = new BlurFilter(2,2,1);
			canvas.filters = [glow,blur];
			
			//Draw it in, finally.
			FlxG.buffer.draw(canvas, null, null,"hardlight");	
		}
	}
}