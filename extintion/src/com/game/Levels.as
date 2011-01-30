package com.game
{
	import org.flixel.*;

	public class Levels extends FlxObject
	{
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme1.mp3')] private var MusMain1:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme2.mp3')] private var MusMain2:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme3.mp3')] private var MusMain3:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme4.mp3')] private var MusMain4:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme5.mp3')] private var MusMain5:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme6.mp3')] private var MusMain6:Class;
		[Embed(source = '/Data/Musica/sound.swf', symbol = 'Maintheme7.mp3')] private var MusMain7:Class;
		
		private var cur_level:int = 0
			
		public static var main_theme:FlxSound = new FlxSound;
		
		public function Levels(Level:int)
		{
			cur_level = Level;
			
			if(cur_level==0)
			{
				main_theme.loadEmbedded(MusMain7,true);
			}
			else if(cur_level==1)
			{
				main_theme.loadEmbedded(MusMain6,true);
			}
			else if(cur_level==2)
			{
				main_theme.loadEmbedded(MusMain5,true);
			}
			else if(cur_level==3)
			{
				main_theme.loadEmbedded(MusMain4,true);
			}
			else if(cur_level==4)
			{
				main_theme.loadEmbedded(MusMain3,true);
			}
			else if(cur_level==5)
			{
				main_theme.loadEmbedded(MusMain2,true);
			}
			else if(cur_level==6)
			{
				main_theme.loadEmbedded(MusMain1,true);
			}
			
			main_theme.play();
		}
		
		private var timer1:Number= 0;
		private var timer1_limit:Number =5;
		
		private var timer2:Number = 0;
		private var timer2_limit:Number = 2;
		
		private var timer3:Number = 0;
		private var timer3_limit:Number = 1;
		
		private var timer4:Number = 0
		private var timer4_limit:Number = .5;
		
		private var fase1:Boolean =false;
		private var fase2:Boolean =false;
		private var fase3:Boolean =false;
		
		override public function update():void
		{
			timer1+=FlxG.elapsed;
			timer2+=FlxG.elapsed;
			timer3+=FlxG.elapsed;
			timer4+=FlxG.elapsed;
			
			if(cur_level==0)
			{
				if(!fase1)
				{
					if(timer3>timer3_limit)
					{
						fase1 = true;
						timer3=0;
					}
				}
				else
				{
					if(timer4>timer4_limit)
					{
						spawn_enemys(Enemy.TYPE_UNO)
						timer4=0;
					}
				}
			}
			else if(cur_level==1)
			{
				if(!fase1)
				{
					if(timer2>timer3_limit)
					{
						fase1=true;
						timer2=0;
					}
				}
				else
				{
					if(timer4>timer4_limit)
					{
						spawn_enemys(Enemy.TYPE_UNO)
						timer4=0;
					}
					
					if(!fase2)
					{
						if(timer1>timer1_limit)
						{
							fase2=true;
							timer1=0;
						}
					}
					else
					{
						if(timer3>timer3_limit)
						{
							spawn_enemys(Enemy.TYPE_DOS);
							timer3 =0;
						}
						
					}
				}
			}
			else if (cur_level==2)
			{
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_UNO)
					timer2=0;
				}
					
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer3 =0;
				}
				
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_TRES);
					timer1 =0;
				}
			}
			
			super.update();
		}
		
		public function spawn_enemys(type:int):void
		{
			var pos:FlxPoint = new FlxPoint;
			var ene:Enemy;
			
			if(type == Enemy.TYPE_UNO)
			{
				for(var j:int; j< Math.floor(Math.random()*4); j++)
				{
					ene = PlayState.get_single_enemy();
					ene.type = Enemy.TYPE_UNO;
					ene.reset(FlxG.width,Math.random()*(FlxG.height-ene.height)+ene.height)
				}
			}
			else if(type == Enemy.TYPE_DOS)
			{
				pos.set(FlxG.width,Math.random()*FlxG.height);
				for(var i:int; i<10; i++)
				{
					ene = PlayState.get_single_enemy();
					ene.type = Enemy.TYPE_DOS;
					ene.init_pos = pos;
					ene.move_angle = 30*i;
					ene.reset(pos.x+ene.width*i,pos.y)
				}
			}
			else if(type == Enemy.TYPE_TRES)
			{
				var gap:Number = 80;
				ene = PlayState.get_single_enemy();
				ene.type = Enemy.TYPE_TRES;
				ene.reset(FlxG.width, FlxG.height/2 +gap);
				
				ene = PlayState.get_single_enemy();
				ene.type = Enemy.TYPE_TRES;
				ene.reset(FlxG.width, FlxG.height/2 -gap);
			}
			else if(type == Enemy.TYPE_CUATRO)
			{
				gap = Math.random()*100+50;
				ene = PlayState.get_single_enemy();
				ene.type = Enemy.TYPE_CUATRO;
				ene.reset(FlxG.width, FlxG.height/2 +gap);
				
				ene = PlayState.get_single_enemy();
				ene.type = Enemy.TYPE_CUATRO;
				ene.reset(FlxG.width, FlxG.height/2 -gap);
			}
			else if(type == Enemy.TYPE_CINCO)
			{
				for(var k:int; k< Math.floor(Math.random()*6); k++)
				{
					ene = PlayState.get_single_enemy();
					ene.type = Enemy.TYPE_CINCO;
					ene.reset(FlxG.width,Math.random()*(FlxG.height-ene.height)+ene.height)
				}
			}
		}
	}
}