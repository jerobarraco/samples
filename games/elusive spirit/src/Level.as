package  
{
	import flash.display.Sprite;
	import org.flixel.*;
	import org.flixel.plugin.*;
	import org.flixel.plugin.photonstorm.FlxExtendedSprite;
	import org.flixel.plugin.photonstorm.FlxScrollZone;
	
	/**
	 * ...
	 * @author Puccini
	 */
	public class Level extends FlxGroup
	{
		[Embed (source = "/Data/Levels/mapCSV_Group1_Map1.csv", mimeType = "application/octet-stream") ] private var mapCSV:Class;
		[Embed (source = "/Data/Levels/mapCSV_Group1_Map2.csv", mimeType = "application/octet-stream") ] private var mapoCSV:Class;
		

		
		
		
		
		public var map:FlxTilemap;
		public var mapObjects:FlxTilemap;
		//public var background:FlxTilemap;
		//private var bgarray:String=  "1, 1, 1, 1, 1, 1, 1";
		//public var back:FlxScrollZone;
		
		
		public var width:int;
		public var height:int;
		public static var tileW:int;
		public static var tileH:int;
		//public var fondo:FlxSprite;
		
		public function Level() 
		{
			map = new FlxTilemap;
			map.loadMap(new mapCSV, Registry.mapTilePNG, 32, 32, 0, 0, 1, 110);
			
			map.setTileProperties(58, FlxObject.UP, null, null, 1);
			map.setTileProperties(3, FlxObject.UP, null, null, 4);
			Registry.map = map;
			
			
			
			//background = new FlxTilemap;
			//background.loadMap(bgarray, fondoPNG , 622, 438, 0, 0, 1, 1);
			
			//fondo = new FlxSprite(0, 0, fondoPNG);
			
			//add(background);
			
			loadObjects();
			
			add(map);
			
			
			width = map.width;
			height = map.height;
			
		}
		
		
		public function loadObjects():void
		{
			
			mapObjects = new FlxTilemap;
			mapObjects.loadMap(new mapoCSV, Registry.mapTilePNG, 32, 32, 0, 0, 1, 110);
			Registry.mapObjects = mapObjects;
			
			for (var tx:int = 0; tx < Registry.TILE_MAP_SIZE_X; tx++)
			{
				for (var tyy:int = 0; tyy < Registry.TILE_MAP_SIZE_Y; tyy++)
				{
					if (mapObjects.getTile(tx, tyy) == 2)
					{
						Registry.objetos.AddSwtich(tx, tyy);
					}
					if (mapObjects.getTile(tx, tyy) == 8)
					{
						Registry.objetos.AddCaja(tx,tyy,true);
					}
					if (mapObjects.getTile(tx, tyy) == 21)
					{
						Registry.objetos.AddLowSpeed(tx, tyy,true,[0])
					}
					if (mapObjects.getTile(tx, tyy) == 27)
					{
						Registry.objetos.AddImpulsor(tx, tyy,false,[0])
					}
					if (mapObjects.getTile(tx, tyy) == 28)
					{
						Registry.objetos.AddLowSpeed(tx, tyy,false,[0])
					}
					if (mapObjects.getTile(tx, tyy) == 40)
					{
						Registry.objetos.AddFastSpeed(tx, tyy,true,[0])
					}
					
					if (mapObjects.getTile(tx, tyy) == 46)
					{
						Registry.objetos.AddFastSpeed(tx, tyy,false,[0])
					}
					
				}
			}
			
			
		}
	}

}