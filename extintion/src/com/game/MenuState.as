package com.game
{
	import org.flixel.*;
	

	public class MenuState extends FlxState
	{
		private var mitexto:FlxText;
		private var prompt:FlxText;
		private var promptframes:int;
		private var features:Array;
		private var options:Array = ["ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"] ;
		private var featnames:Array =
			[ 
				"Primary Cannon", "Secondary Cannon", "Auto-defense Proyectile System", 
				"External Protection Shield System", "Bomb Launcher", "Propeller 1" , "Propeller 2", "Propeller 3"];
				
		override public function create():void
		{
			prompt = new FlxText(25, 400, 30, "_ ");
			prompt.setFormat(null, 16);
			this.add(prompt);
			promptframes = 20;
		}
		public function SetFeats(feats:Array):void {
			var texto:String;
			texto = "";
			var c:int = feats.length;
			for (var i:int = 0; i < feats.length; i++) {
				texto += "\n " + (i + 1).toString() + " -  " + featnames[i] ;
				if (!feats[i]) {
					texto += " [Finished]";
					c -= 1;
				}
			}
			var toptext:String;
			toptext = "Jam-OS >\n Status Report\n Active System Status: " + (c/feats.length*100).toString() + "% \n\nERROR: A fatal error has ocurred, a process must be terminated to continue:\n" 
			toptext += texto;
			if (c == 0){
				toptext += "\n\nExtermination inmineHDR   -   (j×s)  IiCCPsRGB IEC61966-2.1  xÚSwX“÷>ß÷eVBØð±—l œHUÄ‚Õ Hˆâ (¸gAŠˆZ‹U\8îÜ§µ}zïííû×û¼çœçüÎyÏ€&‘æ¢j 9R…<:ØOHÄÉ½€HàæËÂgÅ  ðyx~t°?üäœ—›#ÑÁþ8?çæäáæfçlïôÅ¢þkðo>!ñßþ¼"
				//todo: poner un timer e ir a la pantalla de error final
			}
			mitexto = new FlxText(10, 10, 1000, toptext);
			mitexto.setFormat(null, 12);
			this.add(mitexto);
			features = feats;
		}
		override public function update():void {
			promptframes -= 1;
			if (promptframes < 0) {
				promptframes = 20;
				prompt.visible = !prompt.visible;
			}
			for (var i:int = 0; i < features.length; i++) {
				if (features[i] && FlxG.keys.justPressed(options[i]) ){
					features [i] = false;
					//load another state
					var nuevo:PlayState = new PlayState;
					FlxG.state = nuevo;
					nuevo.set_feats(features);
					return;
				}
			}
		}
		
	}

}
