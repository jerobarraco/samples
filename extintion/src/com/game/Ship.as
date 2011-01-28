package com.game
{
	import org.flixel.*;
	
	public class Ship extends FlxSprite
	{
		
		private var speed:Number = 100;
		
		public function Ship(X:Number=0, Y:Number=0):void
		{
			super(X,Y);
			
			createGraphic(10,10);
			
		}
		
		override public function update():void
		{
			if(FlxG.keys.UP)
			{
				y -= FlxG.elapsed*speed;
			}
			if(FlxG.keys.DOWN)
			{
				y += FlxG.elapsed*speed;
			}
			if(FlxG.keys.LEFT)
			{
				x -= FlxG.elapsed*speed;
			}
			if(FlxG.keys.RIGHT)
			{
				x += FlxG.elapsed*speed;
			}
			if(FlxG.keys.justPressed("Z") || FlxG.keys.justPressed("SPACE"))
			{
				Shoot(RIGHT);
			}
			
			super.update();
		}
		
		private function Shoot(dir:uint):void
		{
			var Xdesv:Number;
			var shots:FlxGroup = PlayState.lyr_shots;
			
			if (dir == RIGHT) 
			{
				Xdesv = 2;
			}
			else 
			{
				Xdesv = -4;
			}
			
			for (var i:uint = 0; i < shots.members.length; i++)
			{
				if (!shots.members[i].exists)
				{
					shots.members[i].reset(x+ Xdesv, y+ 2);
					return;
				}
			}
			//sino se crea y se agrega al array (push) dentro de la capa de sprites
			var shot:Shots = new Shots( x + Xdesv, y +2);
			shot.reset(x + Xdesv, y+2);
			shots.members.push(PlayState.lyr_shots.add(shot));
		}
	}
}