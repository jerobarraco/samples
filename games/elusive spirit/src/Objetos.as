package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	/**
	 * ...
	 * @author Puccini
	 */
	public class Objetos extends FlxSprite
	{
		
		protected var activated:Boolean; 		// indica si esta activado (si es consumible) 
		protected var fadeout:Boolean;
		protected var fadein:Boolean;
		protected var timeFade:Number;
		
		public function Objetos(x:int , y:int , activated:Boolean) 
		{
			super(x, y);
			this.activated = activated;
			fadeout = false;
		}
		
		override public function update():void
		{
			super.update();
			
			if ( fadeout)
			{
				alpha -= FlxG.elapsed /timeFade;
				if (alpha < 0)
				{
					activated = false;
					
				}
			}
			
		}
		
		
		public function ChangeEstado():void
		{
			activated = !activated;
		}
		
		
		public function hit(player:FlxObject):void
		{
			
		}
		
		protected function fadeOut(time:Number):void
		{
			fadeout = true;
			timeFade = time;
		}
		
	}

}