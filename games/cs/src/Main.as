package
{
	import com.game.*;
	
	import org.flixel.*;
	
	[SWF(width = "640" , height = "480" , backgroundColor = "#C0C0C0")]
	[Frame(factoryClass="Preloader")]
	
	public class Main extends FlxGame
	{		
		public function Main():void
		{
			super(640, 480, HistoryStart, 1);
		}		
	}
}