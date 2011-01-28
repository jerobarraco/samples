package com.game
{
	import org.flixel.FlxGroup;
	import org.flixel.FlxState;

	public class PlayState extends FlxState
	{
		
		
		public static var lyr_down:FlxGroup;
		public static var lyr_middle:FlxGroup;
		public static var lyr_shots:FlxGroup;
		public static var lyr_top:FlxGroup;
		
		override public function create():void
		{
			lyr_down = new FlxGroup;
			lyr_middle = new FlxGroup;
			lyr_shots = new FlxGroup;
			lyr_top = new FlxGroup;
			
			var player:Ship = new Ship(20,20);
			lyr_middle.add(player);
			
			for(var i:int = 0; i<10; i++)
			{
				var shot:Shots = new Shots(0,0);
				lyr_shots.add(shot);
			}
			
			this.add(lyr_down);
			this.add(lyr_middle);
			this.add(lyr_shots);
			this.add(lyr_top);
		}
	}
}