package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	/**
	 * ...
	 * @author Puccini
	 */
	public class Player extends FlxSprite
	{
		[Embed (source = "/Data/Sprites/player.png") ] private var playerPNG:Class;
		[Embed (source = "/Data/Sprites/ghost.png") ] private var shadowPNG:Class;
		
		private var jumpSpeed:Number = 0; 

		private var shadow:Boolean = false;
		private var acc:int = 10;
		public var shadow_started : Boolean = false;
		public var diftime:Number = 0;
		public function Player(x:int, y:int, shadow:Boolean ) 
		{			
			super(x * Registry.tilesize, y * Registry.tilesize);
			if (FlxG.getPlugin( FlxControl) == null)
			{
				FlxG.addPlugin( new FlxControl);
			}
			
			this.shadow = shadow;
			if (shadow){
				loadGraphic(shadowPNG, true, true, 41, 50, true);
				this.active = false;
				this.visible = false;
				FlxControl.create(this, FlxControlHandler.MOVEMENT_ACCELERATES, FlxControlHandler.STOPPING_DECELERATES, 2, true, false);
				FlxControl.player2.setGravity(0, 400);
			}else {
				loadGraphic(playerPNG, true, true,  41, 50, true);
				//FlxControl.player1.setJumpButton("Z", FlxControlHandler.KEYMODE_PRESSED, 250, FlxObject.FLOOR, 250, 200);
				
				FlxControl.create(this, FlxControlHandler.MOVEMENT_ACCELERATES, FlxControlHandler.STOPPING_DECELERATES, 1, true, false);
				FlxControl.player1.setFireButton("CONTROL", FlxControlHandler.KEYMODE_PRESSED, 250, fire);
				FlxControl.player1.setGravity(0, 400);
			}
			addAnimation("stop", [0], 0, false);
			addAnimation("walk", [0, 1, 2, 3, 4], 5, true);
			addAnimation("jump", [3], 0, false);
			
			
			maxVelocity.y = 300;
			velocity.x  = maxVelocity.x = 250;//very muy important // que arranque con la max para no tener problemas con la sombra
			
			width = 28;
			height = 35;
			
			offset.x = 0;
			offset.y = 11;
			
			//Registry.arma = new BulletManager();
			
			//FlxControl.player1.setCursorControl(false, false, true, true);
			//FlxControl.player1.setWASDControl(false, false, true, true);
			
			//FlxControl.player1.setMovementSpeed(400, 0, 100, 200, 400, 0);
		}
		
		override public function update():void 
		{
			super.update();
			if (touching && FlxObject.FLOOR)
			{
				if (velocity.x != 0){
					play("walk");
					this._curAnim.delay = 1/(velocity.x/40)  ;
					
				}
				else{
					play("stop");
				}
				
			}
			else //if (velocity.y < 0)
			{
				play("jump");
			}
			if (velocity.x < maxVelocity.x)
				velocity.x += acc * FlxG.elapsed;
			
			if ( velocity.x > maxVelocity.x)
				velocity.x = maxVelocity.x;
			
			if (shadow) return;
			
			//codigo no para el shadow
			if(FlxG.keys.justPressed("Z"))
			{
				jump();
			}
			var ontile:int = (x / Registry.tilesize);
			
			if (ontile>Registry.TILE_MAP_END)		
			{
				goback();
			}
			
			
			if (shadow_started) return;

			diftime = Registry.TILE_MAP_END - ontile ;
			diftime *= Registry.tilesize;
			diftime /= maxVelocity.x ;
			
			//FlxG.watch(this, "diftime");
			if (diftime < 3 ){ // segundos
				PlayState.StartShadow();
				shadow_started = true;
			}
		}
		public function jump():void
		{
			if (!(touching && FlxObject.FLOOR))
				return;
			velocity.y -= 250;
			
			if (!shadow)
				PlayState.AddAction(Registry.AC_JUMP);
		}
		public function goback():void {
			var nx:int = Registry.START_TILE * Registry.tilesize;
			x = nx;
			
			if (!shadow) 
				PlayState.AddAction(Registry.AC_RESTART);
			else
				FlxG.log("shadow restarted");
		}
		public function accell():void {
			maxVelocity.x += acc*2;
			if (!shadow) 
				PlayState.AddAction(Registry.AC_ACCEL);
		}
		public function deccell():void {
			maxVelocity.x -= acc*2;
			if (!shadow) 
				PlayState.AddAction(Registry.AC_DECCEL);
		}
		
		public function trip():void{
			velocity.x -= (maxVelocity.x *2)/ acc ;
			if (!shadow)
				PlayState.AddAction(Registry.AC_TRIP);
		}
		
		private function fire():void
		{
			
			//Registry.arma.fire(x, y, this.facing);
			
		}
		
		
	}

}