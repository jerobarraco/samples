package com.game
{
	import org.flixel.FlxBackdrop;
	import org.flixel.FlxG;
	import org.flixel.FlxGroup;
	import org.flixel.FlxPoint;
	import org.flixel.FlxState;
	import org.flixel.FlxU;

	public class PlayState extends FlxState
	{
		[Embed(source = "/data/fondos/fondo01.png")] private var ImgFondo1:Class;
		[Embed(source = "/data/fondos/fondo02.png")] private var ImgFondo2:Class;
		
		public static var lyr_back:FlxGroup;
		public static var lyr_player:FlxGroup;
		public static var lyr_enemy:FlxGroup;
		public static var lyr_Pshots:FlxGroup;
		public static var lyr_Eshots:FlxGroup;
		public static var lyr_top:FlxGroup;
		public static var lyr_hud:FlxGroup;
		
		public static var enemies_array:Array = new Array;
		
		public static var player:Ship;
		
		public static var data_timer:Number=30;
		
		private var levels:Levels;
		
		private var ene_spawn_timer:Number = 10;
		private var ene_spawn_timer_max:Number = 2;
		
		private var video:Video;
		
		override public function create():void
		{
			lyr_back = new FlxGroup;
			lyr_player = new FlxGroup;
			lyr_enemy = new FlxGroup;
			lyr_Pshots = new FlxGroup;
			lyr_Eshots = new FlxGroup;
			lyr_top = new FlxGroup;
			
			player = new Ship(40, FlxG.height/2);
			
			for(var i:int = 0; i<10; i++)
			{
				var shot:Shots = new Shots(0,0);
				lyr_Pshots.add(shot);
			}
			
			for(var j:int = 0; j<10; j++)
			{
				var enes:Enemy = new Enemy(0,0);
				lyr_enemy.add(enes);
			}
			
			enemies_array = lyr_enemy.members;
			
//			video = new Video();
//			video.Escala = 1;
//			video.zoom = 1;
			
			var back:FlxBackdrop = new FlxBackdrop(ImgFondo1);
			back.velocity.x = -80;
			lyr_back.add(back);
			
			back = new FlxBackdrop(ImgFondo2);
			back.velocity.x = -110;
			lyr_back.add(back);
			
			levels = new Levels(FlxG.level);
			add(levels);
			
			this.add(lyr_back);
			this.add(lyr_Pshots);
			this.add(lyr_Eshots);
			this.add(lyr_player);
			this.add(lyr_enemy);
			this.add(lyr_top);
			this.add(lyr_Pshots);
			this.add(lyr_Eshots);
		}
		
//		private var enemy:int=1;
		
		override public function update():void
		{
//			ene_spawn_timer+=FlxG.elapsed;
//			if(ene_spawn_timer>ene_spawn_timer_max)
//			{
//				var tmp:Enemy;
//				for each (tmp in lyr_enemy.members) {
//					if (tmp.dead) {
//							tmp.reset(FlxG.width, 0);
//							enemy += 1;
//							break;
//					}
//				}
				
//				ene_spawn_timer=0;
//			}
			
			//colisiones
			FlxU.overlap(lyr_Pshots, lyr_enemy, shot_enemy);
			FlxU.overlap(lyr_enemy, lyr_player, player.Hit);
			FlxU.overlap(lyr_Eshots, lyr_player, player.Hit2);
			super.update();
			
			if(player.dead)
			{
				Ship.main_theme.stop();
				var nuevo:MenuState = new MenuState;
				FlxG.state = nuevo;
				nuevo.SetFeats(player.features);
			}
			data_timer -= FlxG.elapsed;
			if (data_timer < 0) {
				data_timer = 10;
				Ship.main_theme.stop();
				
				var nuevo2:HistoryPart = new HistoryPart;
				FlxG.state = nuevo2;
				nuevo2.features = player.features;				
			}
		}
		
		public static function get_single_enemy():Enemy
		{
			var enes:Array = enemies_array;
			var ene:Enemy;
			
			for (var i:uint = 0; i < enes.length; i++)
			{
				ene = enes[i];
				if (!ene.exists)
				{
					return ene;
				}
			}
			//sino se crea y se agrega al array (push) dentro de la capa de sprites
			ene = new Enemy(0,0);
			enemies_array.push(lyr_enemy.add(ene));
			return ene;
		}
		
		public function shot_enemy(shot:Shots, ene:Enemy):void
		{
			shot.kill();
			
			if(ene.health == 1)
			{
				ene.kill();
			}
				
			ene.health -=1;
		}
		public function set_feats(feats:Array):void {
			for (var i:int = 0; i < feats.length; i++) {
				player.SetFeature(i, feats[i]);
			}
		}
	}
}