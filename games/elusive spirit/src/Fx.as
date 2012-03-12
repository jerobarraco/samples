package  
{
	import org.flixel.*;

	public class Fx extends FlxGroup
	{
		private var pixels:FlxGroup;
		
		public function Fx() 
		{
			super();
			
			pixels = new FlxGroup();
			
			//	Here we create an FlxGroup containing 40 FlxEmitters, all the same, used when the aliens are shot/explode
			for (var i:int = 0; i < 40; i++)
			{
				var tempPixel:FlxEmitter = new FlxEmitter();
				tempPixel.setSize(8, 8);
				tempPixel.gravity = 200;
				tempPixel.setXSpeed(-50, 50);
				tempPixel.setYSpeed( -30, -60);
				tempPixel.setRotation(0, 0);	// VITAL!!!
				tempPixel.makeParticles(Pixel, 50, 16, true, 0);
				tempPixel.lifespan = 2;
				tempPixel.exists = false;
				pixels.add(tempPixel);
			}
			
			add(pixels);
			
		}
		
		override public function update():void
		{
			super.update();
		}
		
		public function explodeBlock(ax:int, ay:int):void
		{
			var pixel:FlxEmitter = FlxEmitter(pixels.getFirstAvailable());
			
			if (pixel)
			{
				pixel.x = ax;
				pixel.y = ay;
				pixel.start(true);
			}
		}
		
	}

}