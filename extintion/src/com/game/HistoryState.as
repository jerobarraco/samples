package com.game
{
	import org.flixel.*;
	import org.flixel.data.FlxKeyboard;

	public class HistoryState extends FlxState
	{
		private var histories:Array;
		private var nextState;
		[Embed(source = "/data/Historia/01 Blue Screen Inicio.png")] private var ImgScreen:Class;
		private var img:FlxSprite;
		
		override public function create():void
		{
			img = new FlxSprite(0, 0);
			img.loadGraphic(ImgScreen);
			//img.addAnimation("algo", [0, 1], 5, true);
			img.play("algo");
			this.add(img);
			
		}
		override public function update():void {
			if (FlxG.keys.justPressed("Z")) {
				var nuevo:PlayState = new PlayState;
				FlxG.state = nuevo;
				
				//pasar al estado siguiente
			}			
			
		}
		
	}

}
