package com.game
{
	import flash.display.BitmapData;
	import flash.display.Sprite;
	import flash.geom.Point;
	
	import org.flixel.*;
	
	import utils.Colors;
	import utils.Utils;
	
	public class Shots extends FlxSprite
	{
		public static var STATE_NORMAL:int = 0;
		public static var STATE_HOMMING:int =1;
		public static var STATE_ENE:int =2;
		
		public var state:int = STATE_NORMAL;
		
		[Embed(source = "/data/Nave/homming.png")] private var ImgHomming:Class;
		[Embed(source = "/data/Nave/shot.png")] private var ImgShot:Class;
		
		public var speed:Number = 0;
		private var max_speed:Number = 200;
		
		public var target:Enemy;
		public var up:Boolean=false;
		
		public var my_angle:Number = 0;
		
		public var friend:Boolean = true;
		
		private var previous_pos:FlxPoint = new FlxPoint;
		
		private var trailSprite:Sprite;
		private var canvas:BitmapData;
		
		public function Shots(X:Number=0, Y:Number=0):void 
		{
			super(X, Y);
			
			previous_pos.set(X,Y);
			trailSprite = new Sprite();
			
			exists = false;
		}
		
		override public function update():void
		{
			if(state==STATE_NORMAL)
			{
				update_normal();
			}
			else if(state==STATE_HOMMING)
			{
				update_homming();
			}
			else if(state==STATE_ENE)
			{
				
			}
			
			var move:FlxPoint = FlxU.rotatePoint(1,0,0,0,my_angle);
			velocity.x = -move.x*speed;
			velocity.y = -move.y*speed;
			
			if(!onScreen())
				this.kill()
			
			super.update();
		}
		
		public function update_normal():void
		{
		}
		
		public function update_homming():void
		{
			if(speed<max_speed)
			{
				speed+= FlxG.elapsed * 250//250;
				if(up)
				{
					my_angle-= FlxG.elapsed * 500//500;
				}
				else
				{
					my_angle+= FlxG.elapsed * 500//500;
				}
			}
			else
			{
				if(target==null || target.dead)
				{
					target = find_target();
					return;
				}
				//Aiming
				var dx:Number = x - target.x;
				var dy:Number = y - target.y;
				var da:Number = FlxU.getAngle(dx,dy);
				
				angle = my_angle = da;
			
				//estela
				trailSprite.graphics.clear();
				trailSprite.graphics.lineStyle(height/4, TrailDrawer.COLOR_TRAIL);
				trailSprite.graphics.moveTo(x + frameWidth/2, y + frameHeight/2);
				trailSprite.graphics.lineTo(previous_pos.x, previous_pos.y);
				
				previous_pos.set(x+width/2,y+height/2);
			}
		}
		
		override public function reset(X:Number, Y:Number):void
		{
			previous_pos.set(X,Y);
			trailSprite.graphics.clear();
			trailSprite.graphics.moveTo(X + frameWidth/2, Y + frameHeight/2);
			trailSprite.graphics.lineTo(X + frameWidth/2, X + frameWidth/2);
			
			if(state==STATE_HOMMING)
			{
				loadGraphic(ImgHomming,true,false,10,7);
				addAnimation("homming",[0,1],20);
				play("homming");
				color = 0xffffffff;
				speed = 0;
				
				up = Y - height < PlayState.player.y;
				
				angle =180;
				my_angle = 180;
				
//				target = find_target();
			}
			else if(state==STATE_NORMAL)
			{
				loadRotatedGraphic(ImgShot);
				speed = max_speed;
				
				if(friend)
				{
					color = 0xffff0000;
				}
				else
				{
					color = 0xff0000ff;
				}
			}
			else if(state==STATE_ENE)
			{
				loadRotatedGraphic(ImgShot);
				
				if(friend)
				{
					color = 0xffff0000;
				}
				else
				{
					color = 0xff0000ff;
				}
				angle =180;
				my_angle = 180;
			}
			
			super.reset(X,Y);
		}
		
		override public function render():void
		{
			canvas = PlayState.trail_draw.trailsBmp;
			canvas.draw(trailSprite);
			
			super.render();
		}
		
		public function find_target():Enemy
		{
			var enes:Array = PlayState.lyr_enemy.members;
			var ene:Enemy = null;
			var ene_min:Enemy = null;
			var ene_p:Point; //porque si ponia la p antes quedaba feo
			
			var min_dist:Number = FlxG.width * 2;//iniciado en un numero exagerado
			
			var dist:Number;
			var pos:Point = new Point(x, y);
			var hw:Number = FlxG.width / 2.0;
			for (var i:uint = 0; i < enes.length;  i++) {
				ene = enes[i]; 
				//le saque la primer condicion porque es imposible que el members tenga un ene == null
				if (ene.exists && (!ene.dead) && (ene.x>hw)){
					ene_p = new Point(ene.x, ene.y);
					dist= Utils.point_distance(ene_p, pos);
					if (dist < min_dist) {
						ene_min = ene;
						min_dist = dist;
					}
				}
			}
			return ene_min;
		}
	}

}