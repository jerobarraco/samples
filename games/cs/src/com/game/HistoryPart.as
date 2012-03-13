package com.game
{//Cada parte de la historia
	import org.flixel.*;

	public class HistoryPart extends FlxState
	{
		private var img:FlxSprite;
		
		[Embed(source = "/data/Historia/Screens/00.png")] private var Img1:Class;
		[Embed(source = "/data/Historia/Screens/01.png")] private var Img2:Class;
		[Embed(source = "/data/Historia/Screens/02.png")] private var Img3:Class;
		[Embed(source = "/data/Historia/Screens/03.png")] private var Img4:Class;
		[Embed(source = "/data/Historia/Screens/04.png")] private var Img5:Class;
		
		[Embed(source = "/data/Historia/Fake/00.png")] private var Img6:Class;
		[Embed(source = "/data/Historia/Fake/01.png")] private var Img7:Class;
		[Embed(source = "/data/Historia/Fake/02.png")] private var Img8:Class;
		[Embed(source = "/data/Historia/Fake/03.png")] private var Img9:Class;
		[Embed(source = "/data/Historia/Fake/04.png")] private var Img10:Class;
		[Embed(source = "/data/Historia/Fake/05.png")] private var Img11:Class;
		[Embed(source = "/data/Historia/Fake/06.png")] private var Img12:Class;
		
		[Embed(source = '/data/Musica/sound.swf', symbol = 'EsperaXP.mp3')] private var MusEspera:Class;
		
		private var theme:FlxSound = new FlxSound;
		
		private var Imgs:Array = [Img1, Img2, Img3, Img4, Img5, Img6, Img7, Img8, Img9, Img10, Img11, Img12];
		private static var showed:Array = [false, false, false, false, false, false, false, false, false, false, false, false];
		
		private var promptframes:int;
		private var prompt:FlxText;
		public var features:Array;
		override public function create():void
		{
			var avail:Array = [];
			//creamos un array con las dipsonibles
			for (var i:int = 0; i < showed.length; i++) {
				if (!showed[i]) {
					avail.push(i);
				}
			}
			//si no quedan disponible vamos al history end
			if (avail.length == 0) {
				//reseteamos los showed para poder seguir jugando despues
				for (var j:int = 0; j < showed.length; j++) {
					showed[j] = false;
				}
				FlxG.state = new HistoryEnd;
				return;				
			}
			var r: int = Math.random() * avail.length;
			var usar:int = avail[r];
			showed[usar] = true;
			var sprite:FlxSprite = new FlxSprite(x, y, Imgs[usar]);
			this.add(sprite);
			
			var mitexto:FlxText = new FlxText(10, 420, 600, "Press ENTER to continue");
			mitexto.setFormat("System", 12);
			this.add(mitexto);
			prompt = new FlxText(10, 450, 30, "_ ");
			prompt.setFormat("System", 12);
			this.add(prompt);
			promptframes = 20;
			
			theme.loadEmbedded(MusEspera, true);
			theme.play();
			
		}
		override public function update():void {
			promptframes -= 1;
			if (promptframes < 0) {
				promptframes = 20;
				prompt.visible = !prompt.visible;
			}
			if (FlxG.keys.justPressed("ENTER") ) {
				theme.stop()
				FlxG.level += 1;
				if (FlxG.level > 6) FlxG.level = 6;
				var state:PlayState = new PlayState;
					
				FlxG.state = state;
				state.set_feats(features);
			}
		}
		
	}

}
