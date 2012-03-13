package com.game 
{
	import org.flixel.FlxText;
	import org.flixel.FlxG;
	public class Points extends FlxText
	{
		private var anim:Number = 0;
		private var tmp_color: Number = 0xFFFFFFFF;
		public function Points(X:Number=0, Y:Number=0):void 
		{
			super(X, Y, 50, "0");
			setFormat(null, 8, 0xFFFFFFFF, "center");
		}
		override public function reset(X:Number, Y:Number):void {
			super.reset(X, Y);
			anim = 0;
			tmp_color = 0xFFFFFFFF;
			alpha = 1.0;
		}
		public function set_value(Value:Number = 0):void {
			text = String(Value);
		}
		override public function update():void {
			super.update();
			y -= FlxG.elapsed * 20;
			alpha -= FlxG.elapsed;//esto cierra porque limitamos la animacion a 1 segundo, si eso cambiara habria que hacer algo aca
			//tmp_color -= Math.floor(FlxG.elapsed*255*255*255);//es un intento de decrementar solo el alpha se ve mas divertido que el otro
			color = tmp_color;
			anim += FlxG.elapsed;
			if (anim > 1) { kill(); }
		}
		
	}

}