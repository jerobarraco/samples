package com.game 
{
	import flash.display.Loader;
	import flash.display.Sprite;
	import flash.utils.ByteArray;
	import flash.display.MovieClip;
	import flash.events.Event;
	import flash.media.SoundTransform;
	
	import org.flixel.FlxG;
	
	public class Video extends Sprite
	{
		//movie clip
		[Embed(source = '/data/Videos/videos.swf', mimeType = "application/octet-stream")] private var VidSwf:Class;
		
		private static var VIDEO_POSX:int = 10;
		private static var VIDEO_POSY:int = 10;
		
		public var loader:Loader;
		public var load:ByteArray;
		public var video:MovieClip;
		public var loaded:Boolean;
		
		//para el zoom
		public var Escala:Number;
		public var zoom:Number;
		
		public function Video() 
		{
			load = new VidSwf();
			loader = new Loader();
			loader.contentLoaderInfo.addEventListener(Event.COMPLETE, loadCompleteListener, false, 0, false);
			loader.loadBytes(load);
			
			addEventListener(Event.ENTER_FRAME, onEnterFrame);
			
		}
		
		public function onEnterFrame(e:Event):void
		{
			if (loaded)
			{
				if (FlxG.pause == true)
				{
					video.stop();
				}
			}
		}
		
		
		private function loadCompleteListener(e:Event):void
		{
			video = MovieClip(loader.content);
			FlxG.stage.addChild(video);
			video.x = 50 //FlxG.width - ((VIDEO_POSX * Escala) / 2);
			video.y = 50 //FlxG.height * 2 - ((VIDEO_POSY * Escala) );
			
			video.scaleX = Escala;
			video.scaleY = Escala;
//			video.soundTransform = new SoundTransform(0, 0);
//			video.gotoAndStop(0);
			
			video.alpha = 0.5;
			
			loaded = true;
		}
		
		public function TransitionZoom(NewZoom:Number):void
		{
			if (zoom < NewZoom)
				zoom += 0.75 * FlxG.elapsed;
			else if (zoom > NewZoom)
				zoom -= 0.75 * FlxG.elapsed;
			else zoom = NewZoom;
			
			ZoomVideo(zoom);
		}
		
		public function ZoomVideo(NewZoom:Number):void
		{
			Escala = 1;
			
			video.x = FlxG.width - ((/*(video.width)*/ VIDEO_POSX * NewZoom) / 2);
			video.y = FlxG.height*2 - ((/*(video.height)*/VIDEO_POSY * NewZoom) );
			
			video.scaleX = Escala;
			video.scaleY = Escala;
			
			Escala = NewZoom;
			
			video.x = FlxG.width - ((/*(video.width)*/ VIDEO_POSX * NewZoom) / 2);
			video.y = FlxG.height*2 - ((/*(video.height)*/ VIDEO_POSY * NewZoom) );
			
			video.scaleX = Escala;
			video.scaleY = Escala;
		}
		
	}

}