package  
{
	import org.flixel.FlxObject;
	import org.flixel.FlxG;
	import Player;
	/**
	 * ...
	 * @author ...
	 */
	public class Action 
	{
		public var wait:Number  = 0;
		public var type:int = 0;
		
		public function Action() 
		{
			
		}
		public function reset():void {
			wait = 0;
			type = 0;
		}
		public function activate(shadow:Player):void {
			//FlxG.log("Action: " + String(this.type));
			switch(this.type) {
				case Registry.AC_JUMP:
					shadow.jump(); break;
				case Registry.AC_RESTART:
					shadow.goback(); break;
				case Registry.AC_ACCEL:
					shadow.accell(); break;
				case Registry.AC_DECCEL:
					shadow.deccell(); break;
				default:
					FlxG.log("action unknown");
			}
			
		}
		
	}

}