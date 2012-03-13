package com.game
{
	import com.game.dialog.DialogManager;
	import com.game.Explotion;
	import com.game.Points;
	import com.game.ProgressBar;
	
	import flash.display.BlendMode;
	
	import org.flixel.FlxBackdrop;
	import org.flixel.FlxG;
	import org.flixel.FlxGroup;
	import org.flixel.FlxPoint;
	import org.flixel.FlxSprite;
	import org.flixel.FlxState;
	import org.flixel.FlxText;
	import org.flixel.FlxU;
	
	public class PlayState extends FlxState
	{
		[Embed(source = "/data/Fondos/fondo01.png")] private var ImgFondo1:Class;
		[Embed(source = "/data/Fondos/fondo02.png")] private var ImgFondo2:Class;		
		
		public static var lyr_back:FlxGroup;
		public static var lyr_player:FlxGroup;
		
		public static var lyr_enemy:Recycler;
		public static var lyr_Pshots:Recycler;
		public static var lyr_Plazer:Recycler;
		public static var lyr_Eshots:Recycler;
		public static var lyr_points:Recycler;
		public static var lyr_explotions:Recycler;
		public static var lyr_files:Recycler;
		
		public static var lyr_top:FlxGroup;
		public static var lyr_hud:FlxGroup;
		
		public static var player:Ship;
		public static var pLifeBar:ProgressBar;
		public static var pTimeBar:ProgressBar;
		
		private var levels:Levels;
		
		public static var data_timer:int;
		private var dm_timer:Number = 10;//prueba
		
		private static var score:Number = 0;
		private static var score_txt:FlxText;
		private var video:Video;
		
		public static var trail_draw:TrailDrawer;
		
		private static var dm:DialogManager=new DialogManager();
		override public function create():void
		{
			//dm = new DialogManager();
			lyr_back = new FlxGroup;
			lyr_player = new FlxGroup;
			lyr_enemy = new Recycler(Enemy);
			lyr_Pshots = new Recycler(Shots);
			lyr_Plazer = new Recycler(Lazer);
			lyr_Eshots = new Recycler(Shots);
			lyr_points = new Recycler(Points);
			lyr_explotions = new Recycler(Explotion);
			lyr_files = new Recycler(File);
			lyr_top = new FlxGroup;
			
			player = new Ship(40, FlxG.height / 2);
			
			//le hacemos crear 100 
			for(var i:int = 0; i<1; i++)
			{
				lyr_Pshots.add(new Shots());
			}
			
			for(var j:int = 0; j<1; j++)
			{
				lyr_enemy.add(new Enemy());
			}
			for(var k:int = 0; k<1; k++)
			{
				lyr_Eshots.add(new Shots());
			}
			
			var back:FlxBackdrop = new FlxBackdrop(ImgFondo1);
			back.velocity.x = -80;
			lyr_back.add(back);
			
			back = new FlxBackdrop(ImgFondo2);
			back.velocity.x = -110;
			lyr_back.add(back);
			
			levels = new Levels(FlxG.level);
			add(levels);
			
			score_txt = new FlxText(10, 10, 60, "0");
			score_txt.setFormat(null, 8);
			lyr_points.add(score_txt);
			
			//efecto de trail
			trail_draw = new TrailDrawer();
			
			this.add(lyr_back);
			this.add(trail_draw);
			this.add(lyr_Plazer);
			this.add(lyr_player);
			this.add(lyr_enemy);
			this.add(lyr_Pshots);
			this.add(lyr_Eshots);
			this.add(lyr_top);
			this.add(lyr_points);
			this.add(lyr_explotions);
			this.add(lyr_files);
			this.add(dm.getLayer());

			pLifeBar = new ProgressBar(this, 0, 0, 2, player.height, 0xff000000, 0xffffffff, 0xff106010,  2, true);
			pLifeBar.total_value = player.health; //mas facil asi
			pLifeBar.update_value(player.health);
			pLifeBar.set_visible(true);
			//mejor reiniciarla cada vez que se inicia el stage, eso hace mas facil las cosas
			//y nos aseguramos que al iniciar el stage se reinicie el valor
			data_timer = 0; //lo pongo en una variable que incrementa desde 0 porque asi funciona mejor la progressbar
			
			pTimeBar = new ProgressBar(this, 0, 0, player.width, 2, 0xff000000,  0xff000000, 0xff5050A0,  2, false);
			//inicializo la pbar en 1000 para permitir luego que los archivos puedan dar mas o menos puntaje
			pTimeBar.total_value = 1000;
			pTimeBar.update_value(0);
			pTimeBar.set_visible(true);
			
		}
		private function hitfile(f:File, nave:Ship):void {
			data_timer += 10;
			f.kill();
			pTimeBar.update_value(data_timer);
			
		}
		override public function update():void
		{			
			//colisiones
			FlxU.overlap(lyr_Pshots, lyr_enemy, shot_enemy);
			FlxU.overlap(lyr_enemy, player.collision_box, player.Hit);
			FlxU.overlap(lyr_Eshots, player.collision_box, player.Hit);
			FlxU.overlap(lyr_files, player, hitfile );
			
			super.update();
			
			if(player.dead)
			{
				Ship.main_theme.stop();
				var nuevo:MenuState = new MenuState;
				FlxG.state = nuevo;
				nuevo.SetFeats(player.features);
			}
			if (data_timer >= 1000) {
				Ship.main_theme.stop();
				
				var nuevo2:HistoryPart = new HistoryPart;
				FlxG.state = nuevo2;
				nuevo2.features = player.features;				
			}
			pLifeBar.move_bar(player.x - 3, player.y);
			pTimeBar.move_bar(player.x - 3, player.y + player.height+1 );
			score_txt.x = player.x;
			score_txt.y = player.y + player.height + 5;
			
			dm.update();
		}
		
		public static function give_points(P:Number = 0, X:Number = 0, Y:Number = 0):void {
			var point_text:Points = lyr_points.getInstance() as Points;
			point_text.reset(X, Y);
			point_text.set_value(P);
			score += P;
			score_txt.text = String(score);
		}
		public static function get_single_enemy():Enemy
		{
			return lyr_enemy.getInstance() as Enemy;
		}
		
		public function shot_enemy(shot:Shots, ene:Enemy):void
		{
			shot.kill();
			
			if(ene.health == 1)
			{
				give_points((ene.type+1)*10, ene.x, ene.y);
				ene.kill();
				//no se si convenga matar al enemigo en el playstate, por cuestion de orden, creo que el cada objeto deberia auto-matarse cuando corresponda
				(lyr_explotions.getInstance() as FlxSprite).reset(ene.x, ene.y);
			}
				
			ene.health -=1;
		}
		public function set_feats(feats:Array):void {
			for (var i:int = 0; i < feats.length; i++) {
				player.SetFeature(i, feats[i]);
			}
			dm.SetAlive(feats);
		}
	}
}