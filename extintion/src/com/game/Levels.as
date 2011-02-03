package com.game
{
	import org.flixel.*;

	public class Levels extends FlxObject
	{
		private var cur_level:int = 0
			
		
		public function Levels(Level:int)
		{
			cur_level = Level;
			
		}
		
		private var timer1:Number= 0;
		private var timer1_limit:Number = 5;
		
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
			
			if(cur_level==0) //----------------------- NIVEL 0 ------------------------------
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
						timer4=0.3;
					}
				}
			}
			else if(cur_level==1)//---------------- NIVEL 1 --------------------------------
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
						timer4=0.2;
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
						if(timer4>timer4_limit)
						{
							spawn_enemys(Enemy.TYPE_UNO);
							timer1 =0;
						}
						
						if(timer1>timer1_limit)
						{
							spawn_enemys(Enemy.TYPE_DOS);
							timer1 =0;
						}
						
					}
				}
			}
			else if (cur_level==2)//-------------------------- NIVEL 2 ---------------------------
			{
				if(timer4>timer4_limit)
				{
					spawn_enemys(Enemy.TYPE_UNO);
					timer4=0.2;
				}
					
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer2=0;
				}
				
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_UNO);
					timer1 =0.2;
				}
			}
			else if (cur_level==3)//-------------------------- NIVEL 3 ---------------------------
			{
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_UNO);
					timer3=0;
				}
					
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer2=0;
				}
				
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer1 =0;
				}
			}
			else if (cur_level==4)//-------------------------- NIVEL 4 ---------------------------
			{
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_UNO);
					timer3=0;
				}
					
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer2=0;
				}
				
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer1 =0;
				}
			}
			else if (cur_level==5)//-------------------------- NIVEL 5 ---------------------------
			{
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer2=0;
				}
					
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer3=0;
				}
				
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer1 =0;
				}
			}
			else if (cur_level==6)//-------------------------- NIVEL 6 ---------------------------
			{
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer2=0;
				}
					
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer3=0;
				}
				
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer1 =0;
				}
			}
			else if (cur_level==7)//-------------------------- NIVEL 7 ---------------------------
			{
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer2=-0.5;
				}
					
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer3=0;
				}
				
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer1 =-2;
				}
			}
			else if (cur_level==8)//-------------------------- NIVEL 8 ---------------------------
			{
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_DOS);
					timer2=0;
				}
					
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer3=0;
				}
				
				if(timer4>timer4_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer4 =-3;
				}
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_TRES);
					timer1 =0;
				}
			}
			else if (cur_level==9)//-------------------------- NIVEL 9 ---------------------------
			{
							
				if(timer4>timer4_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer4=0;
				}
				
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer4 =-1;
				}
				if(timer1>timer1_limit)
				{
					spawn_enemys(Enemy.TYPE_TRES);
					timer1 =0;
				}
			}
			else if (cur_level==10)//-------------------------- NIVEL 10 ---------------------------
			{
						
				if(timer4>timer4_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer4=0;
				}
				
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer3=0;
				}
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_TRES);
					timer2 =0;
				}
			}
			else if (cur_level==11)//-------------------------- NIVEL 11 ---------------------------
			{
				if(timer4>timer4_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer4=0;
				}
				
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer3=0;
				}
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_TRES);
					timer2 =0;
				}
			}
			else if (cur_level==12)//-------------------------- NIVEL 12 ---------------------------
			{
				if(timer4>timer4_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer4=0;
				}
				
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer3=0;
				}
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_TRES);
					timer2 =0;
				}
			}
			else if (cur_level==13)//-------------------------- NIVEL 13 ---------------------------
			{
				if(timer4>timer4_limit)
				{
					spawn_enemys(Enemy.TYPE_CINCO);
					timer4=0;
				}
				
				if(timer3>timer3_limit)
				{
					spawn_enemys(Enemy.TYPE_CUATRO);
					timer3=2;
				}
				if(timer2>timer2_limit)
				{
					spawn_enemys(Enemy.TYPE_TRES);
					timer2 =1;
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