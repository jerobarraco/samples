package com.game
{
	
	import org.flixel.FlxGroup;
	import org.flixel.FlxObject;
	
	public class Recycler extends FlxGroup {

		private var myclass:Class;
		
		public function Recycler(type:Class):void 
		{
			myclass = type;
			super();
		}
		
		public function getInstance():FlxObject 
		{
			var object:FlxObject = this.getFirstAvail();
			if (object == null)
			{	
				object = new myclass();
				this.add(object);
			}
			return object;
		}
		
		/**
		 * Call this function to find get an array with all the members of the group that are living
		 */
		public function getLiving():Array
		{
			var count:int = 0;
			var living:Array = new Array();
			var i:uint = 0;
			var o:FlxObject;
			var ml:uint = members.length;
			for (i = 0; i < ml; i++) {
				o = members[i] as FlxObject;
				if (o.exists && !o.dead) {
					living.push(o);
				}
			}
			return living;
			/*while(i < ml)
			{
				o = members[i++] as FlxObject;
				if(o != null)
				{
					if(o.exists && !o.dead)
					{
						living[count] = o;
						count++;
						//living.push(o);
					}
				}
			}
			return living;*/
		}
	}
}