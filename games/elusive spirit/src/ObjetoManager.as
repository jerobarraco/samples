package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	/**
	 * ...
	 * @author Puccini
	 */
	public class ObjetoManager 
	{
		
		public var ObjetoGroup:FlxGroup;
		public var switches:FlxGroup;
		
		
		
		public function ObjetoManager() 
		{
			super();
			
			switches = new FlxGroup();
			ObjetoGroup = new FlxGroup();			
		}
		
		
		public function AddSwtich(x:int , y:int):void
		{
			
			var switchs:Switches = new Switches(x, y);
			switches.add(switchs);
			
		}
		
		
		
		
		// Agrega un bloque que reduce la velocidad
		public function AddLowSpeed(x:int , y:int, activated:Boolean, NumSwitch:Array):void
		{
			var low:Objetos = new LowSpeed( x, y, activated);
			ObjetoGroup.add(low);
			/*for (var i:int = 0; i < NumSwitch.length; i++)
			{
				Switches(switches.members[NumSwitch[i]]).Add(low);
			}*/
		}

		// Agrega un bloque que aumenta la velocidad
		public function AddFastSpeed(x:int, y:int, activated:Boolean, NumSwitch:Array):void
		{
			var fast:Objetos = new FastSpeed(x, y, activated);
			ObjetoGroup.add(fast);
			/*for (var i:int = 0; i < NumSwitch.length; i++)
			{
				Switches(switches.members[NumSwitch[i]]).Add(fast);
			}*/
		}
		
		// te tira para arriba 
		public function AddImpulsor(x:int, y:int,activated:Boolean, NumSwitch:Array):void
		{
			var imp:Objetos = new Impulsor(x, y,activated);
			ObjetoGroup.add(imp);
			/*for (var i:int = 0; i < NumSwitch.length; i++)
			{
				Switches(switches.members[NumSwitch[i]]).Add(imp);
			}*/
		}
		
		
		public function AddCaja(x:int, y:int, activated:Boolean):void
		{
			var caja:Objetos = new Caja(x, y, activated);
			ObjetoGroup.add(caja);
		}
		
		/*override public function update():void
		{
			//super.update();
		}*/
		
		
		
		public function hitObjeto(player:FlxObject, objeto:FlxObject):void
		{
			((Objetos) (objeto).hit(player));
			
			
		}
		
		
		public function hitSwitch(player:FlxObject, objeto:FlxObject):void
		{
			Switches(objeto).PushSwitch();
		}
		
	}

}