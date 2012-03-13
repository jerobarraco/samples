package com.game.dialog 
{
	//no tengo idea donde poner esto
	Array.prototype.popAt = function(index:int):Object {
		/*var sp:Array = this.splice(index, 1);
		return sp[0];*/
		return this.splice(index, 1)[0];
	}
	Array.prototype.removeAt = function (index:int):void {
		this.splice(index, 1);
	}
	Array.prototype.remove = function(rem:Object) {
	   // Simple prototype to 
	   // remove an element from
	   // a normal or multidimension Array
	   for(var i:int=0; i<this.length; i++) {
		  if(this[i]==rem) {
			this.splice(i, 1);
		  }
		  else if(this[i].length > 0) {
			this[i].remove(rem);
		  }
		}
		return this;
	}
	
	public class Character 
	{
		public var name:String;
		public var faces:Array;
		//character is alive
		public var alive:Boolean;
		public function Character() 
		{
			alive = true;
		}
		
	}

}