package com.game 
{
	import org.flixel.*;
	
	
	public class File extends FlxSprite
	{
		[Embed(source = "/data/Archivos/Imagen.png")] private var Img:Class;
		
		public var magnetized:Boolean = false;
		
		private var gravity:int = 5;
		
		public function File():void
		{
			super();
			velocity.x = -20;
			
			loadGraphic(Img);
		}
		
		override public function update():void 
		{
			if (!onScreen() && (!y<0)) this.kill();
			alpha += 0.1;
			if (alpha >= 1) {
				alpha = 1;
			}
			
			if(magnetized)
			{
				//Aiming
				var dx:Number = x - PlayState.player.x;
				var dy:Number = y - PlayState.player.y;
				var da:Number = FlxU.getAngle(dx,dy);

				var move:FlxPoint = FlxU.rotatePoint(1,0,0,0,da);
				velocity.x = -move.x*1000;
				velocity.y = -move.y*1000;
			}
			else
			{
				velocity.y+=gravity;
			}
			
			super.update();
		}
		
		override public function reset(X:Number, Y:Number):void {
			alpha = 0;
			magnetized = false;
			velocity.x = -20;
			velocity.y = -180;
			super.reset(X, Y);
		}
		
	}

}