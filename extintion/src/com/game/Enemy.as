package com.game
{
	import org.flixel.FlxEmitter;
	import org.flixel.FlxG;
	import org.flixel.FlxGroup;
	import org.flixel.FlxPoint;
	import org.flixel.FlxSprite;
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
		
		[Embed(source = "/data/enemigos/enemigo_pequeno.png")] private var ImgEne:Class;
		[Embed(source = "/data/part_explo.png")] private var ImgExplo:Class;

		private var explo:FlxEmitter = new FlxEmitter;
		
		public var init_pos:FlxPoint = new FlxPoint();
		
		private var base_health:Number = 3;
		
		public function Enemy(X:Number=0, Y:Number=0, SimpleGraphic:Class=null)
		{
			super(X, Y, SimpleGraphic);
			
			loadGraphic(ImgEne);
			
			explo.createSprites(ImgExplo);
			PlayState.lyr_top.add(explo);
			
			exists = false;
			dead = true;
			
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
			x-= FlxG.elapsed * 100;
			
			move_angle += FlxG.elapsed*200 ;
			
			var radian:Number = Utils.deg_to_rad(move_angle);
			
			y = init_pos.y + (40* (Math.cos(radian)));
			
		}
		
		private var shot_angle:Number = 0;
		
		private var shot_timer:Number=0;
		private var shot_timer_max:Number= .1;
		
		public function update_dos():void
		{
			if(x>FlxG.width-150)
			{
				x-= FlxG.elapsed*150;
			}
			else
			{
				shot_timer+=FlxG.elapsed;
				if(shot_timer>shot_timer_max)
				{
					Shoot(new FlxPoint(x+width/2,y+height/2),shot_angle);
					shot_angle +=10;
					shot_timer = 0;
				}
			}
		}
		
		public function update_tres():void
		{
			
		}
		
		public function update_cuatro():void
		{
			
		}
		
		public function update_cinco():void
		{
			
		}
		
		private function Shoot(Pos:FlxPoint, Angle:Number):void
		{
			var shots:FlxGroup = PlayState.lyr_Eshots;
			var pos:FlxPoint = Pos;
			
			for (var i:uint = 0; i < shots.members.length; i++)
			{
				if (!shots.members[i].exists)
				{
					shots.members[i].reset(pos.x, pos.y);
					shots.members[i].state = 3;
					shots.members[i].friend = false;
					shots.members[i].angle = Angle;
					
					return;
				}
			}
			//sino se crea y se agrega al array (push) dentro de la capa de sprites
			var shot:Shots = new Shots(pos.x, pos.y);
			shot.reset(pos.x, pos.y);
			shot.state = 3;
			shot.friend = false;
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
			super.reset(X,Y);
		}
	}
}