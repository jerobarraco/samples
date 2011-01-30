package com.game
{
	import org.flixel.*;
	import org.flixel.data.FlxKeyboard;

	public class HistoryEnd extends FlxState
	{
		[Embed(source = "/data/Historia/Jam-OS_OUT.png")] private var ImgScreen:Class;
		private var img:FlxSprite;
		
		override public function create():void
		{
			img = new FlxSprite(0, 0, ImgScreen);
			this.add(img);
		}
		override public function update():void {
			if (FlxG.keys.justPressed("Z")) {
				FlxG.state = new HistoryStart;
				//pasar al estado siguiente, en otras palabras, reiniciar
			}			
		}		
	}

}