package  
{
	import org.flixel.*;
	/**
	 * ...
	 * @author ...
	 */
	public class Position extends FlxSprite
	{
		[Embed(source = '/Data/Sprites/caritas.png')] private var pospng:Class;
		public var shadow:Boolean;
		public function Position(shadow:Boolean) {
			super();
			this.shadow = shadow;
			loadGraphic(pospng, true, false, 20, 20, true);
			addAnimation("player", [0], 0, false);
			addAnimation("ghost", [1], 0, false);
			if (shadow) {
				play("ghost");
				setAlive(false);
			}
			else play("player");
			scrollFactor.x = scrollFactor.y = 0;
		}
		override public function update():void {
			super.update();
			var px:int;
			if (shadow) {
				px = PlayState.shadow.x;
			}else {
				px = PlayState.player.x;
			}
			var w:int = Registry.tilesize * Registry.TILE_MAP_END;
			x = (px / w) * FlxG.camera.width;
		}
		public function setAlive(show:Boolean):void {
			visible = show;
			active = show;
		}
	}

}