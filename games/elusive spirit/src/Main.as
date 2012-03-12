package  
{
	import org.flixel.*;
	import org.flixel.plugin.photonstorm.*;
	
	
	[SWF(width="640", height="400", backgroundColor="#000000")]
	[Frame(factoryClass = "Preloader")]
	
	/**
	 * ...
	 * @author Puccini
	 */
	public class Main extends FlxGame
	{
		
		
		public function Main() 
		{
			super(640, 400, MainMenuState, 1);
			forceDebugger = true;
		}
		
	}

}