package  
{
	
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	/**
	 * ...
	 * @author Puccini
	 */
	public class Caja extends Objetos
	{
		
		//[Embed (source = "/Data/Sprites/caja.png") ] private var cajaPNG:Class;
		
		public function Caja(x:int, y:int, activated:Boolean) 
		{
			super(x*Registry.tilesize, y*Registry.tilesize, activated);				// 16 = tamaño tile
			loadGraphic(Registry.mapTilePNG,  true,  true, 32, 32, true);
			addAnimation("act", [8], 0, false);
			addAnimation("desact", [9], 0, false);
			//play("act");
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
				Player ( player).trip();
				
				Registry.fx.explodeBlock(x, y);
				
				activated = !activated;
				//FlxG.fade(0xff000000, 1, kill);
				this.fadeOut(0.4);
				//kill();
			}
		}
		
		override public function ChangeEstado():void
		{
			
		}
		
		
	}

}