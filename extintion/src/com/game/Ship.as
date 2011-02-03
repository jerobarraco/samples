package com.game
{
	import org.flixel.*;
	
	public class Ship extends FlxSprite
	{
		
		[Embed(source = "/data/nave/nave_base.png")] private var ImgPlayer:Class;
		
		[Embed(source = "/data/nave/nave_canones1.png")] private var ImgCanon1:Class;
		[Embed(source = "/data/nave/nave_canones2.png")] private var ImgCanon2:Class;
		[Embed(source = "/data/nave/nave_canones3.png")] private var ImgCanon3:Class;
		[Embed(source = "/data/nave/nave_escudo.png")] private var ImgEscudo:Class;
		[Embed(source = "/data/nave/nave_lanzabombas.png")] private var ImgBombas:Class;
		[Embed(source = "/data/nave/nave_propulsores1-2.png")] private var ImgPropulsores:Class;
		[Embed(source = "/data/nave/nave_propulsores1-4.png")] private var ImgPropulsores14:Class;
		[Embed(source = "/data/nave/nave_propulsores1-6.png")] private var ImgPropulsores16:Class;
		[Embed(source = "/data/nave/nave_fuego1.png")] private var ImgFuego:Class;
		[Embed(source = "/data/nave/nave_fuego1-4.png")] private var ImgFuego14:Class;
		[Embed(source = "/data/nave/nave_fuego1-6.png")] private var ImgFuego16:Class;
		
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme1.mp3')] private var MusMain1:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme2.mp3')] private var MusMain2:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme3.mp3')] private var MusMain3:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme4.mp3')] private var MusMain4:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme5.mp3')] private var MusMain5:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme6.mp3')] private var MusMain6:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme7.mp3')] private var MusMain7:Class;
		
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'disparo.mp3')] private var Snddisp:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'downgrade.mp3')] private var SndDown:Class;

		
		public static var main_theme:FlxSound = new FlxSound;
		
		public static var music_number:int = 0;
		
		private var sound_disp:FlxSound;
		private var sound_down:FlxSound;
		
		private var Sprites:Array;
		private var Images:Array;
		
		private var speed:Number = 300;
		private var base_speed:Number = 100;
		private var hitmultiplier:Number = 1;
		public var features:Array = [ true, true, true, true, false, true, true, true]; //array de bools para los features

		private var my_pos:FlxPoint = new FlxPoint;
		
		//Puntos de los disparos
		private var cannon_1:FlxPoint = new FlxPoint(23, 6);
		private var cannon_2:FlxPoint = new FlxPoint(35, 11);
		private var cannon_3:FlxPoint = new FlxPoint(47, 19);
		private var cannon_4:FlxPoint = new FlxPoint(35, 27);
		private var cannon_5:FlxPoint = new FlxPoint(23, 32);
		private var cheats:Boolean = false;
		//todo: timer para ver datos;
		public function Ship(X:Number=0, Y:Number=0):void
		{
			super(X,Y);
			health = 10;
			//array con la lista de features activos
			//features = new Array;
			/*
			* 0= cannon1
			* 1= cannon2
			* 2= cannon3
			* 3= escudo
			* 4= bombas
			* 5= speed1
			* 6= speed2
			* 7= speed3
			* 
			*/
			for (var j:int = 0; j < 8; j++) {
				features[j] = (j!=3) ;
			}
			
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
			
			music_number +=1;
			
			sound_disp = new FlxSound();
			sound_disp.loadEmbedded(Snddisp);
			
			sound_down = new FlxSound();
			sound_down.loadEmbedded(SndDown);
		}
		override public function update():void
		{
			if(FlxG.keys.UP)
			{
				y -= FlxG.elapsed * speed;
				if (y < 0) y  = 0;
			}
			if(FlxG.keys.DOWN)
			{
				y += FlxG.elapsed * speed;
				if (y > FlxG.height - height) y = FlxG.height - height;
			}
			if(FlxG.keys.LEFT)
			{
				x -= FlxG.elapsed * speed;
				if (x < 0) x = 0;
			}
			if(FlxG.keys.RIGHT)
			{
				x += FlxG.elapsed * speed;
				if (x > FlxG.width - width) x = FlxG.width - width;
			}
			if (cheats){
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
			if(FlxG.keys.justPressed("Z") || FlxG.keys.justPressed("SPACE"))
			{
				my_pos.set(x, y);
				
				if (features[0]) { //main
					Shoot(FlxPoint.add(my_pos, cannon_3), Shots.STATE_NORMAL);
				}
				if (features[1]) { //soporte
					Shoot(FlxPoint.add(my_pos, cannon_2), Shots.STATE_NORMAL);
					Shoot(FlxPoint.add(my_pos, cannon_4), Shots.STATE_NORMAL);
				}
				if (features[2]) { //homming
					 Shoot(FlxPoint.add(my_pos, cannon_1), Shots.STATE_HOMMING);
					 Shoot(FlxPoint.add(my_pos, cannon_5), Shots.STATE_HOMMING);
				}
				
				sound_disp.play();
			}
			for (var i:int = 0; i < Sprites.length; i++ ) {
				Sprites[i].x = x;
				Sprites[i].y = y;
			}
			super.update();
		}
		
		private function Shoot(Pos:FlxPoint, Type:int):void
		{
			var shots:FlxGroup = PlayState.lyr_Pshots;
			var pos:FlxPoint = Pos;
			
			for (var i:uint = 0; i < shots.members.length; i++)
			{
				if (!shots.members[i].exists)
				{
					shots.members[i].reset(pos.x, pos.y);
					shots.members[i].state = Type;
					shots.members[i].friend = true;
					return;
				}
			}
			//sino se crea y se agrega al array (push) dentro de la capa de sprites
			var shot:Shots = new Shots(pos.x, pos.y);
			shot.reset(pos.x, pos.y);
			shot.state = Type;
			shot.friend = true;
			shots.members.push(PlayState.lyr_Pshots.add(shot));
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
		
		public function Hit2(shot:Shots, me:FlxSprite):void {
			shot.kill();
			health -= 1 * hitmultiplier;
			if (health < 0) {
				kill();
			}
			sound_down.play();
		}
		
		public function Hit(shot:Enemy, me:FlxSprite):void 
		{
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