package com.game
{
	import org.flixel.FlxG;
	import org.flixel.FlxPoint;
	import org.flixel.FlxSprite;
	import org.flixel.FlxState;
	
	public class ProgressBar
	{
		private var bar:FlxSprite;
		private var out_frame:FlxSprite;
		private var inside:FlxSprite;
		
		private var color_frame:int = 0xff000000;
		private var color_in:int = 0xffffffff;
		private var color_bar:int = 0xffff0000;
		
		private var frame_thickness:Number =2;
		
		private var position:FlxPoint = new FlxPoint;
		private var size:FlxPoint = new FlxPoint;
		
		private var is_vertical:Boolean;
		
		public var cur_value:Number = 0;
		public var total_value:Number = 100;
		
		public var speed:Number=50;
		
		/**
		 * creates progress bar
		 * 
		 * @param	X				The initial X position.
		 * @param	Y				The initial Y position.
		 * @param	Width			
		 * @param	Height
		 * @param 	Color_frame
		 * @param	Color_in
		 * @param	Color_bar
		 * @param	Frame_thickness
		 * @param	Vertical		If the bar is vertical or horizontal
		 */		
		public function ProgressBar(My_state:FlxState,X:Number,Y:Number,Width:Number, Height:Number,
							Color_frame:int=0xff000000, Color_in:int=0xffffffff,Color_bar:int=0xffff0000,
							Frame_thickness:Number=2, 
							Vertical:Boolean = false):void
		{
			position.set(X,Y);
			size.set(Width,Height);
			
			color_bar = Color_bar;
			color_frame = Color_frame;
			color_in = Color_in;
			
			frame_thickness = Frame_thickness;
			
			is_vertical = Vertical;
			
			out_frame = new FlxSprite(position.x, position.y);
			out_frame.createGraphic(size.x + frame_thickness, size.y + frame_thickness,color_frame);
			out_frame.scrollFactor.x = out_frame.scrollFactor.y = 0;
			My_state.add(out_frame);
			
			inside = new FlxSprite(position.x + frame_thickness/2, position.y + frame_thickness/2);
			inside.createGraphic(size.x, size.y, color_in);
			inside.scrollFactor.x = inside.scrollFactor.y = 0;
			My_state.add(inside);
			
			bar = new FlxSprite(position.x + frame_thickness/2, position.y + frame_thickness/2);
			if(is_vertical)
			{
				bar.createGraphic(size.x, 1, color_bar);
			}
			else
			{
				bar.createGraphic(1, size.y, color_bar);
			}
			bar.scrollFactor.x = bar.scrollFactor.y = 0;
			bar.origin.x = bar.origin.y = 0;
			My_state.add(bar);
			
			update_value(0);
		}
		
		public function update_value(Value:Number):void
		{
			Value = Math.max(Value,0);
			if(is_vertical)
			{
				Value = Math.min(Value,total_value);
				bar.scale.y = Value* size.y/total_value;
			}
			else
			{
				Value = Math.min(Value,total_value);
				bar.scale.x = Value* size.x/total_value;;
			}
			cur_value = Value;
		}
		
		private var prev_value:Number =0;
		
		public function animate_bar(Value:Number):void
		{
			if(Value==cur_value)
				return;
			if(Value>cur_value)
			{
				cur_value += FlxG.elapsed*speed;
				update_value(cur_value);
			}
			if(Value<cur_value)
			{
				cur_value -= FlxG.elapsed*speed;
				update_value(cur_value);
			}
		}
		
		public function move_bar(X:Number,Y:Number):void
		{
			out_frame.x =X;
			out_frame.y =Y;
			
			inside.x = X+1;
			inside.y = Y+1;
			
			bar.x = X+1;
			bar.y = Y+1;
			
		}
		
		public function change_color(Color_frame:int=0xff000000, Color_in:int=0xffffffff,Color_bar:int=0xffff0000):void
		{
			out_frame.color = Color_frame;
			inside.color = Color_in;
			bar.color = Color_bar;
		}
		
		public function set_visible(V:Boolean = true):void
		{
			out_frame.visible = V;
			inside.visible = V;
			bar.visible = V;
		}
	}
}