package com.game
{
	import org.flixel.FlxEmitter;
	import org.flixel.FlxG;
	import org.flixel.FlxGroup;
	import org.flixel.FlxPoint;
	import org.flixel.FlxSound;
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
		
		[Embed(source = "/data/Enemigos/Enemigo_nodisparador_mediano1.PNG")] private var ImgEne1:Class;
		[Embed(source = "/data/Enemigos/Enemigo_disparador-eslabon-4-.PNG")] private var ImgEne2:Class;
		[Embed(source = "/data/Enemigos/Enemigo_disparador-1-.png")] private var ImgEne3:Class;
		[Embed(source = "/data/Enemigos/Enemigo_disparador-5-.png")] private var ImgEne4:Class;
		[Embed(source = "/data/Enemigos/Enemigo_nodisparador_grande1.PNG")] private var ImgEne5:Class;
		
		[Embed(source = '/data/Musica/sound.swf', symbol = 'explosion_corta.mp3')] private var SndExplo:Class;

		private var sound_explo:FlxSound;
		
		//[Embed(source = "/data/part_explo.png")] private var ImgExplo:Class;

		//private var explo:FlxEmitter = new FlxEmitter;
		
		public var init_pos:FlxPoint = new FlxPoint();
		
		private var base_health:Number = 2//3;
		
		private var player:Ship;
		
		public function Enemy(X:Number=0, Y:Number=0, SimpleGraphic:Class=null)
		{
			super(X, Y);
			
			/*explo.createSprites(ImgExplo);
			explo.gravity = 0;
			explo.particleDrag.x = explo.particleDrag.y = 300;
			explo.minParticleSpeed.x = explo.minParticleSpeed.y = -200;
			explo.maxParticleSpeed.x = explo.maxParticleSpeed.y = 200;
			explo.setRotation(0, 0);
			PlayState.lyr_top.add(explo);*/
			
			exists = false;
			dead = true;
			
			player = PlayState.player;
			
			sound_explo = new FlxSound;
			sound_explo.loadEmbedded(SndExplo);		
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
				dead = true; //kill()?
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
		
		private function Shoot(Pos:FlxPoint, Angle:Number, Speed:Number=50):void
		{
			var shot:Shots = PlayState.lyr_Eshots.getInstance() as Shots;
			shot.friend = false;//no es muy util porque los shots del enemigo y del personaje estan en diferentes grupos
			shot.state = Shots.STATE_ENE;
			shot.reset(Pos.x, Pos.y);
			shot.speed = Speed;
			shot.my_angle = Angle;
		}
		
		override public function kill():void
		{
			/*explo.at(this);
			explo.start(true,0.5);*/
			PlayState.lyr_files.getInstance().reset(x, y);
			sound_explo.play();
			super.kill();
		}
		
		override public function reset(X:Number, Y:Number):void
		{
			health = base_health * (type +1);
			velocity.x = velocity.y = 0;
			acceleration.x = acceleration.y = 0;
				
			if(type == TYPE_UNO)
			{
				loadGraphic(ImgEne1,false,false,32,28);
				
				//bounding box tweaks
				width = 17;
				height = 12;
				offset.x = 7;
				offset.y = 9;
			}
			else if(type == TYPE_DOS)
			{
				loadGraphic(ImgEne2);
				//bounding box tweaks
				width = frameWidth;
				height = frameHeight;
				offset.x = 0;
				offset.y = 0;
			}
			else if(type == TYPE_TRES)
			{
				loadGraphic(ImgEne3,true);
				addAnimation("giro",[0,1,2,3],30);
				//bounding box tweaks
				width = 10;
				height = 14;
				offset.x = 6;
				offset.y = 4;
			}
			else if(type == TYPE_CUATRO)
			{
				loadGraphic(ImgEne4);
				//bounding box tweaks
				width = frameWidth;
				height = frameHeight;
				offset.x = 0;
				offset.y = 0;
			}
			else if(type == TYPE_CINCO)
			{
				loadGraphic(ImgEne5);
				//bounding box tweaks
				width = 27;
				height = 30;
				offset.x = 6;
				offset.y = 5;
			}
			
			super.reset(X,Y);
		}
	}
}