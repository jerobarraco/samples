package com.game
{
	
	import org.flixel.*;
	import org.flixel.data.FlxKeyboard;
	
	public class HistoryStart extends FlxState
	{
		[Embed(source = "/data/Historia/01 Blue Screen Inicio.png")] private var ImgScreen:Class;
		[Embed(source = "/data/Historia/Welcome_1.png")] private var ImgWelcome1:Class;
		[Embed(source = "/data/Historia/Welcome_2.png")] private var ImgWelcome2:Class;
		[Embed(source = "/data/Historia/Welcome_3.png")] private var ImgWelcome3:Class;
		[Embed(source = "/data/Historia/Welcome_4.png")] private var ImgWelcome4:Class;
		[Embed(source = "/data/Historia/Warning_Screen.png")] private var ImgWarning:Class;
		[Embed(source = "/data/Historia/Warning_Code.png")] private var ImgWarningC:Class;
		[Embed(source = "/data/Historia/Load_AV.png")] private var ImgLoadAV:Class;
		[Embed(source = "/data/Historia/Barra_carga_antivirus_560x27.png")] private var ImgLoadAVC:Class;
		[Embed(source = "/data/Historia/ZtoStart.png")] private var ImgStart:Class;

		private var img:FlxSprite;
		private var welcome1:FlxSprite;
		private var welcome2:FlxSprite;
		private var welcome3:FlxSprite;
		private var welcome4:FlxSprite;
		private var warn:FlxSprite;
		private var warnc:FlxSprite;
		private var av:FlxSprite;
		private var avc:FlxSprite;
		private var start:FlxSprite;
		private var cont:Number=0;
		private var flag:Boolean=false;
		public var features:Array;
		
		private var progres_av:ProgressBar;
		
		override public function create():void
		{
			img = new FlxSprite(0, 0, ImgScreen);
			welcome1 = new FlxSprite(0, 0, ImgWelcome1);
			welcome2 = new FlxSprite(0, 0, ImgWelcome2);
			welcome3 = new FlxSprite(0, 0, ImgWelcome3);
			welcome4 = new FlxSprite(0, 0, ImgWelcome4);
			warn = new FlxSprite(0, 0, ImgWarning);
			warnc = new FlxSprite(207, 170, ImgWarningC);
			
			av = new FlxSprite(0, 0, ImgLoadAV);
			avc = new FlxSprite(38, 48, ImgLoadAVC);
			start = new FlxSprite(0, 0, ImgStart);
			
			this.add(img);
			this.add(welcome1);
			welcome1.visible = false;
			this.add(welcome2);
			welcome2.visible = false;
			this.add(welcome3);
			welcome3.visible = false;
			this.add(welcome4);
			welcome4.visible = false;
			this.add(warn);
			warn.visible = false;
			this.add(warnc);
			warnc.visible = false;
			this.add(av);
			av.visible = false;
			this.add(avc);
			avc.visible = false;
			this.add(start);
			start.visible = false;
			FlxG.level = 0;
			
			progres_av = new ProgressBar(this,38,48,561,30);
			progres_av.set_visible(false);
			
		}
		override public function update():void {
			cont+=FlxG.elapsed;
			if (!flag) {
				cont = 0;
				if (FlxG.keys.justPressed("Z")) {
					welcome1.visible = true;
					flag = true;
				}
			}
			if (cont>4&&cont<8) {
				welcome2.visible = true;
			}
			if (cont>8&&cont<12) {
				welcome3.visible = true;
			}
			if (cont>12&&cont<12.5) {
				welcome4.visible = true;
			}
			if (cont>12.5&&cont<16) {
				warn.visible = true;
				warnc.visible = (cont%2)<1;
			}
			if (cont>16&&cont<18) {
				av.visible = true;
				progres_av.set_visible(true);
				progres_av.animate_bar(75);
			}
			if (cont>18&&cont<19) {
				//avc.visible = true;
			}
			if (cont>19) {
				start.visible = true;
				
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

}
