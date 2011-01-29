package com.game
{
	import org.flixel.FlxG;
	import org.flixel.FlxSprite;
	
	public class Enemy extends FlxSprite
	{
		public function Enemy(X:Number=0, Y:Number=0, SimpleGraphic:Class=null)
		{
			super(X, Y, SimpleGraphic);
			
			createGraphic(10,10,0xffff0000);
			kill();
		}
		
		override public function update():void
		{
			x-= FlxG.elapsed * 100;
			
			if(x<0)
				kill();
			
			super.update();
		}
	}
}