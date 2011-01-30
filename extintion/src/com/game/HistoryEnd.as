package com.game
{
	import org.flixel.*;
	import org.flixel.data.FlxKeyboard;

	public class HistoryEnd extends FlxState
	{
		[Embed(source = "/data/Historia/Jam-OS_OUT.png")] private var ImgScreen:Class;
		[Embed(source = "/data/Historia/Jam-OS_Terminate_blink.png")] private var Imgblk:Class;
		private var img:FlxSprite;
		private var blk:FlxSprite;
		private var cont:Number=60;
		
		override public function create():void
		{
			img = new FlxSprite(0, 0, ImgScreen);
			blk = new FlxSprite(130, 250, Imgblk);			
			this.add(img);
			this.add(blk);
		}
		override public function update():void {
			if (FlxG.keys.justPressed("Z")) {
				FlxG.state = new HistoryStart;
				//pasar al estado siguiente, en otras palabras, reiniciar
			}
			cont-=FlxG.elapsed;
			if (cont<0){
				blk.visible=!blk.visible;
				cont=60;
			}		
		}		
	}

}
