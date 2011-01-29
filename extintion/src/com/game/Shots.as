package com.game
{
	import flashx.textLayout.elements.SpecialCharacterElement;
	
	import org.flixel.*;
	
	public class Shots extends FlxSprite
	{
		public static var STATE_NORMAL:int = 0;
		public static var STATE_HOMMING:int =1;
		
		public var state:int = STATE_HOMMING;
		
		[Embed(source = "/data/Spark.png")] private var ImgSpark:Class;
		
		private var _sparks:FlxEmitter;
		
		private var speed:Number = 200;
		
		public var target:FlxPoint = new FlxPoint();
		
		public function Shots(X:Number, Y:Number):void 
		{
			super(X, Y);
			
			_sparks = PlayState.lyr_top.add(new FlxEmitter(X,Y)) as FlxEmitter;
			_sparks.createSprites(ImgSpark, 10, 16, true, 0);
			
			createGraphic(6,2,0xffff0000);
			
			maxThrust = speed;
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
			
			thrust = maxThrust;
			
			if(x>FlxG.width
			|| x<0
			|| y<0
			|| y> FlxG.height)
				this.kill()
			
			super.update();
		}
		
		public function update_normal():void
		{
			angle = 180;
		}
		
		public function update_homming():void
		{
			//Aiming
			var dx:Number = (x + width / 2) - target.x;
			var dy:Number = (y + height / 2) - target.y;
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
			angle = 0;
			thrust = 0;
			maxThrust = speed;
			velocity.x = velocity.y = 0;
			acceleration.x = acceleration.y = 0;
			
			super.reset(X,Y);
		}
		
	}

}