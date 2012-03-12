package  
{
	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.geom.Rectangle;

	public class Pixel extends Bitmap
	{
		/**
		 * This class is simple a strip of pixels used for when the aliens are shot
		 * It's used in Fx.as as the Class given to the FlxEmitters
		 * 
		 * It could easily be a PNG instead, but I like keeping it here so it's really easy to change the colours/size
		 * and see the results immediately. Once I'm 100% happy with it, I would then usually bake it into a PNG
		 */
		public function Pixel()
		{
			super(new BitmapData(16, 2, false, 0x000000));
			
			bitmapData.fillRect(new Rectangle(0, 0, 2, 2), 0xffff00);
			bitmapData.fillRect(new Rectangle(2, 0, 2, 2), 0xffbb00);
			bitmapData.fillRect(new Rectangle(4, 0, 2, 2), 0xbbff00);
			bitmapData.fillRect(new Rectangle(6, 0, 2, 2), 0xbbbb00);
			bitmapData.fillRect(new Rectangle(8, 0, 2, 2), 0xfbfb00);
			bitmapData.fillRect(new Rectangle(10, 0, 2, 2), 0xbfbf00);
			bitmapData.fillRect(new Rectangle(12, 0, 2, 2), 0xdddd00);
			bitmapData.fillRect(new Rectangle(14, 0, 2, 2), 0xdfdf00);
		}
		
	}

}