package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	/**
	 * ...
	 * @author Puccini
	 */
	public class Switches extends FlxSprite
	{
		
		//[Embed (source = "/Data/Sprites/chick.png") ] private var switchPNG:Class;
		//public var Cosas:FlxGroup;
		private var activated:Boolean; 
		private var time:Number;
		
		
		public function Switches(x:int,y:int) 
		{
			super(x*Registry.tilesize, y*Registry.tilesize);		// 16= tamaño del tile 
			loadGraphic(Registry.mapTilePNG, true, false, 32, 32, true);
			addAnimation("act", [2]);
			addAnimation("desact", [3]);
			play("act");
			//Cosas = new FlxGroup();
			activated = true;
			time = 0;
			
		}
		
		public function Add(Objeto:FlxObject):void
		{
			//Cosas.add(Objeto);
			
			
		}
		
		
		override public function update():void
		{
			super.update();
			
			
			if (!activated)
			{
				time += FlxG.elapsed;
				if ( time > 1.3)
				{
					activated = true;
					time = 0;
				}
				play("desact");
			}else {
				play("act");
			}
		}
		
		public function PushSwitch():void
		{
			if (activated)
			{
				//Cosas.callAll("ChangeEstado");
				Registry.objetos.ObjetoGroup.callAll("ChangeEstado");
				Registry.fx.explodeBlock(x, y);
				activated = false;
			}
		}
		
		
		
		
	}

}