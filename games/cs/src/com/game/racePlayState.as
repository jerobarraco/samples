package com.DeathRace
{
	import flash.events.MouseEvent;
	import flash.events.Event;
	
	
	import flash.geom.Point;
	import flash.geom.Rectangle;
	import flash.net.navigateToURL;
	import flash.net.URLRequest;
	import flash.ui.Mouse;
	import org.flixel.*
	
	import flash.display.Shader;
	import flash.filters.ShaderFilter;
		
	public class racePlayState extends FlxState
	{
		//shader
		[Embed(source = '../../Data/OldSchool_wave.pbj', mimeType = 'application/octet-stream')] private var FltOldSchool:Class;
		//sonidos
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'tv_turn_on.mp3')] private var SndTvon:Class;
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'tv_hum.mp3')] private var SndTvHum:Class;
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'tv_turn_off.mp3')] private var SndTvOff:Class;
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'RAINDANCE.mp3')] private var SndPsy:Class;
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'suspiro.mp3')] private var SndSusp:Class;
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'heart_Beat.wav')] private var SndHeart:Class;
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'breath.wav')] private var SndBreath:Class;
		[Embed(source = '../../Data/Audio/sonidos.swf', symbol = 'gymnopedie.wav')] private var SndCrash:Class;
		
		
		private static var VIDEO_NORMAL_ZOOM:Number = 0.50;
		private static var VIDEO_CRASH_ZOOM:Number = 1.5;
		private static var VIDEO_PSY_ZOOM:Number = 1.7;
		
		//variable del estado
		public static var Main:PlayState;
		
		public var game_started:Boolean = false;
		
		private var _sndTvon:FlxSound;
		private var _sndTvHum:FlxSound;
		private var _sndTvOff:FlxSound;
		private var _sndPsy:FlxSound;
		private var _sndSusp:FlxSound;
		private var _sndHeart:FlxSound;
		private var _sndBreath:FlxSound;
		private var _sndCrash:FlxSound;
		
		//si corre o no el hum
		private var _hum_playing:Boolean = false;
		private var _heart_playing:Boolean = false;
		
		//auto
		private var _car:Car;
		
		//video
		private var _video:Video;
		private var _lastPlayedFrame:uint;
		
		//fences
		private var _fenceL:Fences;
		private var _fenceH:Fences;
		
		//spawner
		private var _spawner:Spawner;
		
		//Hud
		private var _score:FlxText;
		
		//layers de cosas
		public static var lyrTrack:FlxGroup;
		public static var lyrSprites:FlxGroup;
		public static var lyrObstacles:FlxGroup;
		public static var lyrHUD:FlxGroup;
		public static var lyrExplotions:FlxGroup;
		
		//filtro gráfico
		private var _coolShader:Shader;
		private var _coolFilter:ShaderFilter;
		private var _shader_angle:Number;
		private var _shader_angleSpeed:Number = 500;
		private var _shader_minAngleSpeed:Number = 2;
		private var _shader_maxradio:Number = 160;
		private var _shader_maxInnerRadio:Number = 80;
		private var _shader_radio:Number = 0;
		private var _shader_innerRadio:Number = 0;
		
		//explosion
		private var _explosion:FlxEmitter;
		private var _explosionStar:FlxEmitter;
		
		//Star Power
		public var _starPower:Boolean = false;
		private var _starPower_time:Number = 0;
		private var _starPower_timeLimit:Number=600;
		
		//giros	
		private var _turn:String;
		public var turnX:int=0;
		private var TurnLeft:Number = -55;
		private var TurnRight:Number = 55;
		private var TurnCenter:Number = 0;
		private var _trackLine:Track;
		
		public function PlayState():void
		{
			super();
			
			//este estado es main
			Main = this;
		}
		
		override public function create():void
		{
			//inicia las capas
			lyrTrack= new FlxGroup;
			lyrSprites = new FlxGroup;
			lyrObstacles = new FlxGroup;
			lyrHUD = new FlxGroup;
			lyrExplotions = new FlxGroup;
			
			//inicia el shader del filtro de tv
			_coolShader = new Shader( new FltOldSchool());
			//desviación de los colores
			_coolShader.data.intensityRGB.value = [1.5, 1.5, 1.5];
			_coolShader.data.directionRGB.value = [0, 1, -1];
			_coolShader.data.frequencyRGB.value = [0.02, 0.02, 0.02];			
			//circulo de oscuridad
			_coolShader.data.center.value = [FlxG.width/2 ,FlxG.height/2 ];
			_coolShader.data.radius.value = [0];
			_coolShader.data.InnerRadius.value = [0];
			//scanlines
			_coolShader.data.lineSize.value = [2];
			_coolShader.data.lineSize2.value = [1];
			//crea el filtro
			_coolFilter = new ShaderFilter(_coolShader);
			
			//carga sonidos
			_sndTvon = new FlxSound();
			_sndTvon.loadEmbedded(SndTvon);
						
			_sndTvOff = new FlxSound();
			_sndTvOff.loadEmbedded(SndTvOff);
			
			_sndPsy = new FlxSound();
			_sndPsy.loadEmbedded(SndPsy);
			
			_sndSusp = new FlxSound();
			_sndSusp.loadEmbedded(SndSusp);
						
			_sndTvHum = new FlxSound();
			_sndTvHum.loadEmbedded(SndTvHum);
			
			_sndHeart = new FlxSound();
			_sndHeart.loadEmbedded(SndHeart);
						
			_sndBreath = new FlxSound();
			_sndBreath.loadEmbedded(SndBreath);
			
			_sndCrash = new FlxSound();
			_sndCrash.loadEmbedded(SndCrash);
			_sndCrash.volume = 1;
			
			
			//explosion
			_explosion = add(new FlxEmitter(0,0)) as FlxEmitter;
			for (var n:int = 0; n < 10; n += 1)
			{
				_explosion.add( new Explosion());
			}
			lyrExplotions.add(_explosion);
			
			//explosiones de estrellas
			_explosionStar = add(new FlxEmitter(0,0)) as FlxEmitter;
			for (var k:int = 0; k < 10; k += 1)
			{
				_explosionStar.add( new ExplosionStar());
			}
			lyrExplotions.add(_explosionStar);			
			
			//fondo
			FlxState.bgColor = 0xff8f8f8f;
			
			//score
			_score = new FlxText(0, 0, FlxG.width, "0");
			_score.color = 0xd8eba2;
			_score.size = 16;
			_score.alignment = "center";
			lyrHUD.add(_score);
			
			//auto
			_car = new Car(FlxG.width/2,FlxG.height/2+5);
			lyrSprites.add(_car);
			
			//video
			_video = new Video(_car);
			_video.Escala = VIDEO_CRASH_ZOOM;
			_video.zoom = VIDEO_CRASH_ZOOM;
			
			//agrega los layers al playstate
			this.add(lyrTrack);
			this.add(lyrObstacles);
			this.add(lyrSprites);
			this.add(lyrHUD);
			this.add(lyrExplotions);
		}
		
		public function startGame():void
		{
			//sonido tv
			_sndTvon.volume = 1;
			_sndTvon.play();
			
			_sndHeart.stop();
			_sndBreath.stop();
			
			//fondo ruta
			var img:uint = 0;
			var max_count_img:uint = 8;
			var count_img:uint = max_count_img;
			for (var i:int = 0; i <(FlxG.height+16)/1; i += 1)
			{
				if (count_img <= 0 )
				{
					if (img == 0)
					{
						img = 1;
						count_img = max_count_img;
					}
					else 
					{
						img = 0;
						count_img=max_count_img;
					}
				}
				else 
					count_img -= 1;
					
				
			lyrTrack.add(new Track(0,(i*1)-16,img,_car));
			}
			
			//fences
			_fenceL = new Fences(0, _car.y);
			lyrObstacles.add(_fenceL);
			_fenceH = new Fences(0, _car.y);
			lyrObstacles.add(_fenceH);
			//pone los fences en posicion
			_fenceL.origenx = turnX;
			_fenceL.desvioRadio = -80;
			_fenceH.origenx = turnX;
			_fenceH.desvioRadio = 80;
			
			//spawner de obstaculos
			_spawner = new Spawner(0,0,lyrObstacles,_car, this);
			add(_spawner);
						
			_video.ZoomVideo(VIDEO_NORMAL_ZOOM);
			_video.Escala = VIDEO_NORMAL_ZOOM;
			_video.zoom = VIDEO_NORMAL_ZOOM;
			
			game_started = true;
			_car.choco = false;
			
			FlxG.score = 0;
		}
		
		override public function update():void
		{	
			Mouse.show();
			
			//controles del video
			if (_video.loaded)
			{	
				if (!(game_started))
				{
					_video.video.stop();
					_video.video.titles.last_score.text = FlxG.score.toString();
					_video.video.titles.hi_score.text = FlxG.hi_score.toString();
					
					_video.video.titles.tembac_button.addEventListener(MouseEvent.CLICK, Goto_tembac);
					
					loop_sound(_sndHeart);
					loop_sound(_sndBreath);
					
				}
				else
				{
					//permite mover el auto
					_car.mover = true;
													
					//corre el video cuando el auto se mueve
					if (!_car.choco)
					{
						//corre la musica
						if (!_hum_playing)
						{
							FlxG.playMusic(SndTvHum);
							_hum_playing = true;
						}
						
						/*if (FlxG.keys.UP)					
						{*/
							//si no esta bajo el efecto de la estrella
							if (_starPower == false)
							{
								//loopea el video de la carrera
								if (_video.video.currentLabel == "EndLoop")
								{
									_video.video.gotoAndPlay("StartLoop");
								}
								else
								{
									_video.video.play();
								}
							}
							else
							{
								//timer de la estrella
								_starPower_time += 60 * FlxG.elapsed;
								
								//zoom pa alante
								if (_starPower_time < _starPower_timeLimit-120)
								{
									_video.TransitionZoom(VIDEO_PSY_ZOOM);
								}
								else if (_starPower_time > _starPower_timeLimit-120)
								{
									//empieza el zoom para atras
									_video.TransitionZoom(VIDEO_NORMAL_ZOOM);
								}
								if (_starPower_time > _starPower_timeLimit)
								{
									_starPower = false;
									//_spawner.ResetEnemies();
									_video.video.gotoAndPlay(_lastPlayedFrame);
									_video.video.alpha = 1;
									_video.ZoomVideo(VIDEO_NORMAL_ZOOM);
									
									//musica psy
									_sndPsy.stop();
								}
								
								if (_video.video.currentLabel == "PsychoEnd")
								{
									_video.video.gotoAndPlay("PsychoStart");
								}
								else
								{
									_video.video.play();
									if (_video.video.currentLabel == "PsychoStart")
									{
									_video.video.Psy2.play();
									_video.video.Psy1.play();
									}
								}
							}
							
							//curvas
							_turn = _video.video.turn;
							
							//localiza todos los miembros del track y los mueve en curva
								//giros
								if (_turn=="Center")
								{
									if (turnX < TurnCenter )
										turnX += 120*FlxG.elapsed;
									else if (turnX > TurnCenter )
										turnX -= 120*FlxG.elapsed;
									else
										{
											turnX = TurnCenter;										
										}
								}
				
								if (_turn == "Right")
								{
									if (turnX < TurnLeft )
										turnX += 120*FlxG.elapsed;
									else if (turnX > TurnLeft )
										turnX -= 120*FlxG.elapsed;
									else
										turnX = TurnLeft;
								}
				
								if (_turn == "Left")
								{
									if (turnX < TurnRight )
										turnX += 120*FlxG.elapsed;
									else if (turnX > TurnRight )
										turnX -= 120*FlxG.elapsed;
									else
										turnX = TurnRight
								}
								
							
							var l:uint = lyrTrack.members.length;
							for (var i:uint = 0; i < l; i++)
							{
								_trackLine = lyrTrack.members[i] as Track;
								_trackLine.origenx = turnX;
							}
								
							//la fuerza de giro para el auto
							_car.origenx = turnX - 25;
							_car.TurnForce = turnX /40;
							
							//mueve los fences
							_fenceL.origenx = turnX;
							_fenceL.desvioRadio = -80;
							_fenceH.origenx = turnX;
							_fenceH.desvioRadio = 80;
													
							//collisiones con los obstaculos
							FlxU.overlap(_car, lyrObstacles, Crash);
					}
					else
					{
						//frena el video despues de chocar
						if (_video.video.currentLabel == "FenceCrashEnd"
							|| _video.video.currentLabel == "FenceCrash2End"
							|| _video.video.currentLabel == "PedestrianHitEnd"
							|| _video.video.currentLabel == "PedestrianHit2End"
							|| _video.video.currentLabel == "CarCrashEnd" 
							|| _video.video.currentLabel == "CarCrash2End")
							{
							GameRestart();
							}
						else
						{
							_video.TransitionZoom(VIDEO_CRASH_ZOOM);
						}				
					}
				}
			}
			
			//restart
			if (FlxG.keys.ENTER && _car.choco && !game_started)
			{
				startGame();
			}
			
			super.update();
		}
		
		public function Crash(P:Car, E:Obstacles):void
		{
			if (!(E is Star))
			{
				var PercentajeVideo:uint = FlxU.random() * 100;
				
				//no esta bajo los efectosd e la estrella
				if (_starPower == false)
				{
					P.choco = true;
					_explosion.at(P);
					_explosion.start(true, 1);
					
					//apaga la tele
					_hum_playing = false;
					FlxG.music.stop();
					_sndTvOff.play();
					
					_sndCrash.play();
			
					if (E is Fences)
					{
						if (PercentajeVideo<50)
							_video.video.gotoAndPlay("FenceCrash");	
						else
							_video.video.gotoAndPlay("FenceCrash2");		
					}
					if (E is Pedestrian)
					{
						_spawner.DeleteEnemy(E);
						
						if (PercentajeVideo<50)
							_video.video.gotoAndPlay("PedestrianHit");	
						else
							_video.video.gotoAndPlay("PedestrianHit2");
					}
					if (E is OtherCar)
					{
						_spawner.DeleteEnemy(E);
						
						if (PercentajeVideo<50)
							_video.video.gotoAndPlay("CarCrash");
						else
							_video.video.gotoAndPlay("CarCrash2");
					}
					
					
				}
				else
				{
					//esta bajo los efectos de la estrella
					if (E is Fences)
					{
						P.choco = true;
						_explosion.at(P);
						_explosion.start(true, 1);
						_video.video.alpha = 1;
						
						//apaga la tele
						_hum_playing = false;
						FlxG.music.stop();
						_sndTvOff.play();
						_sndCrash.play();
						//musica psy
						_sndPsy.stop();
						
						
						if (PercentajeVideo<50)
							_video.video.gotoAndPlay("FenceCrash");	
						else
							_video.video.gotoAndPlay("FenceCrash2");		
					}
					else
					{
						if (!E.dead)
						{
							//suma y dibuja el Score
							FlxG.score+=100;
							_score.text = FlxG.score.toString();
						}
						
						
						E.kill();
						_spawner.DeleteEnemy(E);	
						
						_explosion.at(E);
						_explosion.start(true, 1);
											
						
												
						//sonido suspiro
						_sndSusp.play();						
					}
				}
			}
			else
			{
				//choca con la estrella
				_spawner.DeleteEnemy(E);
				
				_explosionStar.at(E);
				_explosionStar.start(true, 1);
				
				_starPower = true;
				//_spawner.ResetEnemies();
				_starPower_time = 0;
				
				//musica psy
				_sndPsy.play();
				
				//guarda el último frame de la carrera
				if (!(_video.video.currentLabel == "PsychoStart" || _video.video.currentLabel == "PsychoEnd"))
				{
				_lastPlayedFrame = _video.video.currentFrame;
				}
				_video.video.gotoAndPlay("PsychoStart");
				_video.video.alpha = 0.5;
			}
			
		}
		
	
		public function GameRestart():void
		{
			this.stage.removeChild(_video.video);
			_sndCrash.stop();
			FlxG.hi_score = Math.max(FlxG.hi_score, FlxG.score);
			FlxG.state = new PlayState();
			
		}
		
		override public function postProcess():void
		{
			if (!_car.choco && game_started)
			{
				//animación de prender la tele
				//angulo de distorsion
				if (_shader_angleSpeed > _shader_minAngleSpeed)
				{
					_shader_angleSpeed -= 400 * FlxG.elapsed;
					
					_coolShader.data.frequencyRGB.value = [0.002*_shader_angleSpeed, 0.002*_shader_angleSpeed, 0.002*_shader_angleSpeed];
					_coolShader.data.intensityRGB.value = [4.0, 4.0, 4.0];
				}
				else
				{
					_shader_angleSpeed = _shader_minAngleSpeed
					
					_coolShader.data.frequencyRGB.value = [0.02, 0.02, 0.02];
					_coolShader.data.intensityRGB.value = [1.5, 1.5, 1.5];
				}
				
				//luz prende
				if (_shader_radio < _shader_maxradio)
				{
					_shader_radio += 300 * FlxG.elapsed;
									
				}
				else
				{
					_shader_radio = _shader_maxradio;
					
					if (_shader_innerRadio < _shader_maxInnerRadio)
					{
						_shader_innerRadio += 400 * FlxG.elapsed;
					}
					else
					{
						_shader_innerRadio = _shader_maxInnerRadio;
						
					}
				}
			}
			else
			{
				//apaga la tele
				if (_shader_innerRadio > 0)
				{
					_shader_innerRadio -= 1000 * FlxG.elapsed;
					
					_shader_angleSpeed = 600;
					_coolShader.data.frequencyRGB.value = [0.002*_shader_angleSpeed, 0.002*_shader_angleSpeed, 0.002*_shader_angleSpeed];
					_coolShader.data.intensityRGB.value = [4.0, 4.0, 4.0];
									
				}
				else
				{
					_shader_innerRadio = 0;
					
					if (_shader_radio >0)
					{
						_shader_radio -= 400 * FlxG.elapsed;
					}
					else
					{
						_shader_radio = 0;
						
					}
				}
				
			}
			
			//pequeño bamboleo de las lineas
			if (_shader_angle < 359)
				_shader_angle += (FlxU.random()*_shader_angleSpeed)* FlxG.elapsed;
			else
				_shader_angle = 0;
				
			_coolShader.data.angleDegrees.value = [_shader_angle];
			
			//luz bordes
			_coolShader.data.radius.value = [_shader_radio];
			_coolShader.data.InnerRadius.value = [_shader_innerRadio];
			
			FlxG.buffer.applyFilter(FlxG.buffer, new Rectangle(0, 0, screen.width, screen.height), new Point(0, 0), _coolFilter);
			
		}
		
		private function loop_sound(Sono:FlxSound):void
		{
			if (!Sono.playing)
			{
				Sono.play();
			}
		}
		
		private function Goto_tembac(e:Event):void
		{
			navigateToURL( new URLRequest("http://www.tembac.com"));
		}
		
	}
		
}