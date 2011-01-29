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
		
		private var speed:Number = 300;
		private var features:Array;
		/*
		 * 0= cannon1
		 * 1= cannon2
		 * 2= cannon3
		 * 3= escudo
		 * 4= bombas
		 * 5= propulsores
		 * 
		 * 
		 * 
		 */
		
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
			for (var i:int = 0; i < 6; i++) {
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
		
		public function Hit(shot:Enemy, me:FlxSprite):void 
		{
			shot.kill();
			health -= 1;
			 switch(health) {
				case 9: 
					cannon1.kill(); 
					features[0] = false;
					break;
				case 8: 
					cannon2.kill(); 
					features[1] = false;
					break;
				case 7: 
					cannon3.kill(); 
					features[2] = false;
					break;
				case 6:
					bombas.kill(); 
					features[3] = false;
					break;
				case 5: 
					escudo.kill();
					features[4] = false;
					break;
				case 4: 
					propulsores.kill(); 
					features[5] = false;
					break;
			}
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