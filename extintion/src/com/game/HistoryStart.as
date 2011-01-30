package com.game
{
	import org.flixel.*;
	import org.flixel.data.FlxKeyboard;
	
	public class HistoryStart extends FlxState
	{
		[Embed(source = "/data/Historia/01 Blue Screen Inicio.png")] private var ImgScreen:Class;
		private var img:FlxSprite;
		public var features;
		override public function create():void
		{
			img = new FlxSprite(0, 0, ImgScreen);
			this.add(img);
			FlxG.level = 0;
		}
		override public function update():void {
			if (FlxG.keys.justPressed("Z")) {
				var nuevo:PlayState = new PlayState;
				FlxG.state = nuevo;
				//importante para cuando se va a jugar de nuevo
				nuevo.set_feats([true, true, true, true, false, true, true, true ]);
				//pasar al estado siguiente
			}			
		}		
	}

}
