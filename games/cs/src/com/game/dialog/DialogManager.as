package com.game.dialog 
{
	import org.flixel.FlxGroup;
	import org.flixel.FlxSprite;
	import org.flixel.FlxG;
	import org.flixel.FlxText;

	public class DialogManager extends FlxSprite//para el update y alguna otra randomneada por ahi
	{
		public static var DRIVER:int = 0;
		public static var SHOOT:int = 1;
		public static var HOMMING:int = 2;
		public static var SHIELD:int = 3;
		
		//todo Static vars para lso diferentes pjs
		private var characters:Array;//array of characters
		
		private var timer:Number = 4;
		private var prev_diag:Array = null;
		private var dialogs:Dialogs;		
		private var texto : FlxText;
		private var layer :FlxGroup;
		public function DialogManager() 
		{
			super();
			//este orden creo que es el mas probable
			//OJO debe respetar las constantes de arriba
			characters = new Array(
				new CharDriver(),
				new CharShoot(),
				new CharHomming(),
				new CharShield()				
			);
			dialogs = new Dialogs();
			/*esta clase controla todo lo del dialogo,
			 * tiene un array de personajes, y sabe si estan vivos o muertos
			 * cada x tiempo tira un random para ver quien empieza con un comentario, 
			 * llama a Talk de ese personaje, esto debe devolver un mensaje o null si falla
			 * en caso que falla reintenta con otro pj.
			 * Si el mensaje tiene respuesta, luego de un delay llama automáticamente al siguiente personaje con el numero 
			 * de la respuesta.(si esta vivo)
			 *
			 */
			this.layer = new FlxGroup;
			
		}
		public function getLayer():FlxGroup {
			return this.layer;
		}
		function DrawMessage(msg:Message):void {
			var txt:String = characters[msg.pj].name +" says: " + msg.text
			texto = new FlxText(10, 420, 600, txt);
			texto.setFormat("System", 12);
			layer.add(texto);
		}
		override public function update():void{
			super.update();
			timer -= FlxG.elapsed;
			if (timer < 0) {
				layer.remove(texto);
				Talk();
			}
		}
		function Talk():Boolean {
			
			/* 1º ver si hay un dialogo anterior
			 * 2º random entre comentario y evento 
			 * 3º ?
			 * 4º profit
			 */
			if ((prev_diag == null) || (prev_diag.length == 0)) {
				if (Math.random() >0.5) {
					//get diag from event
					prev_diag = dialogs.GetRandomDialog();
				}else {
					prev_diag = dialogs.GetRandomEventDialog();
				}
				
			}
			if (prev_diag != null) {
				var msg:Message = prev_diag.popAt(0);
				if (characters[msg.pj].alive) {
					DrawMessage(msg);
					if (prev_diag.length > 0) {
						timer = 1+(Math.random()*2);
					} else {
						timer = 2 + (Math.random()*4);
					}
					return true;
				}
				else {
					prev_diag = dialogs.GetRandomEventDialog();
				}
			}
			timer = 2;//sino va a medio que tildarse el juego
			return false;
		}
		public function SetAlive(alives:Array):void {
			var c:Character;
			c = characters[SHOOT];
			c.alive = alives[0] || alives[1];
			
			c = characters[HOMMING];
			c.alive = alives[2];
			
			c = characters[SHIELD];
			c.alive = alives[3];
			
			c = characters[DRIVER];
			c.alive = alives[5] || alives[6] || alives[7]  ;//ver los indices en Ship.feats
		}
	}
}