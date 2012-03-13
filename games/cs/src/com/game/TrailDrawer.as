package com.game
{
	import flash.display.BitmapData;
	import flash.geom.ColorTransform;
	import flash.geom.Point;
	
	import org.flixel.*;
	
	import utils.Colors;
	
	public class TrailDrawer extends FlxGroup
	{
		
		public static var COLOR_TRAIL:int = Colors.C_AWHITE;
		private var fade1:Number = 0.88;
		private var fade2:Number = 0.92;
		
		public var trailsBmp:BitmapData;
		public var trailsBmp2:BitmapData;
		
		public function TrailDrawer(X:Number=0, Y:Number=0, SimpleGraphic:Class=null)
		{
			super();
			
			trailsBmp = new BitmapData(FlxG.width, FlxG.height, true, 0x00ffffff);
			trailsBmp2 = new BitmapData(FlxG.width, FlxG.height, true, 0x00ffffff);
			
		}
		
		override public function render():void
		{
			trailsBmp.scroll(-80 * FlxG.elapsed,0);
			trailsBmp.colorTransform(FlxG.buffer.rect,new ColorTransform(1,1, 1, fade1));
			
			FlxG.buffer.copyPixels(trailsBmp, FlxG.buffer.rect, new Point, null, null, true);
			
			trailsBmp2.scroll(-160 * FlxG.elapsed,60 * FlxG.elapsed);
			trailsBmp2.colorTransform(FlxG.buffer.rect,new ColorTransform(1,1, 1, fade2));
			
			FlxG.buffer.copyPixels(trailsBmp2, FlxG.buffer.rect, new Point, null, null, true);
			
			trailsBmp2.copyPixels(trailsBmp,FlxG.buffer.rect, new Point, null, null, true);
			
			super.render();
			
		}
		
	}
}