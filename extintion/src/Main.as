package
{
	import org.flixel.*;
	import com.game.PlayState;
	import com.game.MenuState
	
	[SWF(width = "640" , height = "480" , backgroundColor = "#c0c0c0")]
	[Frame(factoryClass="Preloader")]
	
	public class Main extends FlxGame
	{		
		public function Main():void
		{
			super(640, 480, PlayState, 1);
		}		
	}
}