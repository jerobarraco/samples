package
{
	import org.flixel.*;
	import com.game.*;
	
	[SWF(width = "640" , height = "480" , backgroundColor = "#c0c0c0")]
	[Frame(factoryClass="Preloader")]
	
	public class Main extends FlxGame
	{		
		public function Main():void
		{
			super(640, 480, HistoryState, 1);
		}		
	}
}