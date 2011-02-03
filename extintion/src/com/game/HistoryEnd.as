package com.game
{
	import org.flixel.*;
	import org.flixel.data.FlxKeyboard;

	public class HistoryEnd extends FlxState
	{
		[Embed(source = "/data/Historia/Jam-OS_OUT.png")] private var ImgScreen:Class;
		[Embed(source = "/data/Historia/Jam-OS_Terminate_blink.png")] private var Imgblk:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'MisterioXP.mp3')] private var MusEnd:Class;
		
		private var theme:FlxSound = new FlxSound;
		
		private var img:FlxSprite;
		private var blk:FlxSprite;
		private var cont:Number=1;
		
		override public function create():void
		{
			img = new FlxSprite(0, 0, ImgScreen);
			blk = new FlxSprite(130, 250, Imgblk);
			this.add(img);
			this.add(blk);
			theme.loadEmbedded(MusEnd, true);
			theme.play();
		}
		override public function update():void {
			if (FlxG.keys.justPressed("ENTER") || FlxG.keys.justPressed("ESCAPE") ) {
				theme.stop();
				FlxG.state = new HistoryLoader;
				//pasar al estado siguiente, en otras palabras, reiniciar
			}
			cont-=FlxG.elapsed;
			if (cont<0){
				blk.visible=!blk.visible;
				cont=1;
			}		
		}		
	}

}
