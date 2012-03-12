package form;

import java.util.ArrayList;
import java.util.List;

public class Bloque {
	static final String path="/bloque/";//carpeta donde estan los archivos jsp para esta clase
	public static String getURLLista(Long idPrograma){
		//Devuelve la URL armada para la lista de bloques.
		return path + "list.jsp?idPrograma=" + idPrograma.toString();
	}
	
	public static String getUrlEdit(Long id, Long idPrograma){
		//Esto devuelve el URL del jsp para editar (con el id del objeto que necesitás editar)
		return path+"edit.jsp?id="+id.toString() + "&idPrograma="+idPrograma.toString();
	}
	
	@Deprecated
	public static StringBuilder getLinkLista(Long idPrograma){
		//Devuelve el String con el link (con los tags y todo) de la lista para bloques.
		return Item.Link("Listar Bloques", getURLLista(idPrograma), null, null );
	}
	@Deprecated
	public static StringBuilder getLinkNew(Long idPrograma){
		//Devuelve el String con el link para ir al jsp para hacer un bloque nuevo.
		return Item.Link("Nuevo Bloque", path+"edit.jsp?idPrograma="+idPrograma.toString(), null, "suelto");
	}
	@Deprecated
	public static StringBuilder getLinkEdit(Long id, Long idPrograma){
		//Devuelve el String con el link para ir al jsp de editar.
		return Item.Link(Item.imgEditar, path+"edit.jsp?id="+id.toString() + "&idPrograma="+idPrograma.toString(),null, null);
	}
	@Deprecated
	public static StringBuilder getLinkDelete(Long id, Long idPrograma){
		//Este método devuelve el String con todos los tags y el texto para generar el link que nos lleva a borrar una tupla de la tabla del listado.
		return Item.Link(Item.imgBorrar, path+"del.jsp?id="+id.toString()+"&idPrograma="+idPrograma.toString(),null, null);
	}
	public static StringBuilder getLinkDeleteEl(Long id, Long idPrograma, Long idEl){
		//Construye el String para lograr borrar la tupla u objeto de bloque.
		StringBuilder s = new StringBuilder( path);
		s.append("remElement.jsp?id=");
		s.append(id);
		s.append("&idPrograma=");
		s.append(idPrograma);
		s.append("&idElement=");
		s.append(idEl);
		//hasta acá el url... uff...
		
		s = Item.Link(Item.imgBorrar, s.toString(), null, null );
		return s;
	}
		
	public static StringBuilder getLista(Long idPrograma){
		//Genera la tabla completa con todos los bloques que pertenezcan a un programa (o no). 
		StringBuilder s = new StringBuilder();
		//En esta parte verifica si existe una id, si no existe, se piden todos los bloques.
		if (idPrograma == null) return s;
		model.manager m = new model.manager();
		
		model.Programa prog = m.getById(model.Programa.class, idPrograma);
		//Se busca todos los objetos de programa (que va a ser uno, donde el ID coincida)
		if (prog == null) return s;
		//Si no encontró nada devuelve nada.
		s.append("Programa id:");
		s.append(prog.getId().toString());
		s.append("<br />");
		
		List<StringBuilder> rows = new ArrayList();
		//Se arma la cabecera de la tabla.
		List<StringBuilder> heads = new ArrayList();
		heads.add(Item.TableHeader("Id"));
		heads.add(Item.TableHeader("Duración"));
		heads.add(Item.TableHeader("Editar"));
		heads.add(Item.TableHeader("Eliminar"));
		
		rows.add(Item.TableRow(heads));
		//Se arman las tuplas de la tabla.
		for (model.Bloque p: prog.getBloques()){
			List<StringBuilder> cells = new ArrayList();
			cells.add(Item.TableCell(p.getId().toString()));
			cells.add(Item.TableCell(Item.DateASTR(p.getDuracion())));
			cells.add(Item.TableCell(getLinkEdit(p.getId(), idPrograma).toString() ));
			cells.add(Item.TableCell(getLinkDelete(p.getId(), idPrograma).toString()));
			rows.add(Item.TableRow(cells));
		}
		//Se generan los tags de tabla
		s.append(Item.Table(rows));
		//Se devuelve la tabla completa, "embalada y lista" para usar.
		return s;
	}
	
	public static StringBuilder editar(Long id, Long idPrograma){
		//devuelve el form para editar
		
		//Inicializacion base previa
		StringBuilder cuerpo= new StringBuilder(); //Todo el cuerpo, contiene el form para agregar y la lista
		
		model.manager m = new model.manager();
		model.Bloque p;
		if(idPrograma == null) return cuerpo;
		model.Programa prog = m.getById(model.Programa.class, idPrograma);
		if (prog == null){
			cuerpo.append("Programa inválido");
			return cuerpo;
		}
		if (id == null){
			p = new model.Bloque();
			p.setPrograma(prog);
			m.save(p);
			id = p.getId();//Lo guardamos en id para usar mas tarde id
		}else{
			p = m.getById(model.Bloque.class, id);
		}
		//Fin inicializacion		
		
		//Elementos del form para agregar temas/propagandas
		List<StringBuilder> formItems = new ArrayList();
		
		//Elementos de la lista de elementos
		List<StringBuilder> rows = new ArrayList();
		//cabeceras
		List<StringBuilder> heads = new ArrayList();
		
		heads.add(Item.TableHeader("Elemento"));
		heads.add(Item.TableHeader("Quitar"));
		rows.add(Item.TableRow(heads));
		
		//elementos
		for (model.Elemento e : p.getElementos()){
			List<StringBuilder> cells = new ArrayList();
			cells.add(Item.TableCell(e.toString()));
			cells.add(Item.TableCell(getLinkDeleteEl(id, idPrograma, e.getId()).toString()));
			rows.add(Item.TableRow(cells));
		}
		//agregamos la lista de elementos al cuerpo
		cuerpo.append(Item.Table(rows));
		cuerpo.append("<br />");
		
		//combo de temas
		List<StringBuilder> temas = new ArrayList();
		for (model.Tema t : model.Tema.getAll()){
			temas.add(Item.SelectItem(t.getId().toString(), t.toString(), false));
		}
		
		//combo de propagandas
		List<StringBuilder> propagandas = new ArrayList();
		for (model.Propaganda prop : model.Propaganda.getAll()){
			propagandas.add(Item.SelectItem(prop.getId().toString(), prop.toString(), false));
		}
		
		
		formItems.add(Item.Hidden("id", p.getId().toString()));
		formItems.add(Item.Hidden("idPrograma", idPrograma.toString()));
		
		formItems.add(Item.Select("idTema", temas, "Temas"));
		formItems.add(Item.Button("agregar", "Agregar Tema"));
		
		formItems.add(new StringBuilder("<br />"));
		
		formItems.add(Item.Select("idPropaganda", propagandas, "Propaganda"));
		formItems.add(Item.Button("agregar", "Agregar Propaganda"));
		//Agregamos un form con todo esto
		cuerpo.append(Item.Form(formItems, path+"addElement.jsp", null));
		return cuerpo;
	}
	
	private static boolean addEl(Long id, model.manager m , model.Elemento e){
		//Agrega un elemento nuevo al sistema (puede ser propaganda o tema).
		model.Bloque p = m.getById(model.Bloque.class, id);
		if (p==null) return false;
		
		p.getElementos().add(e);
		return m.save(p);
	}
	
	public static boolean addTema(Long id, Long idTema){
		//Agrega un tema a un bloque y ejecuta el método addEl para agregarlo finalmente.
		model.manager m = new model.manager();
		model.Tema t = m.getById(model.Tema.class, idTema);
		if (t == null) return false;
		
		return addEl(id, m, t);
	}
	
	public static boolean addPropaganda(Long id, Long idPropaganda){
		//Agrega una propaganda a un bloque y ejecuta el método addEl para agregarlo finalmente.
		model.manager m = new model.manager();
		model.Propaganda prog = m.getById(model.Propaganda.class, idPropaganda);
		if (prog == null) return false;
	
		return addEl(id, m, prog);
	}
	public static boolean remElement(Long id, Long idElement){
		//Elimina o remueve un elemento dentro de un bloque.
		model.manager m = new model.manager();
		model.Bloque p = m.getById(model.Bloque.class, id);
		if (p==null) return false;
		
		for (model.Elemento e : p.getElementos()){
			if (e.getId().equals(idElement)){
				p.getElementos().remove(e);
				//lo quitamos de la lista de elementos del periodo, pero no lo eliminamos de la base de datos
				//porque otro periodo puede usar lo mismo.
				//Escrito dentro de un diagrama de clases esto figuraría como una relación 
				//de agregación, los elementos Tema y Propaganda no componen un bloque, 
				//existen por fuera de él.
				return m.save(p);
			}
		}
		return false;
	}
	public static boolean delete(Long id){
		//Elimina un bloque.
		if (id==null) return false;
		model.manager m = new model.manager();
		model.Bloque p = m.getById(model.Bloque.class, id);
		if (p==null) return false;
		return m.remove(p);	
	}
}
