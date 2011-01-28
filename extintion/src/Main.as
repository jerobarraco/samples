package
{
	import org.flixel.*;
	import com.game.PlayState;
	
	[SWF(width = "640" , height = "480" , backgroundColor = "#ffffff")]
	[Frame(factoryClass="Preloader")]
	
	public class Main extends FlxGame
	{		
		public function Main():void
		{
			super(640,480, PlayState,1);
		}		
	}
}