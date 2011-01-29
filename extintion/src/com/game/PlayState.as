package com.game
{
	import org.flixel.FlxG;
	import org.flixel.FlxGroup;
	import org.flixel.FlxState;

	public class PlayState extends FlxState
	{
		public static var lyr_player:FlxGroup;
		public static var lyr_enemy:FlxGroup;
		public static var lyr_shots:FlxGroup;
		public static var lyr_top:FlxGroup;
		
		private var ene_spawn_timer:Number = 0;
		private var ene_spawn_timer_max:Number = .2;
		
		override public function create():void
		{
			lyr_player = new FlxGroup;
			lyr_enemy = new FlxGroup;
			lyr_shots = new FlxGroup;
			lyr_top = new FlxGroup;
			
			var player:Ship = new Ship(20,20);
			lyr_player.add(player);
			
			for(var i:int = 0; i<10; i++)
			{
				var shot:Shots = new Shots(0,0);
				lyr_shots.add(shot);
			}
			
			for(var j:int = 0; j<10; j++)
			{
				var enes:Enemy = new Enemy(0,0);
				enes.kill();
				lyr_enemy.add(enes);
			}
			
			this.add(lyr_player);
			this.add(lyr_enemy);
			this.add(lyr_shots);
			this.add(lyr_top);
		}
		
		private var enemy:int=1;
		
		override public function update():void
		{
			ene_spawn_timer+=FlxG.elapsed;
			if(ene_spawn_timer>ene_spawn_timer_max)
			{
				var tmp:Enemy;
				for each (tmp in lyr_enemy.members) {
					if (tmp.dead) {
							tmp.reset(FlxG.width, Math.random() * FlxG.height);
							enemy += 1;
							break;
					}
				}
				ene_spawn_timer=0;
			}
			super.update();
		}
	}
}