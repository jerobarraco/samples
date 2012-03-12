package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	
	/**
	 * 		--- Fast Speed ---
	 * aumenta velocidad 
	 * 
	 */
	public class FastSpeed extends Objetos
	{
		
		//[Embed (source = "/Data/Sprites/cat.png") ] private var objetoPNG:Class;
		
		public function FastSpeed(x:int, y:int, activated:Boolean) 
		{
			
			super(x*Registry.tilesize, y*Registry.tilesize, activated);				// 16 = tamaño tile
			loadGraphic(Registry.mapTilePNG,  true,  true, 32, 32, true);
			addAnimation("act", [40], 0, false);
			addAnimation("desact", [46], 0, false);
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
			if (activated){
				(Player)(player).accell();				
				Registry.fx.explodeBlock(x, y);
				activated = false;
			}
		}
	}

}