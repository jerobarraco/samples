package com.game
{
	import org.flixel.*;
	import org.flixel.data.FlxKeyboard;

	public class HistoryLoader extends FlxState
	{
		[Embed(source = "/data/Historia/Black_Screen_Scroll.png")] private var ImgScroll:Class;
		
		private var scroll:FlxSprite;
		private var cont:Number;
		private var cont2:Number;
		
		override public function create():void
		{
			scroll = new FlxSprite(0, 460, ImgScroll);		
			this.add(scroll);
			cont=0;
			cont2=0;
		}
		override public function update():void {
			cont+=FlxG.elapsed;
			if (cont>1){			
				scroll.visible=true;
				scroll.y-=10;				
			}
			
			if (scroll.y<=-600) {
				scroll.y=-600;
				cont2+=FlxG.elapsed;
			}

			if (cont2>1.5){
				FlxG.state = new HistoryStart;
				//pasar al estado siguiente, en otras palabras, reiniciar
			}
		}		
	}

}
