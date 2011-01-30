package com.game
{
	import org.flixel.FlxEmitter;
	import org.flixel.FlxG;
	import org.flixel.FlxGroup;
	import org.flixel.FlxPoint;
	import org.flixel.FlxSprite;
	import org.flixel.FlxU;
	import org.osmf.layout.AbsoluteLayoutFacet;
	
	import utils.Utils;
	
	public class Enemy extends FlxSprite
	{
		public static var TYPE_UNO:int = 0;
		public static var TYPE_DOS:int = 1;
		public static var TYPE_TRES:int = 2;
		public static var TYPE_CUATRO:int = 3;
		public static var TYPE_CINCO:int = 4;
		
		public var type:int = 0;
		
		[Embed(source = "/data/enemigos/Enemigo_nodisparador_mediano1.PNG")] private var ImgEne1:Class;
		[Embed(source = "/data/enemigos/Enemigo_disparador-eslabon-4-.PNG")] private var ImgEne2:Class;
		[Embed(source = "/data/enemigos/Enemigo_disparador-1-.PNG")] private var ImgEne3:Class;
		[Embed(source = "/data/enemigos/Enemigo_disparador-5-.PNG")] private var ImgEne4:Class;
		[Embed(source = "/data/enemigos/Enemigo_nodisparador_grande1.PNG")] private var ImgEne5:Class;
		
		[Embed(source = "/data/part_explo.png")] private var ImgExplo:Class;

		private var explo:FlxEmitter = new FlxEmitter;
		
		public var init_pos:FlxPoint = new FlxPoint();
		
		private var base_health:Number = 3;
		
		private var player:Ship;
		
		public function Enemy(X:Number=0, Y:Number=0, SimpleGraphic:Class=null)
		{
			super(X, Y, SimpleGraphic);
			
			//loadGraphic(ImgEne5);
			
			explo.createSprites(ImgExplo);
			PlayState.lyr_top.add(explo);
			
			exists = false;
			dead = true;
			
			player = PlayState.player;
			
		}
		
		override public function update():void
		{
			if(type == TYPE_UNO)
			{
				update_uno();
			}
			else if(type == TYPE_DOS)
			{
				update_dos();
			}
			else if(type == TYPE_TRES)
			{
				update_tres();
			}
			else if(type == TYPE_CUATRO)
			{
				update_cuatro();
			}
			else if(type == TYPE_CINCO)
			{
				update_cinco();
			}
			
			if(x<-width)
			{
				exists=false;
				dead = true
			}
			
			super.update();
		}
		
		public var move_angle:Number = 0;
		
		public function update_uno():void
		{
			x-= FlxG.elapsed * 60;
		}
		
		public function update_dos():void
		{
			x-= FlxG.elapsed * 100;
			
			move_angle += FlxG.elapsed*200 ;
			
			var radian:Number = Utils.deg_to_rad(move_angle);
			
			y = init_pos.y + (40* (Math.cos(radian)));
		}
		
		private var shot_angle:Number = 0;
		
		private var shot_timer:Number=0;
		private var shot_timer_max:Number= .1;
		
		public function update_tres():void
		{
			if(x>FlxG.width-150)
			{
				x-= FlxG.elapsed*150;
				shot_angle = 0;
			}
			else
			{
				if(shot_angle<1280)
				{
					play("giro");

					shot_timer+=FlxG.elapsed;
					if(shot_timer>shot_timer_max)
					{
						Shoot(new FlxPoint(x+width/2,y+height/2),shot_angle, 300);
						shot_angle += 25;
						shot_timer = 0;
					}
				}
				else
				{
					x-= FlxG.elapsed*150;
				}
			}
		}
		
		public function update_cuatro():void
		{
			shot_timer_max = 2;
			x-= FlxG.elapsed * 60;
			
			shot_timer+=FlxG.elapsed;
			if(shot_timer>shot_timer_max)
			{
				shot_angle = FlxU.getAngle(x- player.x, y-player.y);
				Shoot(new FlxPoint(x+width/2,y+height/2),shot_angle);
				shot_timer = 0;
			}
			
		}
		
		public var life_timer:Number = 0;
		public var life_timer_max:Number = 5;
		
		public function update_cinco():void
		{
			if(life_timer<life_timer_max)
			{
				maxThrust = 200;
				thrust= 70;
			
				angle = FlxU.getAngle(x- player.x, y-player.y);
			}
			else
			{
				life_timer = 0;
				 kill();
			}
		}
		
		private function Shoot(Pos:FlxPoint, Angle:Number, speed:Number=50):void
		{
			var shots:FlxGroup = PlayState.lyr_Eshots;
			var pos:FlxPoint = Pos;
			var shot_speed:Number = speed;
			
			for (var i:uint = 0; i < shots.members.length; i++)
			{
				if (!shots.members[i].exists)
				{
					shots.members[i].state = Shots.STATE_ENE;
					shots.members[i].friend = false;
					shots.members[i].reset(pos.x, pos.y);
					shots.members[i].speed = shot_speed;
					shots.members[i].angle = Angle;
					
					return;
				}
			}
			//sino se crea y se agrega al array (push) dentro de la capa de sprites
			var shot:Shots = new Shots(pos.x, pos.y);
			shot.state = Shots.STATE_ENE;
			shot.friend = false;
			shot.reset(pos.x, pos.y);
			shot.speed = shot_speed;
			shot.angle = Angle;
			shots.members.push(PlayState.lyr_Eshots.add(shot));
		}
		
		override public function kill():void
		{
			explo.at(this);
			explo.start();
			
			super.kill();
		}
		
		override public function reset(X:Number, Y:Number):void
		{
			health = base_health * (type +1);
			velocity.x = velocity.y = 0;
			acceleration.x = acceleration.y = 0;
			
			if(type == TYPE_UNO)
			{
				loadGraphic(ImgEne1);
			}
			else if(type == TYPE_DOS)
			{
				loadGraphic(ImgEne2);
			}
			else if(type == TYPE_TRES)
			{
				loadGraphic(ImgEne3,true);
				addAnimation("giro",[0,1,2,3],30);
			}
			else if(type == TYPE_CUATRO)
			{
				loadGraphic(ImgEne4);
			}
			else if(type == TYPE_CINCO)
			{
				loadGraphic(ImgEne5);
			}
			
			super.reset(X,Y);
		}
	}
}