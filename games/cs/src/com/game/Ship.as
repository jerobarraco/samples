package com.game
{
	import com.game.ProgressBar;
	
	import flash.geom.Point;
	
	import org.flixel.*;
	
	import utils.Utils;
	
	public class Ship extends FlxSprite
	{
		
		[Embed(source = "/data/Nave/Nave.png")] private var ImgPlayer:Class;
		
		[Embed(source = "/data/Nave/Nave_canones1.png")] private var ImgCanon1:Class;
		[Embed(source = "/data/Nave/Nave_canones2.png")] private var ImgCanon2:Class;
		[Embed(source = "/data/Nave/Nave_canones3.png")] private var ImgCanon3:Class;
		[Embed(source = "/data/Nave/Nave_escudo.png")] private var ImgEscudo:Class;
		[Embed(source = "/data/Nave/Nave_lanzabombas.png")] private var ImgBombas:Class;
		[Embed(source = "/data/Nave/Nave_propulsores1-2.png")] private var ImgPropulsores:Class;
		[Embed(source = "/data/Nave/Nave_propulsores1-4.png")] private var ImgPropulsores14:Class;
		[Embed(source = "/data/Nave/Nave_propulsores1-6.png")] private var ImgPropulsores16:Class;
		[Embed(source = "/data/Nave/Nave_fuego1.png")] private var ImgFuego:Class;
		[Embed(source = "/data/Nave/Nave_fuego1-4.png")] private var ImgFuego14:Class;
		[Embed(source = "/data/Nave/Nave_fuego1-6.png")] private var ImgFuego16:Class;
		
		[Embed(source = '/data/Musica/sound.swf', symbol = 'Maintheme1.mp3')] private var MusMain1:Class;
		[Embed(source = '/data/Musica/sound.swf', symbol = 'Maintheme2.mp3')] private var MusMain2:Class;
		[Embed(source = '/data/Musica/sound.swf', symbol = 'Maintheme3.mp3')] private var MusMain3:Class;
		[Embed(source = '/data/Musica/sound.swf', symbol = 'Maintheme4.mp3')] private var MusMain4:Class;
		[Embed(source = '/data/Musica/sound.swf', symbol = 'Maintheme5.mp3')] private var MusMain5:Class;
		[Embed(source = '/data/Musica/sound.swf', symbol = 'Maintheme6.mp3')] private var MusMain6:Class;
		[Embed(source = '/data/Musica/sound.swf', symbol = 'Maintheme7.mp3')] private var MusMain7:Class;
		
		[Embed(source = '/data/Musica/sound.swf', symbol = 'disparo.mp3')] private var Snddisp:Class;
		[Embed(source = '/data/Musica/sound.swf', symbol = 'downgrade.mp3')] private var SndDown:Class;

		
		public static var main_theme:FlxSound = new FlxSound;
		
		public static var music_number:int = 0;
		
		private var sound_disp:FlxSound;
		private var sound_down:FlxSound;
		
		private var Sprites:Array;
		private var Images:Array;
		
		public var collision_box:FlxSprite;
		
		private var cheats:Boolean = true;
		private var speed:Number = 180;
		private var base_speed:Number = 60;
		private var old_speed:Number = 0;
		private var hitmultiplier:Number = 1;
		public var features:Array = [ true, true, true, true, false, true, true, true, true]; //array de bools para los features
		//array con la lista de features activos
			/*
			* 0= cannon1
			* 1= cannon2
			* 2= cannon3 (homming)
			* 3= escudo
			* 4= bombas
			* 5= speed1
			* 6= speed2
			* 7= speed3
			* 8= magnet100%
			* 9= magnet50%
			* 
			*/
			
		private var my_pos:FlxPoint = new FlxPoint;
		//Puntos de los disparos
		private var cannon_1:FlxPoint = new FlxPoint(18, 5);
		private var cannon_2:FlxPoint = new FlxPoint(30, 6);
		private var cannon_3:FlxPoint = new FlxPoint(42, 14);
		private var cannon_4:FlxPoint = new FlxPoint(30, 23);
		private var cannon_5:FlxPoint = new FlxPoint(18, 28);
		
		
		//autofire timer
		private var shot_timer:Number = 0;
		private var shot_timer_max:Number = 0.15//60/240;
		private var lazer:Lazer;
		private var lazer_active:Boolean;
			
		//magnet timer
		private var magnet_timer:Number =0;
		private var magnet_timer_max:Number = 0.1;
		private var magnet_activated:Boolean = false;
		
		//todo: timer para ver datos;
		public function Ship(X:Number=0, Y:Number=0):void
		{
			super(X,Y);
			health = 10;
			
			loadGraphic(ImgPlayer);
			
			PlayState.lyr_player.add(this);
			
			//Creamos las partes
			Images = [
				ImgCanon1, ImgCanon2, ImgCanon3, ImgBombas,
				ImgPropulsores, ImgPropulsores14, ImgPropulsores16,
				ImgFuego, ImgFuego14, ImgFuego16, ImgEscudo
			];
			
			Sprites = new Array;
			for (var i:int = 0; i < Images.length; i++) {
				if ((i < 7) || (i>9)) {
					Sprites[i] = new FlxSprite(x, y, Images[i]);
				}else
				{
					Sprites[i] = new FlxSprite(x, y);
					Sprites[i].loadGraphic(Images[i], true, false, 50, 40);
					
					Sprites[i].addAnimation("fuego", [0, 1, 2], 5, true);	
					Sprites[i].play("fuego");
				}
				
				PlayState.lyr_player.add(Sprites[i]);
			}
			
			lazer_active = false;
			
			//sprite de collision
			collision_box = new FlxSprite(x+22,y+15);
			collision_box.createGraphic(9,9);
			collision_box.visible = false;
			PlayState.lyr_player.add(collision_box);
			
			music_number +=1;
			
			sound_disp = new FlxSound();
			sound_disp.loadEmbedded(Snddisp);
			
			sound_down = new FlxSound();
			sound_down.loadEmbedded(SndDown);
		}
		
		override public function update():void
		{
			if(FlxG.keys.UP || FlxG.keys.W)
			{
				y -= FlxG.elapsed * speed;
				if (y < 0) y  = 0;
			}
			if(FlxG.keys.DOWN || FlxG.keys.S)
			{
				y += FlxG.elapsed * speed;
				if (y > FlxG.height - height) y = FlxG.height - height;
			}
			if(FlxG.keys.LEFT || FlxG.keys.A)
			{
				x -= FlxG.elapsed * speed;
				if (x < 0) x = 0;
			}
			if(FlxG.keys.RIGHT || FlxG.keys.D)
			{
				x += FlxG.elapsed * speed;
				if (x > FlxG.width - width) x = FlxG.width - width;
			}
			if (cheats){ this.Cheat(); }
			
			//para la posición relativa de los cañones
			my_pos.set(x, y);
			
			//normal fire
			if(FlxG.keys.justPressed("Z") || FlxG.keys.justPressed("SPACE"))
			{
				if (features[0]) { //main
					Shoot(FlxPoint.add(my_pos, cannon_3), Shots.STATE_NORMAL);
				}
				if (features[1]) { //soporte
					Shoot(FlxPoint.add(my_pos, cannon_2), Shots.STATE_NORMAL, 170);
					Shoot(FlxPoint.add(my_pos, cannon_4), Shots.STATE_NORMAL, 190);
				}
				if (features[2]) { //homming
					Shoot(FlxPoint.add(my_pos, cannon_1), Shots.STATE_HOMMING);
					Shoot(FlxPoint.add(my_pos, cannon_5), Shots.STATE_HOMMING);
				}	
				
				old_speed = speed;
			}
			
			var laser_point:FlxPoint = FlxPoint.add(my_pos, cannon_3)
			//laser y magnet
			if(FlxG.keys.pressed("Z") || FlxG.keys.pressed("SPACE"))
			{
				shot_timer+=FlxG.elapsed;
				//laser
				if(shot_timer>shot_timer_max)
				{
					if(!lazer_active)
					{
						var laser_width:int = 0;
						if (features[0]) { //main
							laser_width +=15;
						}
						if (features[1]) { //soporte
							laser_width +=15;
						}
						if (laser_width>0)
						{
							//lazer
							lazer = shoot_lazer(laser_point,laser_width);
						}
						lazer_active = true;
					}
					
					if (features[2]) { //homming
						Shoot(FlxPoint.add(my_pos, cannon_1), Shots.STATE_HOMMING);
						Shoot(FlxPoint.add(my_pos, cannon_5), Shots.STATE_HOMMING);
					}
					
					shot_timer = 0;
				}
				if(lazer_active)
				{
					lazer.LaserOrigin.x = laser_point.x;
					lazer.LaserOrigin.y = laser_point.y;

					sound_disp.play();
				}
				//magnet					
				if(!magnet_activated)
				{
					magnet_timer+=FlxG.elapsed;				
					if(magnet_timer>magnet_timer_max)
					{
						if (features[8]) { //magnet 100%
							magnet(FlxG.width);
							}
						if (features[9]) { //magnet 50%
							magnet(FlxG.width/2);
							}
							magnet_activated = true;
							
							//focus
							old_speed = speed;
							speed = speed/2;
					}
				}
					
			}
			else if(FlxG.keys.justReleased(("Z")) || FlxG.keys.justReleased(("SPACE")) )
			{
				magnet_activated = false;
				magnet_timer = 0;
				shot_timer = 0;
				speed = old_speed;
				lazer_active = false;
				if(PlayState.lyr_Plazer.members.length > 0)
				{
					lazer.kill();
				}
			}

			for (var i:int = 0; i < Sprites.length; i++ ) {
				Sprites[i].x = x;
				Sprites[i].y = y;
			}
			//sprite de colision
			collision_box.x = x + 22;
			collision_box.y = y + 15;
			
			super.update();
		}
		private function Cheat():void {
			if (FlxG.keys.justPressed("ONE")) {
					SetFeature(0, !features[0]);
				}
				if (FlxG.keys.justPressed("TWO")) {
					SetFeature(1, !features[1]);
				}
				if (FlxG.keys.justPressed("THREE")) {
					SetFeature(2, !features[2]);
				}
				if (FlxG.keys.justPressed("FOUR")) {
					SetFeature(3, !features[3]);
				}
				if (FlxG.keys.justPressed("FIVE")) {
					SetFeature(4, !features[4]);
				}
				if (FlxG.keys.justPressed("SIX")) {
					SetFeature(5, !features[5]);
				}
				if (FlxG.keys.justPressed("SEVEN")) {
					SetFeature(6, !features[6]);
				}
				if (FlxG.keys.justPressed("EIGHT")) {
					SetFeature(7, !features[7]);
				}
				if (FlxG.keys.justPressed("NINE")) {
					SetFeature(8, !features[8]);
				}
				if (FlxG.keys.justPressed("Q")) {
					var state:HistoryPart = new HistoryPart;
					FlxG.state = state;
					state.features = features;
				}
				if (FlxG.keys.justPressed("W")) {
					kill();
				}
		}
		private function Shoot(Pos:FlxPoint, Type:int, sangle:Number = 180):void
		{
			var shot:Shots = PlayState.lyr_Pshots.getInstance() as Shots;
			shot.state = Type;
			shot.friend = true;
			shot.my_angle = sangle;
			shot.reset(Pos.x, Pos.y);
		}
		
		private function shoot_lazer(Pos:FlxPoint, LWidth:int):Lazer
		{
			var lazer:Lazer = PlayState.lyr_Plazer.getInstance() as Lazer;
			lazer.widthMax = LWidth;
			lazer.reset(Pos.x,Pos.y);
			return lazer;
		}
		
		private function magnet(dist:Number):void
		{
			var atracted_files:Array = PlayState.lyr_files.getLiving();
			if(atracted_files.length>0)
			{
				var o:File;
				var j:uint
				var ml:uint = atracted_files.length;
				while(j<ml)
				{
					o = atracted_files[j] as File;
					if ( Utils.point_distance( new Point(x,y),new Point(o.x,o.y))<dist)
					{
					o.magnetized = true;
					}
					
					j++;
				}
			}
		}
		
		public function SetFeature(feat:int, val:Boolean):void {
			//Prender o apagar una feat
			features[feat] = val;
			if (val) {
				//Sprites[feat].reset(x, y);
				switch(feat) {
					case 0: Sprites[0].reset(x, y); break;//main
					case 1: Sprites[1].reset(x, y); break;//secundario
					case 2: Sprites[2].reset(x, y); break;//homming
					//shield
					case 3: 
						Sprites[10].reset(x, y); 
						hitmultiplier = 1;
						break; 
					//Bomb
					case 4: Sprites[3].reset(x, y); break;
					//Propeller1
					case 5: 
						Sprites[4].reset(x, y); 
						Sprites[7].reset(x, y); 
						speed += base_speed; break;
					//Propeller2
					case 6: 
						Sprites[5].reset(x, y); 
						Sprites[8].reset(x, y); 
						speed += base_speed; break;
					case 7:
						Sprites[6].reset(x, y); 
						Sprites[9].reset(x, y);
						speed += base_speed; break;
				}
			}else {
				switch(feat) {
					case 0: Sprites[0].kill(); break;//main
					case 1: Sprites[1].kill(); break;//secundario
					case 2: Sprites[2].kill(); break;//homming
					//shield
					case 3: 
						Sprites[10].kill(); 
						hitmultiplier = 2;
						break; 
					//Bomb
					case 4: Sprites[3].kill(); break;
					//Propeller1
					case 5: 
						Sprites[4].kill(); 
						Sprites[7].kill(); 
						speed -= base_speed; break;
					//Propeller2
					case 6: 
						Sprites[5].kill(); 
						Sprites[8].kill(); 
						speed -= base_speed; break;
					//Propeller3
					case 7:
						Sprites[6].kill(); 
						Sprites[9].kill();
						speed -= base_speed; break;
				}
			}
			
			if(music_number==1)
			{
				main_theme.loadEmbedded(MusMain7,true);
			}
			else if(music_number==2)
			{
				main_theme.loadEmbedded(MusMain6,true);
			}
			else if(music_number==3)
			{
				main_theme.loadEmbedded(MusMain5,true);
			}
			else if(music_number==4)
			{
				main_theme.loadEmbedded(MusMain4,true);
			}
			else if(music_number==5)
			{
				main_theme.loadEmbedded(MusMain3,true);
			}
			else if(music_number==6)
			{
				main_theme.loadEmbedded(MusMain2,true);
			}
			else if(music_number>=7)
			{
				main_theme.loadEmbedded(MusMain1,true);
			}
			
			main_theme.play();
		}
		public function Hit(enemy:FlxSprite, me:FlxSprite):void {
			enemy.kill();
			health -= 1*hitmultiplier;
			if (health < 0) {
				kill();
			}
			//acceder objetos ajenos, que cochino :P
			PlayState.pLifeBar.animate_bar(health);
			sound_down.play();
		}	
		public function Hit2(shot:Shots, me:FlxSprite):void {
			//este hit se llama cuando a la nave la golpea un enemigo.
			//por ahora ambas funciones son iguales asi que esta la borro.
			//deprecated
			
			shot.kill();
			health -= 1 * hitmultiplier;
			if (health < 0) {
				kill();
			}
			sound_down.play();
		}
		
		public function Hit3(shot:Enemy, me:FlxSprite):void 
		{
			//Este hit se llama cuando a la nave la golpea ... un tiro
			shot.kill();
			health -= 1*hitmultiplier;
			if (health < 0) {
				kill();
			}
			sound_down.play();
		}
		override public function kill():void
		{
			super.kill();
			for (var i:int = 0; i < Sprites.length; i++) {
				Sprites[i].kill();
			}
		}
		override public function reset(X:Number, Y:Number):void {
			super.reset(X, Y);		
			for (var i:int = 0; i < Sprites.length; i++) {
				Sprites[i].reset(X, Y);
			}
			health = 10;
		}
	}
}