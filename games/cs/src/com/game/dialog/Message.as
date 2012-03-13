package com.game.dialog 
{
	/**
	 * ...
	 * @author 
	 */
	public class Message 
	{
		public var text:String; //texto
		//primer paso, implementar hasta text
		
		public var pj:int; 
		//numero de pj que dice el dialogo (pej : DialogManager.DRIVER)
	
		public var emotion:int; //emocion //para el avatar
		//imagenes de emociones, (artist needed) (quizas le pida a yamila)
		
		public function Message(text:String, pj:int=-1, emotion:int=0) {
			this.text = text;
			this.pj = pj;
			this.emotion = emotion;
		}
		
	}

}