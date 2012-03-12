package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	public class Registry 
	{
		
		[Embed (source = "/Data/Tile/tile_jam.png") ] public static var mapTilePNG:Class;
		public static var map:FlxTilemap;
		public static var mapObjects:FlxTilemap;
		//public static var levelExit:FlxPoint;
		//public static var arma:BulletManager;
		public static var fx:Fx = new Fx;
		public static var objetos:ObjetoManager;
		public static var tilesize:int 		= 32;
		//valores en tiles, no pixels
		public static var TILE_MAP_END:int = 305; 
		public static var TILE_MAP_SIZE_X:int = 320;
		public static var TILE_MAP_SIZE_Y:int = 19;
		public static var START_TILE:int = 6;
		public static var AC_JUMP:int 		= 0;
		public static var AC_RESTART:int 	= 1;
		public static var AC_ACCEL:int 		= 2;
		public static var AC_DECCEL:int 	= 3;
		public static var AC_TRIP:int 		= 4;
		public function Registry() 
		{
		}
		
	}

}