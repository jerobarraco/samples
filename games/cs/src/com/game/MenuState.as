package com.game
{//Cuando muere y debe dejar una parte
	import org.flixel.*;
	

	public class MenuState extends FlxState
	{
		[Embed(source = '/data/Musica/sound.swf', symbol = 'BIT_crazy_menu.mp3')] private var MusMain1:Class;
		[Embed(source = "/data/Menu/base.png")] private var Img1:Class;
		[Embed(source = "/data/Menu/shield on.png")] private static var im_shield:Class;
		[Embed(source = "/data/Menu/shoot1 on.png")] private static var im_shoot1:Class;
		[Embed(source = "/data/Menu/shoot3 on.png")] private static var im_home:Class;
		[Embed(source = "/data/Menu/engine1 on.png")] private static var im_engine1:Class;
		[Embed(source = "/data/Menu/engine1 over.png")] private static var im_engine1_over:Class;
		[Embed(source = "/data/Menu/engine2 on.png")] private static var im_engine2:Class;
		[Embed(source = "/data/Menu/engine2 over.png")] private static var im_engine2_over:Class;
		
		[Embed(source = "/data/Menu/magnet on.png")] private static var im_magnet:Class;
		[Embed(source = "/data/Menu/magnet over.png")] private static var im_magnet_over:Class;
		
		[Embed(source = "/data/Menu/resume on.png")] private static var im_resume:Class;
		
		private var base:FlxSprite;
		private static var Sshield:FlxSprite = new FlxSprite(350, 120, im_shield);
		private static var sShoot1:FlxSprite = new FlxSprite(350, 120, im_shoot1);
		private static var Shome:FlxSprite = new FlxSprite(350, 120, im_home);
		private static var sEngine1:FlxSprite = new FlxSprite(0, 0, im_engine1);
		private static var sEngine1Over:FlxSprite = new FlxSprite(0, 0, im_engine1_over);
		private static var sEngine2:FlxSprite = new FlxSprite(0, 0, im_engine2);
		private static var sEngine2Over:FlxSprite = new FlxSprite(0, 0, im_engine2_over);
		private static var sMagnet:FlxSprite = new FlxSprite(0, 0, im_magnet);
		private static var sMagnetOver:FlxSprite = new FlxSprite(0, 0, im_magnet_over);
		private static var sResume:FlxSprite = new FlxSprite(390, 410, im_resume);
		
		
		private var theme:FlxSound = new FlxSound;
		
		private var mitexto:FlxText;
		private var prompt:FlxText;
		private var prompttime:Number;
		private var features:Array;
		private var options:Array = ["ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"] ;
		private var featnames:Array =
			[ 
				"Primary Cannon", "Secondary Cannon", "Auto-defense Proyectile", 
				"External Protection Shield", "Bomb Launcher", "Propeller 1" , "Propeller 2", "Propeller 3", "Magnet"
			];
		//ojo con estos dos arrays feos , dependen de ship.features, 
		//los features no estan bien definidos aun, asi que hay que tener cuidado con todo eso, 
		private var active_bar:ProgressBar;
		private var button:FlxButton;
		override public function create():void
		{
			base= new FlxSprite(0, 0, Img1);
			this.add(base);
			
			/*prompt = new FlxText(25, 400, 30, "_ ");
			prompt.setFormat(null, 16);
			this.add(prompt);*/
			prompttime = 2;
			
			theme.loadEmbedded(MusMain1);
			theme.play();
			
			active_bar = new ProgressBar(this, 350, 60, 200 , 20, 0xff000000,  0xff000000, 0xff5050A0,  2, false);
			//inicializo la pbar en 1000 para permitir luego que los archivos puedan dar mas o menos puntaje
			active_bar.total_value = 1000;
			active_bar.update_value(0);
			active_bar.set_visible(true);
			//todo poner el % en texto arriba de la barra
			
			
			this.add(sResume);
			//FlxG.mouse.show(ImgCursor, 16, 16); //img, width, height
			FlxG.mouse.show();
			
			//350,120 (el anterior)
			//todo crear los botones solo para los titulos sino es un bardo seleccionar 
			// va a haber que tamb crear una img para el resto de la parteosea, 3 imgs por parte, normal, titulo normal y titulo activo
			//TODO poner esto en los condicionales de los feats
			var btn:FlxButton = new FlxButton(360, 205, this.onButton); 
			btn.loadGraphic(sEngine2, sEngine2Over);
			this.add(btn);
			
			btn = new FlxButton(355, 150, this.onButton); 
			btn.loadGraphic(sEngine1, sEngine1Over);
			this.add(btn);
			
			btn = new FlxButton(460, 220, this.onButton); 
			btn.loadGraphic(sMagnet, sMagnetOver);
			this.add(btn);
			
			
		}
		public function onButton():void {
			//tiene que estar en un public sino no anda.. s
			this.goToPlay();
		}
		public function SetFeats(feats:Array):void {
			var texto:String;
			texto = "";
			var c:int = feats.length;
			for (var i:int = 0; i < feats.length; i++) {
				texto += "\n " + (i + 1).toString() + " -  " + featnames[i] ;
				if (!feats[i]) {//TODO color rojo
					texto += " [Finished]";
					c -= 1;
				}
			}
			active_bar.total_value = feats.length;
			active_bar.update_value(c);
			
			if (feats[3]) { this.add(Sshield); }//todo crear un array
			if (feats[0]) { this.add(sShoot1); }
			if (feats[2]) { this.add(Shome); }
			//if (feats[5]) { this.add(sEngine1); }
			//if (feats[6]) { this.add(sEngine2); }
			if (feats[8]) { this.add(sMagnet); }
			
			var toptext:String;
			//toptext = "Jam-OS >\n Status Report\n Active System Status: " + (c/feats.length*100).toString() + "% \n\nERROR: A fatal error has ocurred, a process must be terminated to continue:\n" 
			toptext = "FATAL ERROR HAS OCURRED\nA process must be terminated to continue:\n";
			toptext += texto;
			if (c == 0) {
				FlxG.state = new HistoryEnd;
				return;
				toptext += "\n\nExtermination inmineHDR   -   (j×s)  IiCCPsRGB IEC61966-2.1  xÚSwX“÷>ß÷eVBØð±—l œHUÄ‚Õ Hˆâ (¸gAŠˆZ‹U\8îÜ§µ}zïííû×û¼çœçüÎyÏ€&‘æ¢j 9R…<:ØOHÄÉ½€HàæËÂgÅ  ðyx~t°?üäœ—›#ÑÁþ8?çæäáæfçlïôÅ¢þkðo>!ñßþ¼"
				//todo: poner un timer e ir a la pantalla de error final
			}
			
			mitexto = new FlxText(35, 150, 350, toptext);
			mitexto.setFormat(null, 12);
			this.add(mitexto);
			
			features = feats;
			
		}
		override public function update():void {
			super.update();
			prompttime -= FlxG.elapsed;
			if (prompttime < 0) {
				prompttime = 2;//todo usar flxg.elapsed
				sResume.visible = ! sResume.visible;
			}
			for (var i:int = 0; i < features.length; i++) {
				if (features[i] && FlxG.keys.justPressed(options[i]) ){
					features[i] = false;
					//load another state
					goToPlay();
					return;
				}
			}
			
		}
		private function goToPlay():void {
			theme.stop();
			FlxG.mouse.hide();
			var nuevo:PlayState = new PlayState;
			FlxG.state = nuevo;
			nuevo.set_feats(features);
			return;
		}
		
	}
//350, 120
}
