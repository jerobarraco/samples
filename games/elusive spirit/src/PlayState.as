package  
{
	import org.flixel.*;
		import org.flixel.plugin.photonstorm.*;
	/**
	 * ...
	 * @author Puccini
	 */
	public class PlayState extends FlxState
	{
		
		//[Embed (source = "/Data/Sounds/psyrider.mp3") ] private var musicMP3:Class;
		[Embed (source = "/Data/Sprites/fondo.png") ] private var fondoPNG:Class;
		public static var player:Player;
		public static var shadow:Player;
		public static var focus:Focus;
		public var level:Level;
		public var objetos:ObjetoManager;
		public static var actions:Array;
		public static var cant_actions:int = 1000;
		public  static var record_act:int = 0;
		public  static var play_act:int = 0;
		public static var playing:Boolean = false;
		//private static var watches:FlxText;
		private static var line:FlxSprite;
		private static var ppos:Position;
		private static var spos:Position;
		
		public static var back: FlxSprite;
		public function PlayState()   
		{
			
		}
		
		override public function create(): void
		{
			
			//FlxG.play(musicMP3, 1);
			
			
			actions = new Array();
			for (var i:int = 0; i < cant_actions; i++) {
				actions.push(new Action());
			}
			
			Registry.objetos = new ObjetoManager();
			player = new Player(Registry.START_TILE, 17, false);
			shadow = new Player(Registry.START_TILE, 17, true);
			focus = new Focus(player);
			level = new Level();
			ppos = new Position(false);
			spos = new Position(true);
			back = new FlxSprite(0, 0);
			back.loadGraphic(fondoPNG, false, false, 927, 676, true);
			
			back.scrollFactor.x = 0;
			
			back.x = 0;
			back.y = level.height-676;
			
			
			FlxG.camera.setBounds(0, 0, level.width, level.height);
			FlxG.camera.follow(focus, FlxCamera.SHAKE_BOTH_AXES);
			FlxG.worldBounds = new FlxRect(0, 0, level.width, level.height);
			
			line = new FlxSprite(FlxG.camera.width, 6);
			line.makeGraphic(FlxG.camera.width, 6, 0x00000000, true);			
			line.drawLine(0, 0, FlxG.camera.width, 0, 0xFFFFFFFF, 2);
			line.scrollFactor.x = line.scrollFactor.y = 0;
			line.x = 0;
			var y : int =FlxG.camera.height - 20;
			spos.y = ppos.y = y - 10;
			line.y = y;
			
			/*watches = new FlxText(30, 30, 200, "");
			watches.scrollFactor.x = watches.scrollFactor.y = 0;
			*/
			/*{ 
				//Registry.objetos = new ObjetoManager();
			
			
			//Creo switches								// primero se crea los switches 
			
			Registry.objetos.AddSwtich(43, 15);		
			Registry.objetos.AddSwtich(57, 13);
			Registry.objetos.AddSwtich(89, 17);
			Registry.objetos.AddSwtich(94, 13);
			Registry.objetos.AddSwtich(97, 9);
			Registry.objetos.AddSwtich(120, 7);
			Registry.objetos.AddSwtich(130, 9);
			Registry.objetos.AddSwtich(220, 17);
			Registry.objetos.AddSwtich(105, 13);
			Registry.objetos.AddSwtich(200, 15);
			Registry.objetos.AddSwtich(150, 13);
			Registry.objetos.AddSwtich(215, 9);
			Registry.objetos.AddSwtich(163, 9);
			Registry.objetos.AddSwtich(243, 11);
			Registry.objetos.AddSwtich(170, 9);
			Registry.objetos.AddSwtich(138, 11);
			Registry.objetos.AddSwtich(205, 13);
			Registry.objetos.AddSwtich(187, 7);
			Registry.objetos.AddSwtich(230, 11);
			Registry.objetos.AddSwtich(221, 9);
			
			
			
			
			// Creo objetos y se los meto a los putos switches 
			
			//Objetos que no dependen de ningun switch 
			Registry.objetos.AddFastSpeed(30, 17, true,[0,3,16,11]);			// segundo se crean los objetos  y se mandan un arreglo con los switch asociados
			Registry.objetos.AddImpulsor(36,17, true,[0,4]);					
			Registry.objetos.AddLowSpeed( 40, 15, true, [0,4]);
			Registry.objetos.AddCaja(46, 15, true);
			Registry.objetos.AddFastSpeed(49, 15, true, [0,3]);
			Registry.objetos.AddImpulsor(57, 17, true, [0]);
			
			//Objetos de switches
			Registry.objetos.AddFastSpeed(62, 13, true, [1]);
			Registry.objetos.AddFastSpeed(63, 13, true, [1,3,5,11,17]);
			Registry.objetos.AddLowSpeed(64, 13, false, [1]);
			Registry.objetos.AddFastSpeed(68, 17, false, [1,19,4,11,17]);
			Registry.objetos.AddFastSpeed(71, 17, false, [1,2]);
			
			
			Registry.objetos.AddFastSpeed(79, 11, true, [2,3,8]);
			Registry.objetos.AddLowSpeed(96, 15, false, [2]);
			Registry.objetos.AddImpulsor(71, 15, false, [2,1]);
			Registry.objetos.AddLowSpeed(78, 15, true, [2,3,9,12,0]);
			Registry.objetos.AddFastSpeed(107, 17, true, [2]);
			
			
			Registry.objetos.AddLowSpeed(72, 15, false, [3,16,0,7]);
			
			
			Registry.objetos.AddLowSpeed(91, 15, true, [4]);
			
			
			Registry.objetos.AddFastSpeed(113, 17, true, [4,6,8,11,17]);
			Registry.objetos.AddFastSpeed(120, 15, true, [4,1]);
			Registry.objetos.AddFastSpeed(130, 17, true, [4]);
			Registry.objetos.AddFastSpeed(140, 15, true, [4,2,0,17]);
			Registry.objetos.AddFastSpeed(150, 17, true, [4]);
			
			Registry.objetos.AddFastSpeed(133, 11, true, [4,8,11,18,4, 15,0]);
			Registry.objetos.AddFastSpeed(120, 7, true, [4,3 ,13,19,8,12]);
			Registry.objetos.AddFastSpeed(210, 9, true, [4,15,16,3,5]);
			Registry.objetos.AddFastSpeed(110, 12, true, [4,14,16,18,5,9]);
			Registry.objetos.AddFastSpeed(237, 17, true, [4,1,5,14,18,13]);
			
			
			Registry.objetos.AddFastSpeed(111, 11, true, [4,17]);
			Registry.objetos.AddFastSpeed(222, 17, true, [4]);
			Registry.objetos.AddFastSpeed(185, 15, true, [4]);
			Registry.objetos.AddFastSpeed(172, 9, true, [4,3]);
			Registry.objetos.AddFastSpeed(211, 11, true, [4]);
			
			
			Registry.objetos.AddFastSpeed(163, 17, true, [4,19,11,13]);
			Registry.objetos.AddFastSpeed(170, 15, true, [4]);
			Registry.objetos.AddFastSpeed(180, 17, true, [4]);
			Registry.objetos.AddFastSpeed(190, 15, true, [4,0,7,15]);
			Registry.objetos.AddFastSpeed(200, 17, true, [4]);
			
			
			
			
			Registry.objetos.AddImpulsor(110, 17, true, [4,3,2,1,0]);
			Registry.objetos.AddImpulsor(80, 17, false, [4]);
			Registry.objetos.AddImpulsor(50, 17, true, [4,3,2,1,0]);
			Registry.objetos.AddImpulsor(130, 17, true, [1,3]);
			
			
			//cajas
			
			Registry.objetos.AddCaja(152, 17, true);
			Registry.objetos.AddCaja(114, 17, true);
			Registry.objetos.AddCaja(185, 17, true);
			Registry.objetos.AddCaja(276, 17, true);
			Registry.objetos.AddCaja(137, 17, true);
			Registry.objetos.AddCaja(207, 17, true);
			
			
			
			
			
			/*
			Registry.objetos.AddFastSpeed(97,16, true, [2]);
			Registry.objetos.AddLowSpeed(105,16, false, [2]);
			Registry.objetos.AddLowSpeed(107, 16, false, [2]);
			Registry.objetos.AddFastSpeed(118,16, true, [2]);
			Registry.objetos.AddFastSpeed(137,16, false, [2]);
			Registry.objetos.AddFastSpeed(105, 16, true, [0]);
			Registry.objetos.AddFastSpeed(140, 16, true, [2]);
			Registry.objetos.AddFastSpeed(145, 16, true, [2]);
			Registry.objetos.AddFastSpeed(146, 16, true, [2]);
			
			
			}*/
			//add(fondo);
			add(back);
			add(level);
			//add(Registry.mapObjects);
			add(Registry.objetos.ObjetoGroup);
			add(Registry.objetos.switches);
			add(focus);
			add(player);
			add(shadow);
			add(Registry.fx);
			
			add(line);
			add(ppos);
			add(spos);
			//add(watches);
		}
		
		override public function update(): void
		{
			super.update();
			FlxG.collide(player, level);
			FlxG.collide (shadow, level);
			FlxG.overlap(player, Registry.objetos.ObjetoGroup, Registry.objetos.hitObjeto); 		
			FlxG.overlap(player, Registry.objetos.switches, Registry.objetos.hitSwitch);
			FlxG.overlap(player, shadow, ShadowTouched);
			var t:Number = FlxG.elapsed; // lo guardo en la var por si cambia mientras la uso
			actions[record_act].wait += t;
			if (playing) {
				if (play_act<=record_act){//intencionalmente <=
					actions[play_act].wait -= t;
					if (actions[play_act].wait <= 0) {
						actions[play_act].activate(shadow);
						var next:int = (play_act + 1 )% cant_actions;
						actions[next].wait += actions[play_act].wait; //siqueda <0 fixeamos el error de tiempo
						play_act = next;
					}
				}
			}
			
			/*var tt:String = "";
			tt += "play "  + String(play_act);
			tt += "\nrecord " + String(record_act);
			tt += "\nspeed " + String(player.velocity.x);
			tt += "\nmspeed " + String(player.maxVelocity.x);
			tt += "\nx " + String(int(player.x/Registry.tilesize));
			
			tt += "\nsspeed " + String(shadow.velocity.x);
			tt += "\nsmspeed " + String(shadow.maxVelocity.x);
			tt += "\nsx " + String(int(shadow.x/Registry.tilesize));
			
			watches.text = tt;*/
			
		}
		public static function StartShadow():void {
			if (playing) return;
			playing  = true;
			shadow.active = true;
			shadow.visible = true;
			spos.setAlive(true);
			//FlxG.log("startshadow");
		}
		public static function AddAction(type:int):void {
			actions[record_act].type = type;
			record_act += 1;
			if (record_act >= cant_actions) {
				record_act -= cant_actions;
			}
			actions[record_act].reset();
			FlxG.log("add action " + String(type));
		}
		public static function ShadowTouched(player:FlxObject, objeto:FlxObject):void {
			if (!shadow.active ) return;
			player.active = false;
			shadow.active = false;
			playing = false;
			if ( player.x < shadow.x+shadow.width/2)
			{
				FlxG.camera.flash(0xffffffff, 2, PlayState.restart);
			}else {
				FlxG.camera.flash(0xff0000, 2, PlayState.restart);

			}
		}
		public static function restart():void {
			play_act = 0;
			record_act = 0;
			playing = false;
			actions[record_act].reset();
			shadow.x = player.x = Registry.tilesize * Registry.START_TILE;
			//shadow.y = player.y = 17;
			player.shadow_started = false;
			
			shadow.active = false;
			shadow.visible = false;
			shadow.velocity.x = shadow.maxVelocity.x = player.velocity.x = player.maxVelocity.x;
			player.active = true;
			spos.setAlive (false);
		}
	}

}