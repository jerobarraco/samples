package form;

import java.util.ArrayList;
import java.util.List;

public class Tema{
	static final String path="/tema/";//carpeta donde estan los archivos jsp para esta clase
	public static String getUrlLista(){
		//Devuelve un String con la URL de la lista.
		return path+"list.jsp";
	}
	public static StringBuilder getLinkLista(String target){
		//Devuelve un StringBuilder con el link que te lleva a la lista.
		return Item.Link("Listar Temas", path+"list.jsp", target, null);
	}
	public static StringBuilder getLinkNew(String target){
		//Devuelve un StringBuilder con el link que te lleva al jsp para generar un nuevo tema.
		return Item.Link("Nuevo Tema", path+"edit.jsp", target, "suelto");
	}
	public static StringBuilder getLinkEdit(Long id){
		//Devuelve un StringBuilder con el link para editar un tema.
		return Item.Link(Item.imgEditar, path+"edit.jsp?id="+id.toString(), null, null);
	}
	public static StringBuilder getLinkDelete(Long id){
		//Devuelve un StringBuilder con el link para borrar un tema.
		return Item.Link(Item.imgBorrar, path+"del.jsp?id="+id.toString(), null, null);
	}
	public static String getUrlEdit(Long id){
		//Devuelve un String con la url para editar un tema.
		return path+"edit.jsp?id="+id.toString();
	}
	public static StringBuilder editar(Long id){		
		//devuelve el form para editar
		List<StringBuilder> items = new ArrayList();
		model.Tema t;
		if (id == null){
			t = new model.Tema();
		}else{
			model.manager m = new model.manager();
			t = m.getById(model.Tema.class, id);
			items.add(Item.Hidden("id", t.getId().toString()));
		}
		
		items.add(Item.Edit("titulo", "Titulo", t.getTitulo()));
		items.add(Item.Edit("autor", "Autor", t.getAutor()));
		items.add(Item.Edit("duracion", "Duracion", Item.DateASTR(t.getDuracion())));
		return Item.Form(items, path+"save.jsp", "Guardar");
	}
	
	public static Long save(Long id, String titulo, String autor, String sdur){
		//Devuelve el id del tema que acaba de editar.
		//Guarda un tema.
		model.Tema t;
		model.manager m = new model.manager();
		if (id == null){
			t = new model.Tema();
		}else{
			t = m.getById(model.Tema.class, id);
		}
		t.setAutor(autor);
		t.setTitulo(titulo);
		t.setDuracion(Item.STRADate(sdur));
		if(m.save(t))			
		return t.getId();
		else
			return null;
	}
	
	public static StringBuilder getLista(){
		//Construye la talba con los temas.
		StringBuilder s = new StringBuilder();
		List<StringBuilder> rows = new ArrayList();
		
		List<StringBuilder> heads = new ArrayList();
		heads.add(Item.TableHeader("Id"));
		heads.add(Item.TableHeader("Autor"));
		heads.add(Item.TableHeader("Titulo"));
		heads.add(Item.TableHeader("Duración"));
		heads.add(Item.TableHeader("Editar"));
		heads.add(Item.TableHeader("Eliminar"));
		
		rows.add(Item.TableRow(heads));
		
		for (model.Tema t: model.Tema.getAll()){
			List<StringBuilder> cell = new ArrayList();
			cell.add(Item.TableCell(t.getId().toString()));
			cell.add(Item.TableCell(t.getAutor()));
			cell.add(Item.TableCell(t.getTitulo()));
			
			cell.add(Item.TableCell(Item.DateASTR(t.getDuracion())));
			cell.add(Item.TableCell(getLinkEdit(t.getId()).toString()));
			cell.add(Item.TableCell(getLinkDelete(t.getId()).toString()));
			rows.add(Item.TableRow(cell));
		}
		s.append(Item.Table(rows));
		return s;
	}
	public static boolean delete(Long id){
		//Borra un tema.
		if (id==null) return false;
		model.manager m = new model.manager();
		model.Tema t = m.getById(model.Tema.class, id);
		if (t==null) return false;
		return m.remove(t);	
	}
}
