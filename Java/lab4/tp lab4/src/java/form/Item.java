package form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
//Invento total de Nande (es una lástima que yo, Lelale no se me haya ocurrido... encima apareció con la novedad sin que haya podido tocar nada :-( )
public class Item {
	
	//Usamos StringBuilder por la función append, si bien hay otras formas de agregar al final 
	//en la cadena de caracteres, pareció que era mas prolijo hacerlo así.
	//Todos los métodos devuelven un StringBuilder y funcionan estáticamente.
	
	//Genera un "enter" en la escritura html.
	public static StringBuilder br = new StringBuilder("<br />");
	//Un detalle leeeeeendo: Pusimos las imagenes de "nuevo", "borrar", "editar", "listar" y "ver".
	public static String imgAgregar = Item.Img("/img/nuevo.gif").toString();
	public static String imgBorrar = Item.Img("/img/borrar.gif").toString();
	public static String imgEditar = Item.Img("/img/editar.gif").toString();
	public static String imgListar = Item.Img("/img/listar.gif").toString();
	public static String imgVer = Item.Img("/img/ver.gif").toString();
	
	public static StringBuilder Link(String text, String url, String target, String Class){ 
		//Genera el html para hacer un link html como cadena de caracteres donde 
		//adosar el url, el target, la clase y el texto para el link.
		StringBuilder s = new StringBuilder();
		s.append("<a href=\"");
		s.append(url);
		if (target!=null){
			s.append("\" target=\"");
			s.append(target);
			
		}
		if (Class!=null){
			s.append("\" class=\"");
			s.append(Class);
		}
		s.append("\">");
		s.append(text);
		s.append("</a>");
		return s;
	}
	
	public static StringBuilder Table(List<StringBuilder> rows){
		//Genera los tags para hacer las tablas y en el medio incerta las tuplas y columnas con rows.
		StringBuilder s = new StringBuilder("<table>");
		for (StringBuilder ns : rows){
			s.append(ns);
		}
		s.append("</table>");
		return s;
	}
	public static StringBuilder TableRow(List<StringBuilder> cells) {
		//Genera los tags de las filas e incerta en el medio las celdas.
		StringBuilder s = new StringBuilder("<tr>");
		for (StringBuilder cell : cells){
			s.append(cell);
		}
		s.append("</tr>");
		return s;
	}
	public static StringBuilder TableCell(String content) {
		//Genera el tag de las celdas.
		StringBuilder s = new StringBuilder("<td>");
		s.append(content);
		s.append("</td>");
		return s;
	}
	public static StringBuilder TableHeader(String content) {
		//Genera los tags de la cabecera de una tabla
		StringBuilder s = new StringBuilder("<th>");
		s.append(content);
		s.append("</th>");
		return s;
	}
	public static StringBuilder Form(List<StringBuilder> items, String action, String submit){ //podria usar parametros opcionales pero son feos en java
		return Form(items, action, submit, false);
	}
	public static StringBuilder Form(List<StringBuilder> items, String action, String submit, boolean multipart ){
		//Genera los tags y los datos para los forms (action, submit)		
		StringBuilder s = new StringBuilder("<FORM ");
		if (multipart)//ojo que los datos de los forms multiparts se deben manejar de otra forma!
			s.append("ENCTYPE='multipart/form-data' ");
		s.append("action=\"");
		s.append(action);
		s.append("\" method=\"POST\">");
		
		for (StringBuilder i : items){
			s.append(i);
		}
		
		if (submit != null){
			if(submit.isEmpty())
				submit = "Enviar";
			s.append("<input type=\"submit\" value=\"");
			s.append(submit);
			s.append("\">");
		}
		s.append("</form>");
		return s;
	}
	public static StringBuilder Input(String type, String name, String label, String value){
		StringBuilder s = new StringBuilder(label);
		//Determina los tags para generar un input... con el tipo de input que debe (String type), nombre, 
		//label (te pone a la izquierda un textito) y el valor que devuelve y en el caso del botón, el texto.
		if(!label.isEmpty()){
			s.append(" : ");
		}
		s.append("<INPUT type=\"");
		s.append(type);
		s.append("\" name=\"");
		s.append(name);
		s.append("\" value=\"");
		if (value != null){
			s.append(value);
		}
		s.append("\">");
		if (!type.equals("HIDDEN"))
			s.append("<br />");
		return s;
	}
	/*<!ENTITY % InputType
  "(TEXT | PASSWORD | CHECKBOX |
    RADIO | SUBMIT | RESET |
    FILE | HIDDEN | IMAGE | BUTTON)"
   >*/
	public static StringBuilder Edit(String name, String label, String value){
		//Hace el input para generar una edición de textbox.
		return Input("TEXT", name, label, value);
	}
	public static StringBuilder Hidden(String name, String value){
		//Hace el input para generar un Hidden (para mandar datos de un html a otro).
		return Input("HIDDEN", name, "", value);
	}
	public static StringBuilder Button(String name, String value){
		//un asco que el input button use el mismo valor para lo que muestra y el value que devuelve
		//(Queja de Nande contra html jaja)
		return Input("SUBMIT", name, "", value);
	}
	static StringBuilder Reset(String name) {
		//Genera un botón que teóricamente pone el formulario a 0... pero no funcionó muy bien.
		return Input("RESET", name, "", name);
	}
	static StringBuilder File(String name, String label) {
		
		return Input("FILE", name, label, name);
	}
	public static StringBuilder Select(String name, List<StringBuilder> options, String label){
		//Genera un combobox... y agrega sus tuplas.
		StringBuilder s = new StringBuilder(label);
		if (label != null){
			s.append(" : ");
		}
		s.append("<SELECT name=\"");
		s.append(name);
		s.append("\">");
		for(StringBuilder st: options){
			s.append(st);
		}
		s.append("</SELECT><br />");
		return s;
	}
	
	public static StringBuilder SelectItem(String value, String label, boolean selected){
		//Agrega las tuplas al select (combobox de html).
		StringBuilder s = new StringBuilder("<OPTION ");
		if (selected ) s.append("SELECTED ");
		s.append("value=\"");
		s.append(value);
		s.append("\">");
		s.append(label);
		s.append("</OPTION>");
		return s;
	}
	//Los siguientes dos métodos los generamos acá y los usamos en el paquete model. 
	//Si bien debería haber una clase en model que haga esto, la idea de ponerlo acá
	//es centralizar las transformaciones de datos a String en una sola clase.
	public static Date STRADate(String duracion){
		//Esto genera que transforme un String a un tipo de dato Date.
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		try {
			return sdf.parse(duracion);
		} catch (ParseException ex) {
			Logger.getLogger(Item.class.getName()).log(Level.SEVERE, null, ex);
			return new Date(0L);
		}		
	}
	public static String DateASTR(Date duracion){
		//Esto genera que un tipo de dato Date se convierta a String.
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		if(duracion == null){
			duracion = new Date(0L);
		}
		return sdf.format(duracion);
	}

	static StringBuilder Img(String path) {
		//Esto crea el tag de agregar imagen.
		StringBuilder s = new StringBuilder ("<img src=\"");
		s.append(path);
		s.append("\"/>");
		return s;
	}
}
