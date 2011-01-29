package com.game
{
	import org.flixel.FlxEmitter;
	import org.flixel.FlxG;
	import org.flixel.FlxSprite;
	import org.osmf.layout.AbsoluteLayoutFacet;
	
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
		
		public function update_uno():void
		{
			x-= FlxG.elapsed * 100;
		}
		
		public function update_dos():void
		{
			
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
		
		
		
		override public function kill():void
		{
			explo.at(this);
			explo.start();
			
			
			super.kill();
		}
	}
}