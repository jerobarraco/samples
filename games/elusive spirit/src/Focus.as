package  
{
	import org.flixel.*;
	public class Focus extends FlxSprite {
		private var p:FlxSprite;
		public function Focus(player : FlxSprite) {
			super(player.x, player.y);
			p = player;
			visible = false;
		}
		override public function update():void 
		{
			super.update();
			x = p.x +Registry.tilesize*8;
			y = p.y;
		}
	}
}