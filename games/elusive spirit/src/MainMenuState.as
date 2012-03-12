package  
{
	import org.flixel.*;
	
	
	
	/**
	 * ...
	 * @author Puccini
	 */
	public class MainMenuState extends FlxState
	{
		
		[Embed(source = '/Data/Sprites/main.png')] private var titlePagePNG:Class;
		
		private var title:FlxSprite;
		private var text:FlxText;
		
		public function MainMenuState() 
		{
			title = new FlxSprite(0, 0, titlePagePNG);
			text = new FlxText(0, 350, 600, " - PRESS Z TO START - ");
			text.alignment = "center";
			text.size = 20;
			text.color = 0x999999;
			text.shadow = 0xff000000;
			
			
			add(title);
			add(text);
			
		}
		
		override public function update():void
		{
			super.update();
			
			if (FlxG.keys.Z)
			{
				changeState();
				//FlxG.fade(0xff000000, 2, changeState);
			}
		}
		
		private function changeState():void
		{
			FlxG.switchState(new PlayState);
		}
		
	}

}