package com.game 
{
	import org.flixel.FlxSprite;
	
	public class Explotion extends FlxSprite
	{
		[Embed(source = "/data/Explotion.png")] private var ImgExplo:Class;
		
		public function Explotion() 
		{
			super();
			loadGraphic(ImgExplo, true, false, 24, 24, false);
			addAnimation("Explo", [0, 1, 2, 3, 4, 5, 6], 20, false);
			play("Explo");
		}
		override public function update():void {
			super.update();
			if (finished) {
				kill();
			}			
		}
		override public function reset(X:Number, Y:Number):void {
			super.reset(X, Y);
			finished = false;
			frame = 0;
			play("Explo");
		}
		
	}

}