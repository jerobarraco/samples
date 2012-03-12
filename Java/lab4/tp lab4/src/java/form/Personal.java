package form;

import java.util.ArrayList;
import java.util.List;

public class Personal {
	static final String path="/personal/";
	
	public static String getUrlLista(){
		//Devuelve el String con la URL del jsp donde se ejecuta la lista de personal.
		return path+"list.jsp";
	}
	public static String getUrlEdit(Long id){
		//Devuelve el String con la URL del jsp para editar un personal.
		return path+"edit.jsp?id="+id.toString();
	}
	public static String getUrlPhoto(Long id){
		//Devuelve el String con la URL del jsp que da una la foto para un personal
		return path+"foto.jsp?id="+id.toString();
	}
	//El Deprecated es para que te lo tache al método para que le digas al programador "Esto no se debería usar".
	@Deprecated
	public static StringBuilder getLinkLista(String target){
		//Construye el StringBuilder del link para la lista.
		return Item.Link("Listar Personal", path+"list.jsp", target, null);
	}
	@Deprecated
	public static StringBuilder getLinkNew(String target){
		//Devuelve el StringBuilder del link para hacer un personal nuevo.
		return Item.Link("Nuevo Personal", path+"edit.jsp", target, "suelto");
	}
	@Deprecated
	public static StringBuilder getLinkEdit(Long id){
		//Este método construye el link para editar un personal.
		return Item.Link(Item.imgEditar, path+"edit.jsp?id="+id.toString(), null, null);
	}
	@Deprecated
	public static StringBuilder getLinkDelete(Long id){
		//Devuelve el StringBuilder del link que lleva a borrar un personal.
		return Item.Link(Item.imgBorrar, path+"del.jsp?id="+id.toString(), null, null);
	}
	
	public static StringBuilder editar(Long id){
		//devuelve el form para editar
		List<StringBuilder> items = new ArrayList();
		model.Personal p;
		if (id == null){
			p = new model.Personal();
		}else{
			model.manager m = new model.manager();
			p = m.getById(model.Personal.class, id);
			items.add(Item.Hidden("id", id.toString()));
		}
		//Imagen actual, 
		if (id != null)
			items.add(Item.Img(getUrlPhoto(id)));
		items.add(Item.Edit("nombre", "Nombre", p.getNombre()));
		items.add(Item.Edit("apellido", "Apellido", p.getApellido()));
		items.add(Item.Edit("dni", "DNI", p.getDni()));
		items.add(Item.File("foto", "Foto"));
		
		//Ojo, el ultimo parametro "true" dice que es un form multipart, es otro tipo de form, y los datos que devuelve se manejan de otra forma
		//ver que el save.jsp funciona diferente.
		//es necesario para poder tomar la imagen par la foto
		return Item.Form(items, path + "save.jsp", "Guardar",  true);
		
	}	
	public static Long save(Long id, String nombre, String apellido, String dni, byte[] foto){
		//Devuelve el id del elemento que guardó y al mismo tiempo guarda un personal.
		model.Personal p;
		model.manager m = new model.manager();
		if (id == null){
			p = new model.Personal();
		}else{
			p = m.getById(model.Personal.class, id);
		}
		p.setNombre(nombre);
		p.setApellido(apellido);
		p.setDni(dni);
		p.setFoto(foto);
		if(m.save(p))			
			return p.getId();
		else
			return null;
	}
	public static StringBuilder getLista(){
		//Construye la lista de personal.
		StringBuilder s = new StringBuilder();
		List<StringBuilder> rows = new ArrayList();
		
		List<StringBuilder> heads = new ArrayList();
		heads.add(Item.TableHeader("Id"));
		heads.add(Item.TableHeader("Nombre"));
		heads.add(Item.TableHeader("Apellido"));
		heads.add(Item.TableHeader("DNI"));				
		heads.add(Item.TableHeader("Foto"));			
		heads.add(Item.TableHeader("Editar"));		
		heads.add(Item.TableHeader("Eliminar"));		
		
		rows.add(Item.TableRow(heads));
		
		for (model.Personal p: model.Personal.getAll()){
			List<StringBuilder> cell = new ArrayList();
			cell.add(Item.TableCell(p.getId().toString()));
			cell.add(Item.TableCell(p.getNombre()));
			cell.add(Item.TableCell(p.getApellido()));
			cell.add(Item.TableCell(p.getDni()));
			cell.add(Item.TableCell(Item.Img(path+"foto.jsp?id="+p.getId().toString()).toString()));
			cell.add(Item.TableCell(getLinkEdit(p.getId()).toString()));
			cell.add(Item.TableCell(getLinkDelete(p.getId()).toString()));
			rows.add(Item.TableRow(cell));
		}
		s.append(Item.Table(rows));
		return s;
	}
	public static boolean delete(Long id){
		//Borra un personal.
		if (id==null) return false;
		model.manager m = new model.manager();
		model.Personal p = m.getById(model.Personal.class, id);
		if (p==null) return false;
		
		
		for(model.Programa prog : p.getDirectorDe()){
			prog.setDirector(null);//hibernate es muy lazy para hacer esto solo.
		}
		p.getDirectorDe().clear();
		for (model.Programa prog : p.getLocutorDe()){
			prog.getLocutores().remove(p);
		}
		p.getLocutorDe().clear();
		return m.remove(p);	
	}
}
