package com.game 
{
	import org.flixel.*
	import utils.Rnd;
	import com.Game.Recycler;

	public class SPFile extends Spawner	{
		public function SPFile(grupo : Recycler) 
		{
			super(grupo, 10, 100, 100);
		}
		override public function DoSpawn():void {
			var coso:FlxObject = migrupo.getInstance();
			coso.reset(FlxG.width, FlxG.height*Math.random());
		}
	}
}