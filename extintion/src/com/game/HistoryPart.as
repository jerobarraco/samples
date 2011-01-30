package com.game
{
	import org.flixel.*;

	public class HistoryPart extends FlxState
	{
		private var img:FlxSprite;
		
		[Embed(source = "/data/Historia/Data/Screens/00.png")] private var Img1:Class;
		[Embed(source = "/data/Historia/Data/Screens/01.png")] private var Img1:Class;
		[Embed(source = "/data/Historia/Data/Screens/02.png")] private var Img1:Class;
		[Embed(source = "/data/Historia/Data/Screens/03.png")] private var Img1:Class;
		[Embed(source = "/data/Historia/Data/Screens/04.png")] private var Img1:Class;
		[Embed(source = "/data/Historia/Data/Screens/05.png")] private var Img1:Class;
		[Embed(source = "/data/Historia/Data/Screens/06.png")] private var Img1:Class;
		
		private var Imgs:Array = [Img1, Img2, Img3, Img4, Img5];
		private static var showed:Array = [false, false, false, false, false];
		
		private var promptframes:int;
		private var prompt:FlxText;
		public var features:Array;
		override public function create():void
		{
			var avail:Array = [];
			
			for (var i:int = 0; i < showed.length; i++) {
				if (!showed[i]) {
					avail.push(i);
				}
			}
			if (avail.length == 0) {
				for (var j:int = 0; j < showed.length; j++) {
					showed[j] = false;
				}
				FlxG.state = new HistoryEnd;
				return;				
			}
			var r: int = Math.random() * avail.length;
			var usar:int = avail[r];
			showed[usar] = true;
			var sprite:FlxSprite = new FlxSprite(x, y, Imgs[usar]);
			this.add(sprite);
			
			/*
			var thismesg:String = Messages[usar];
			
			var mitexto:FlxText = new FlxText(10, 10, 600, thismesg);
			mitexto.setFormat("System", 12);
			this.add(mitexto);
			
			prompt = new FlxText(25, 400, 30, "_ ");
			prompt.setFormat(null, 16);
			this.add(prompt);
			promptframes = 20;*/			
			///Creando imagenes
		}
		override public function update():void {
			/*
			promptframes -= 1;
			if (promptframes < 0) {
				promptframes = 20;
				prompt.visible = !prompt.visible;
			}*/

			if (FlxG.keys.justPressed("Z") ) {
				FlxG.level += 1;
				if (FlxG.level > 6) FlxG.level = 6;
				var state:PlayState = new PlayState;
					
				FlxG.state = state;
				state.set_feats(features);
			}
		}
		private static var Messages:Array=[
			"Captain's Log: No Humans Era, Day One – Time: Unknown.\n"+
			"“It seems we are the only ship could scape from annihilation of our race.\n"+
			"The last 5 months has been full of dispair... To think that we were looking up to the sky, and them sorpresed us fr��������/[CORRUPTED FILE]"
			,
			"“Bitácora del Capitán: Día 8 – Hora: sin importancia\n"+
			"Pasaron 6 días desde que empezaron los problemas, no tenemos acceso a 3 compartimentos de la nave y hay 2 técnicos desaparecidos. Creemos que la causa de todo es un...”"
			,
			"“Archivos Históricos: El 8 de Octubre del año 3129, a lo largo y a lo ancho del planeta Tierra se reportaron avistamientos de naves de origen no humano que emergieron de los océanos y comenzaron a atacar las principales urbes.\n"+
			"Estos invasores fueron llamados...ARCHIVO CORRUPTO”"
			,
			"“... no sabemos cómo combatir a los FoVI (Formas de Vida Intraterrestre), sus naves son más fuertes y resistentes.\n"+
			"Tenemos que admitir que la raza humana no posee la capacidad para enfrentarse a esta amenaza, nuestra opción es escapar, la única salida es hacia el espacio exterior.”"
			,
			"“Archivo Clasificado #1847:\n"+
			"		Buen trabajo soldado, gracias a su sacrificio la extinción de los invasores humanos será completa.\n"+
			"		De ahora en adeante la prosperidad de sus descendientes estará asegurada. Todo el pueblo Zhovorgliano le agradece y lo llevara eternamente en su memoria, al fin Zhovorgl vuelve a estar bajo nuestro control.\n"+
			"		Atte,\n"+
			"			Coronel Bizak”"
			,
			"“Nota a mí mismo:\n"+
			"	Maldita atmósfera! No sé cómo los humanos lo soportan. Estoy a punto de tomar las píldoras de adaptación, el efecto secundario puede ser amnesia temporal. En ese caso tu lista de prioridades es:\n"+
			"	–	Eliminar los humanos a bordo\n"+
			"	–	Infectar la Computadora Principal\n"+
			"	–	Asegurarte que la nave y todo su contenido sea destruido\n"+
			"	–	Tratar de escapar con la nave!!!”"
			,
			
			
			//fakes
			"“Ultimo momento: Fotos exclusivas de Britney y un FoVI!!!”"
			,
			"“Diario Intimo de Jenny: Mark es un sueño, estoy tan contenta de tenerlo en la nave...”"
			,
			"“Bitácora del Vice-Capitán:\n"+
			"		    Maldito Murray! Ya convencí a Andy y Mark, los técnicos, apenas vuelvan tomaremos control de la nave y nos libraremos del yugo de ese dictador.”\n"
			,
			"“Notas de Bob:\n"+
			"        Hay algo muy malo con esta nave y el capitán está demasiado ocupado peleando con el vice como para notarlo!!!”"
			,
			"“Noticiario Internacional: Primicia! Britney nuevamente embarazada! Esto podría ser el resultado de su 'encuentro del tercer tipo' que supuestamente tuvo el mes pasado con los FoVI.”"
			,
			"“Hace 5 días que no veo a Mark, desde que fue a arreglar el desperfecto del depósito. Por suerte Bob está conmigo, es un buen amigo y me da la seguridad que necesito.”"
			,
			"“Para: Vice-Capitán\n"+
			"	De: Andy\n"+
			"	Asunto: Computadora Principal\n"+
			"\n"+
	        "Tal y como usted dijo hay un desperfecto en la Computadora Principal, parece un virus pero no es nada que haya visto antes. Seguiremos investigando con Mark. Cuando terminemos tendremos más argumentos para tomar el control de la nave.”\n"
			,
			"“Notas de Bob:\n"+
			"        Este virus es más duro de lo que parece, hace 24 horas que estoy peleando y todavía no encontré ninguna información de ayuda. Por suerte en un par de horas me encuentro con Jenny, debería bañarme!”\n"			
		]
	}

}
