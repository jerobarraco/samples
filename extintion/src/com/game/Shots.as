package com.game
{
	import org.flixel.*;
	
	public class Shots extends FlxSprite
	{
		public static var STATE_NORMAL:int = 0;
		public static var STATE_HOMMING:int =1;
		public static var STATE_ENE:int =2;
		
		public var state:int = STATE_HOMMING;
		
		[Embed(source = "/data/Spark.png")] private var ImgSpark:Class;
		[Embed(source = "/data/nave/shot.png")] private var ImgShot:Class;
		
		private var _sparks:FlxEmitter;
		
		public var speed:Number = 200;
		
		public var target:Enemy;
		
		public var friend:Boolean = true;
		
		public function Shots(X:Number, Y:Number):void 
		{
			super(X, Y);
			
			_sparks = PlayState.lyr_top.add(new FlxEmitter(X,Y)) as FlxEmitter;
			_sparks.createSprites(ImgSpark, 10, 16, true, 0);
			
			loadRotatedGraphic(ImgShot);
			
			maxThrust = speed;
			
			exists = false;
			
			target = find_target();
			
			angle = 180;
			
		}
		
		override public function update():void
		{
			if(state==STATE_NORMAL)
			{
				update_normal();
				//thrust = maxThrust;
			}
			else if(state==STATE_HOMMING)
			{
				update_homming();
				//thrust = maxThrust;
			}
			else if(state==STATE_ENE)
			{
				
			}
			
			var move:FlxPoint = FlxU.rotatePoint(1,0,0,0,angle);
			velocity.x = -move.x*speed;
			velocity.y = -move.y*speed;
			
			if(!onScreen())
				this.kill()
			
			super.update();
		}
		
		public function update_normal():void
		{
			angle = 180;
		}
		
		public function update_homming():void
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
			
			angle = da;
		}
		
		override public function hitLeft(Contact:FlxObject, Velocity:Number):void
		{
			kill();
		}
	
		override public function hitRight(Contact:FlxObject, Velocity:Number):void
		{
			kill();
		}
		
	    override public function hitTop(Contact:FlxObject, Velocity:Number):void
		{
			kill();
		}
		
		override public function hitBottom(Contact:FlxObject, Velocity:Number):void
		{
			kill();
		}
		
		override public function kill():void
		{
			_sparks.x = x +width/2+(velocity.x*FlxG.elapsed)/2;
			_sparks.y = y+height/2 ;
			_sparks.start(true,0.05,10);
			
			super.kill();
		}
		
		override public function reset(X:Number, Y:Number):void
		{
			angle = 180;
			thrust = 0;
			velocity.x = velocity.y = 0;
			acceleration.x = acceleration.y = 0;
			
			target = find_target();
			
			if(friend)
			{
				color = 0xffff0000;
			}
			else
			{
				color = 0xff0000ff;
			}
			
			super.reset(X,Y);
		}
		
		public function find_target():Enemy
		{
			var enes:Array = PlayState.enemies_array;
			
			for(var i:int; i<enes.length; i++)
			{
				var ene:Enemy = enes[i];
				if(ene.exists)
				{
					return ene;
				}
				else
					return null;
			}
			return null;
		}
		
	}

}