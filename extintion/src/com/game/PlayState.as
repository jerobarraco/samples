package com.game
{
	import org.flixel.FlxG;
	import org.flixel.FlxGroup;
	import org.flixel.FlxState;
	import org.flixel.FlxU;

	public class PlayState extends FlxState
	{
		public static var lyr_player:FlxGroup;
		public static var lyr_enemy:FlxGroup;
		public static var lyr_Pshots:FlxGroup;
		public static var lyr_top:FlxGroup;
		
		private static var player:Ship;
		
		private var ene_spawn_timer:Number = 0;
		private var ene_spawn_timer_max:Number = .2;
		
		override public function create():void
		{
			lyr_player = new FlxGroup;
			lyr_enemy = new FlxGroup;
			lyr_Pshots = new FlxGroup;
			lyr_top = new FlxGroup;
			
			player = new Ship(20, 20);
			
			for(var i:int = 0; i<10; i++)
			{
				var shot:Shots = new Shots(0,0);
				lyr_Pshots.add(shot);
			}
			
			for(var j:int = 0; j<10; j++)
			{
				var enes:Enemy = new Enemy(0,0);
				enes.kill();
				lyr_enemy.add(enes);
			}
			
			
			this.add(lyr_Pshots);
			this.add(lyr_player);
			this.add(lyr_enemy);
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
			
			//colisiones
			FlxU.overlap(lyr_Pshots, lyr_enemy, shot_enemy);
			FlxU.overlap(lyr_enemy, lyr_player, player.Hit);
			super.update();
		}
		
		public function shot_enemy(shot:Shots, ene:Enemy):void
		{
			shot.kill();
			ene.kill();
		}
	}
}