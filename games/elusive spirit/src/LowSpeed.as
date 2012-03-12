package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	/**
	 * 			---- low speed ---
	 * 
	 * disminuye velocidad 
	 * 
	 */
	public class LowSpeed extends Objetos
	{
		
		public function LowSpeed(x:int, y:int, activated:Boolean) 
		{
			super(x*Registry.tilesize, y*Registry.tilesize, activated);				// 16 = tamaño tile
			loadGraphic(Registry.mapTilePNG,  true,  true, 32, 32, true);
			addAnimation("act", [22], 0, false);
			addAnimation("desact", [28], 0, false);
			//play("stop");
			velocity.x = 0;
			velocity.y = 0;
			
		}
		
		override public function update():void
		{
			super.update();
			if (activated)
			{
				play("act");
			}else {
				play("desact");
			}
		}
		override public function hit(player:FlxObject):void
		{
			if (activated)
			{
				(Player)(player).deccell();
				Registry.fx.explodeBlock(x, y);
				activated = !activated;
			}
		}
		
		
	}

}