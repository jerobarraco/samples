package form;

import java.util.ArrayList;
import java.util.List;

public class Propaganda {
	static final String path="/propaganda/";
	public static String getUrlLista(){
		//Devuelve un string con la URL de lista de propaganda.
		return path+"list.jsp";
	}
	public static StringBuilder getLinkLista(String target){
		//Devuelve un StringBuilder con el link para ir a la lista.
		return Item.Link("Listar Propaganda", path+"list.jsp", target, null);
	}
	public static StringBuilder getLinkNew(String target){
		//Devuelve un StringBuilder con el link para crear una nueva propaganda.
		return Item.Link("Nueva Propaganda", path+"edit.jsp", target, "suelto");
	}
	public static StringBuilder getLinkEdit(Long id){
		//Devuelve un StringBuilder con el link para editar los datos de una propaganda.
		return Item.Link(Item.imgEditar, path+"edit.jsp?id="+id.toString(), null, null);
	}
	public static StringBuilder getLinkDelete(Long id){
		//Devuelve un StringBuilder con el link para borrar una propaganda.
		return Item.Link(Item.imgBorrar, path+"del.jsp?id="+id.toString(), null, null);
	}
		public static String getUrlEdit(Long id){
			//Devuelve un String con la URL de editar.
		return path+"edit.jsp?id="+id.toString();
	}
	public static StringBuilder editar(Long id){
		//devuelve el form para editar
		List<StringBuilder> items = new ArrayList();
		model.Propaganda p;
		if (id == null){
			p = new model.Propaganda();
		}else{
			model.manager m = new model.manager();
			p = m.getById(model.Propaganda.class, id);
			items.add(Item.Hidden("id", p.getId().toString()));
		}
		
		items.add(Item.Edit("empresa", "Empresa", p.getEmpresa()));
		items.add(Item.Edit("duracion", "Duracion", Item.DateASTR(p.getDuracion())));
		return Item.Form(items, path+"save.jsp", "Guardar");
	}	
		public static Long save(Long id, String empresa, String sdur){
			//Devuelve el id de la propaganda que se acaba de guardar.
			//Guarda un programa.
		model.Propaganda p;
		model.manager m = new model.manager();
		if (id == null){
			p = new model.Propaganda();
		}else{
			p = m.getById(model.Propaganda.class, id);
		}
		p.setEmpresa(empresa);
		p.setDuracion(Item.STRADate(sdur));
		if(m.save(p))			
			return p.getId();
		else
			return null;
	}
	public static StringBuilder getLista(){
		//Genera la tabla con la lista de propagandas
		StringBuilder s = new StringBuilder();
		List<StringBuilder> rows = new ArrayList();
		
		List<StringBuilder> heads = new ArrayList();
		heads.add(Item.TableHeader("Id"));
		heads.add(Item.TableHeader("Empresa"));	
		heads.add(Item.TableHeader("Duración"));
		heads.add(Item.TableHeader("Editar"));
		heads.add(Item.TableHeader("Eliminar"));
		
		rows.add(Item.TableRow(heads));
		
		for (model.Propaganda p: model.Propaganda.getAll()){
			List<StringBuilder> cell = new ArrayList();
			cell.add(Item.TableCell(p.getId().toString()));
			cell.add(Item.TableCell(p.getEmpresa()));
			
			cell.add(Item.TableCell(Item.DateASTR(p.getDuracion())));
			cell.add(Item.TableCell(getLinkEdit(p.getId()).toString()));
			cell.add(Item.TableCell(getLinkDelete(p.getId()).toString()));
			rows.add(Item.TableRow(cell));
		}
		s.append(Item.Table(rows));
		return s;
	}
	public static boolean delete(Long id){
		//Borra propagandas.
		if (id==null) return false;
		model.manager m = new model.manager();
		model.Propaganda p = m.getById(model.Propaganda.class, id);
		if (p==null) return false;
		return m.remove(p);	
	}
}
