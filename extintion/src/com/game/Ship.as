package com.game
{
	import org.flashdevelop.utils.FlashConnect;
	import org.flixel.*;
	
	public class Ship extends FlxSprite
	{
		
		[Embed(source = "/data/nave/nave_base.png")] private var ImgPlayer:Class;
		
		[Embed(source = "/data/nave/nave_canones1.png")] private var ImgCanon1:Class;
		[Embed(source = "/data/nave/nave_canones2.png")] private var ImgCanon2:Class;
		[Embed(source = "/data/nave/nave_canones3.png")] private var ImgCanon3:Class;
		[Embed(source = "/data/nave/nave_escudo.png")] private var ImgEscudo:Class;
		[Embed(source = "/data/nave/nave_lanzabombas.png")] private var ImgBombas:Class;
		[Embed(source = "/data/nave/nave_propulsores.png")] private var ImgPropulsores:Class;
		//TODO: poner health, bounding box
		//Sprites para las partes
		private var cannon1:FlxSprite;
		private var cannon2:FlxSprite;
		private var cannon3:FlxSprite;
		private var escudo:FlxSprite;		
		private var bombas:FlxSprite;
		private var propulsores:FlxSprite;
		
		private var speed:Number = 400;
		private var base_speed:Number = 100;
		private var features:Array; //array de bools para los features
		
		private var my_pos:FlxPoint = new FlxPoint;
		
		//Puntos de los disparos
		private var cannon_1:FlxPoint = new FlxPoint(17, 7);
		private var cannon_2:FlxPoint = new FlxPoint(30, 12);
		private var cannon_3:FlxPoint = new FlxPoint(40, 19);
		private var cannon_4:FlxPoint = new FlxPoint(29, 27);
		private var cannon_5:FlxPoint = new FlxPoint(18, 37);
		
		
		public function Ship(X:Number=0, Y:Number=0):void
		{
			super(X,Y);
			health = 10;
			//array con la lista de features activos
			features = new Array;
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
			for (var i:int = 0; i < 9; i++) {
				features[i] = true;
			}
			
			loadGraphic(ImgPlayer);
			PlayState.lyr_player.add(this);
			//Creamos las partes
			
			cannon1 = new FlxSprite(x, y, ImgCanon1);
			PlayState.lyr_player.add(cannon1);
			
			cannon2 = new FlxSprite(x, y , ImgCanon2);
			PlayState.lyr_player.add(cannon2);
			
			cannon3 = new FlxSprite(x, y , ImgCanon3);
			PlayState.lyr_player.add(cannon3);
			
			escudo = new FlxSprite(x, y, ImgEscudo);
			PlayState.lyr_player.add(escudo);
			
			bombas = new FlxSprite(x, y, ImgBombas);
			PlayState.lyr_player.add(bombas);
			
			propulsores = new FlxSprite(x, y, ImgPropulsores);
			PlayState.lyr_player.add(propulsores);
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
			if(FlxG.keys.justPressed("Z") || FlxG.keys.justPressed("SPACE"))
			{
				my_pos.set(x, y);
				
				switch(true) {
					case features[0]: Shoot(FlxPoint.add(my_pos, cannon_1), Shots.STATE_HOMMING);
					case features[1]: Shoot(FlxPoint.add(my_pos, cannon_5), Shots.STATE_HOMMING);
					case features[2]: Shoot(FlxPoint.add(my_pos, cannon_2), Shots.STATE_NORMAL);
					case features[3]: Shoot(FlxPoint.add(my_pos, cannon_3), Shots.STATE_NORMAL);
					case features[4]: Shoot(FlxPoint.add(my_pos, cannon_4), Shots.STATE_NORMAL);
				}
			}
			
			cannon1.x = x;
			cannon1.y = y;
			
			cannon2.x = x;
			cannon2.y = y;
			
			cannon3.x = x;
			cannon3.y = y;
			
			escudo.x = x;
			escudo.y = y;
			
			bombas.x = x;
			bombas.y = y;
			
			propulsores.x = x;
			propulsores.y = y;
			
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
					return;
				}
			}
			//sino se crea y se agrega al array (push) dentro de la capa de sprites
			var shot:Shots = new Shots(pos.x, pos.y);
			shot.reset(pos.x, pos.y);
			shot.state = Type;
			shots.members.push(PlayState.lyr_Pshots.add(shot));
		}
		public function SetFeature(feat:int, val:Boolean):void {
			//Prender o apagar una feat
			features[feat] = val;
			if (val) {
				switch(feat) {
					case 0: cannon1.reset(x, y); break;
					case 1: cannon2.reset(x, y); break;
					case 2: cannon3.reset(x, y); break;
					case 3: escudo.reset(x, y); break;
					case 4: bombas.reset(x, y); break;
					case 5: propulsores.reset(x, y); break;
					case 6:  speed += base_speed; break;
					case 7:  speed += base_speed; break ;
					case 8:  speed += base_speed; break;
				}
			}else{
				switch(feat) {
					case 0: cannon1.kill(); break;
					case 1: cannon2.kill(); break;
					case 2: cannon3.kill(); break;
					case 3: escudo.kill(); break;
					case 4: bombas.kill(); break;
					case 5: propulsores.kill(); break;
					case 6: speed -= base_speed; break;
					case 7: speed -= base_speed; break;
					case 8: speed -= base_speed; break;
				}
			}
		}
		public function Hit(shot:Enemy, me:FlxSprite):void 
		{
			shot.kill();
			health -= 1;
			
			if (health < 0) {
				kill();
			}
		}
		override public function kill():void
		{
			super.kill();
			cannon1.kill();
			cannon2.kill();
			cannon3.kill();
			bombas.kill();
			escudo.kill();
			propulsores.kill();
		}
		override public function reset(X:Number, Y:Number):void {
			super.reset(X, Y);		
			cannon1.reset(X, Y);
			cannon2.reset(X, Y);
			cannon3.reset(X, Y);
			bombas.reset(X, Y);
			escudo.reset(X, Y);
			propulsores.reset(X, Y);
			health = 10;
		}
	}
}