package com.game.dialog 
{
	/**
	 * ...
	 * @author 
	 */
	import org.flixel.FlxG;
	public class Dialogs 
	{
		//array de Messages random, recomiendo que solo estos sean mensajes que inicien otros mensajes
		//Oka, sonara complicado, msgs es un array , cada elemento es un set de mensajes que define un dialogo
		//Un dialogo entre varios personajes, o un solo mensaje de un solo personaje.
		//Estos mensajes son random y se van eliminando a medida que se dicen
		public var msgs:Array;
		//array para poner cosas segun elementos del ambiente
		public var evt:Array;
		
		public function Dialogs() 
		{
			msgs = new Array;
			evt= new Array;
			
			var diag :Array;//ejemplo de un dialogo
			
			var pj:int ;//cache
			
			//---------------- COMENTARIOS RANDOM DE DRIVER
			pj = DialogManager.DRIVER;
			diag = new Array( new Message( "Gimme fuel, gimme fire, gimme that wich i desire!!", pj ) );//un comentario al aire
			msgs.push(diag);
			//otra forma
			msgs.push(new Array( new Message( "C'm on! FASTER!", pj )));
			msgs.push(new Array( new Message( "Better run than dead", pj )));
			
			
			//---------------- COMENTARIOS RANDOM DE SHIELD
			pj = DialogManager.SHIELD;
			
			//---------------- COMENTARIOS RANDOM DE HOMMING
			
			//---------------- COMENTARIOS RANDOM DE SHOOT
			
			//---------------- DIALOGOS LAROGS.... 
			diag = new Array(
				new Message("Why are you killing them?", DialogManager.SHIELD),
				new Message("What are you talking about?!. Are you one of them?!", DialogManager.HOMMING),
				new Message("Don't blaim her! Don't you see she's protecting us?", DialogManager.DRIVER), 
				new Message("This is none of your bussiness, you wuss", DialogManager.HOMMING)
			);
			msgs.push(diag);
			
			//otro dialogo
			diag = new Array(
				new Message("Do a barril roll!!", DialogManager.SHIELD),
				new Message("D:! ?", DialogManager.DRIVER)				
			);
			msgs.push(diag);
			
			evt.push(
				//Diag 0
				//este deberia aparecer cuando queda poca energia.. pero una cantidad de veces contada, no siempre
				new Array(new Message("Be careful!", DialogManager.SHIELD))
				);
		}
		public function GetRandomDialog():Array {
			if (msgs.length < 1) { return null };
			
			var rand:int = Math.random() * Number(msgs.length);
			return msgs.popAt(rand);			
		}
		public function GetRandomEventDialog():Array {
			//ojo esto puede explotar
			//Esto es re ineficiente, mejor pienso otra forma de hacerlo
			return null;
		}
		//otras ideas, tener arrays para eventos especiales, como cambios de pantalla, muertes, ataques, jefes, 
		//tiempo, etc
	}

}