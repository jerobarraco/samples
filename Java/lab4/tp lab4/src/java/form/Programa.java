package form;

import java.util.ArrayList;
import java.util.List;

public class Programa {
	static final String path="/programa/";
	//Acá está el Array de días de la semana. Este es el array del cual en model.Programa genera Integer dia como parámetro para posición de este Array.
	static String[] diasSemana = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
	
	public static String getUrlEdit(Long id){
		//Devuelve el String con el URL de editar programa.
		return path+"edit.jsp?id="+id.toString();
	}
	public static String getUrlLista(){
		//Devuelve el String con el URL de la list.jsp
		return path+"list.jsp";
	}
	@Deprecated
	public static StringBuilder getLinkLista(String target){
		//Devuelve el StringBuilder con el link de la lista 
		return Item.Link("Listar Programas", path+"list.jsp", target, null);
	}
	@Deprecated
	public static StringBuilder getLinkNew(String target){
		//Devuelve el StringBuilder con el link para el programa nuevo. 
		return  Item.Link("Nuevo Programa", path+"edit.jsp", target, "suelto");
	}
	
	
	@Deprecated
	public static StringBuilder getLinkEdit(Long id){
		//Devuelve el StringBuilder con el link para editar un programa. 
		return Item.Link(Item.imgEditar, getUrlEdit(id), null, null);
	}
	@Deprecated
	public static StringBuilder getLinkDelete(Long id){
		//Devuelve el StringBuilder con el link para poder borrar un programa.
		return Item.Link(Item.imgBorrar, path+"del.jsp?id="+id.toString(), null, null);
	}
	
	@Deprecated
	public static StringBuilder getLinkRemLocutor(Long id, Long idLocutor){
		//Devuelve el StringBuilder con el link para remover un programa.
		String rem = path+"remLocutor.jsp?id="+id.toString();
			rem += "&idLocutor="+idLocutor.toString();
		return Item.Link(Item.imgBorrar, rem, null, null);
	}
	
	public static StringBuilder getLista(Integer filtroDia, Long idLocSel){
		//Devuelve la tabla completa "embalada y lista para usar" con la lista de programas.
		List<StringBuilder> rows = new ArrayList();
		List<StringBuilder> heads = new ArrayList();
		
		heads.add(Item.TableHeader("ID"));
		heads.add(Item.TableHeader("Horario"));
		heads.add(Item.TableHeader("Duración"));
		heads.add(Item.TableHeader("Dia"));
		heads.add(Item.TableHeader("Director"));
		heads.add(Item.TableHeader("Locutores"));
		heads.add(Item.TableHeader("Editar"));
		heads.add(Item.TableHeader("Eliminar"));
		rows.add(Item.TableRow(heads));

		for (model.Programa p: model.Programa.getFiltrado(filtroDia, idLocSel)){
			List<StringBuilder> cells = new ArrayList();
			
			cells.add(Item.TableCell(String.valueOf(p.getId())));
			cells.add(Item.TableCell(String.valueOf(Item.DateASTR(p.getHorario()))));
			cells.add(Item.TableCell(String.valueOf(Item.DateASTR(p.getDuracion()))));
			
			String dia = diasSemana[p.getDia()];
			cells.add(Item.TableCell(dia));
			
			
			model.Personal dir = p.getDirector();
			String sdir= "";
			if (dir!= null) sdir = dir.toString();
			cells.add(Item.TableCell(sdir));			
			cells.add(Item.TableCell(getConcatLocutores(p).toString()));
			
			cells.add(Item.TableCell(getLinkEdit(p.getId()).toString() ));
			cells.add(Item.TableCell(getLinkDelete(p.getId()).toString()));

			rows.add(Item.TableRow(cells));
		}
		return Item.Table(rows);
	}
	public static boolean delete(Long id){
		//Borra un programa.
		if (id==null) return false;
		model.manager m = new model.manager();
		model.Programa p = m.getById(model.Programa.class, id);
		if (p==null) return false;
		return m.remove(p);	
	}
	public static StringBuilder editar(Long id){		
		//devuelve el form para editar
		StringBuilder cuerpo = new StringBuilder();
		List<StringBuilder> items = new ArrayList();
		model.Programa p;
		if (id == null){
			p = new model.Programa();
		}else{
			model.manager m = new model.manager();
			p = m.getById(model.Programa.class, id);
			items.add(Item.Hidden("id", p.getId().toString()));
		}
		items.add(new StringBuilder("Duracion : " + Item.DateASTR(p.getDuracion()) + "<br />"));
		items.add(Item.Edit("horario", "Horario", Item.DateASTR(p.getHorario())));
		

		items.add(getSelectDia(p.getDia()));
		
		Long idDir = null;
		if (p.getDirector() != null)
			idDir = p.getDirector().getId();
		items.add(getSelectPersonal(idDir, "Director", "idDirector", true));
		
		cuerpo.append(Item.Form(items, path+"save.jsp", "Guardar"));
		if (id!= null){
			cuerpo.append( new StringBuilder("<br /> <h5>Locutores</h5>"));
			cuerpo.append(getFormLocutores(p));
			//lista de periodos
			cuerpo.append( new StringBuilder("<br /> <h5>Bloques</h5>"));
			cuerpo.append(getFormBloques(p));
		}
		
		return cuerpo;
	}
	public static StringBuilder getFormFilter(Integer diaSeleccionado, Long idLocutorSel){
		//Devuelve un form con los filtros (que son locutor y día).
		StringBuilder cuerpo = new StringBuilder();
		List<StringBuilder> items = new ArrayList();
		items.add(getSelectDia(diaSeleccionado));
		items.add(Item.br);
		items.add(getSelectPersonal(idLocutorSel, "Locutor", "idLocutor", true));
		items.add(Item.br);
		items.add(Item.Reset("Limpiar"));
		cuerpo.append(Item.Form(items, path+"list.jsp", "Filtrar"));	
		return cuerpo;
	}
	public static StringBuilder getConcatLocutores(model.Programa p){
		//Concadena todos los locutores uno detrás del otro para la lista de programas.
		//La idea es que aparezcan en una sola tupla, así te devuelven todos los locutores en una sola llamada.
		StringBuilder concat = new StringBuilder();
		boolean primero = true;
		for(model.Personal pr : p.getLocutores()){
			if(!primero){
				concat.append(", ");
			}
			concat.append(pr.toString());
			primero = false;
		}
		return concat;
	}
	public static StringBuilder getSelectDia(Integer diaSeleccionado){
		//Construye el select (combobox de html) para los días. 
		List<StringBuilder> dias = new ArrayList();
		if (diaSeleccionado == null) diaSeleccionado = -1;
		dias.add(Item.SelectItem("", "", false));//al estar primero si no hay nada seleccionado, queda en ese
		boolean selected = false;
		for(Integer i = 0; i < diasSemana.length; i++){
			selected = 	diaSeleccionado.equals(i);
			dias.add(Item.SelectItem(i.toString(), diasSemana[i], selected));
		}
		return Item.Select("dia", dias, "Dias");
	}
	public static StringBuilder getSelectPersonal(Long idSel, String label, String name, boolean empty){
		//Construye el select de personal.
		List<StringBuilder> locs = new ArrayList();
		if (idSel == null) idSel = -1L;
		
		if (empty)
			locs.add(Item.SelectItem("", "", false));
		boolean selected = false;
		for(model.Personal per : model.Personal.getAll()){
			Long idPer = per.getId();
			selected = idPer.equals(idSel);
			locs.add(Item.SelectItem(idPer.toString(), per.toString(), selected));
		}
		return Item.Select(name, locs, label);
	}
	private static StringBuilder getFormBloques(model.Programa p){
		//Construye el form para bloques (con una tabla dentro).
		StringBuilder cuerpo = new StringBuilder();
		List<StringBuilder> rows = new ArrayList();
		List<StringBuilder> heads = new ArrayList();
		
		heads.add(Item.TableHeader("Id"));
		heads.add(Item.TableHeader("Duración"));
		heads.add(Item.TableHeader("Editar"));
		heads.add(Item.TableHeader("Quitar"));
		
		rows.add(Item.TableRow(heads));
		
		for (model.Bloque b: p.getBloques()){
			List<StringBuilder> cells = new ArrayList();
			String bid = b.getId().toString();
			String bval = Item.DateASTR(b.getDuracion());
			
			cells.add(Item.TableCell(bid));
			cells.add(Item.TableCell(bval));
			cells.add(Item.TableCell(Bloque.getLinkEdit(b.getId(), p.getId()).toString()));
			cells.add(Item.TableCell(Bloque.getLinkDelete(b.getId(), p.getId()).toString()));
			rows.add(Item.TableRow(cells));
		}
		
		cuerpo.append(Item.Table(rows));
		cuerpo.append(Bloque.getLinkNew(p.getId()));
		
		return cuerpo;
	}
	private static StringBuilder getFormLocutores(model.Programa p){
		//Construye un form de locutores (con una tabla dentro).
		StringBuilder cuerpo = new StringBuilder();
		List<StringBuilder> rows = new ArrayList();
		List<StringBuilder> heads = new ArrayList();
		//List<StringBuilder> sItems = new ArrayList();
		List<StringBuilder> formItems = new ArrayList();
		//lista de locutores
		heads.add(Item.TableHeader("Id"));
		heads.add(Item.TableHeader("Foto"));
		heads.add(Item.TableHeader("Personal"));
		heads.add(Item.TableHeader("Quitar"));
		
		rows.add(Item.TableRow(heads));
		//lista de locutores
		for(model.Personal per: p.getLocutores()){
			List<StringBuilder> cells = new ArrayList();
			cells.add(Item.TableCell(per.getId().toString()));
			String imagen = Item.Img(Personal.getUrlPhoto(per.getId())).toString();
			cells.add(Item.TableCell(imagen));
			cells.add(Item.TableCell(per.toString()));
			cells.add(Item.TableCell(getLinkRemLocutor(p.getId(), per.getId()).toString()));
			rows.add(Item.TableRow(cells));
		}
		
		cuerpo.append(Item.Table(rows));
		formItems.add(getSelectPersonal(null,"Locutor", "idLocutor", false));

		formItems.add(Item.Hidden("id", p.getId().toString()));		
		StringBuilder form = Item.Form(formItems, "addLocutor.jsp", "Agregar");
		
		cuerpo.append(form);
		return cuerpo;
	}
	public static Long save(Long id, Integer dia, String horario, Long idDirector){
		//Devuelve el id del programa que acaba de guardar.
		//Guarda un programa.
		model.manager m = new model.manager();
		model.Programa p;
		if (id == null){
			p = new model.Programa();
			m.save(p);
		}else{
			p = m.getById(model.Programa.class, id);
		}
		if (p==null) return null;
		
		if (idDirector == null){
			p.setDirector(null);
		}else{
			model.Personal per = m.getById(model.Personal.class, idDirector);
			if (per == null) return null;
			p.setDirector(per);
		}
		
		p.setDia(dia);
		p.setHorario(Item.STRADate(horario));
		
		if( m.save(p))
			return p.getId();
		else
			return null;
	}
	public static boolean addLocutor(Long id, Long idLocutor){
		//Agrega un locutor a un programa.
		if (id ==null || idLocutor == null) return false;
		
		model.manager m = new model.manager();
		model.Programa p = m.getById(model.Programa.class, id);
		if (p==null) return false;
		
		model.Personal loc = m.getById(model.Personal.class, idLocutor);
		if(loc ==null)return false;
		
		p.getLocutores().add(loc);		
		m.persist(p);
		return m.save(p);
	}
	public static boolean remLocutor(Long id, Long idLocutor){
		//Remueve un locutor de un programa.
		model.manager m = new model.manager();
		model.Programa p = m.getById(model.Programa.class, id);
		if (p==null) return false;
		
		
		for (model.Personal per : p.getLocutores()){
			if (per.getId().equals(idLocutor)){
				p.getLocutores().remove(per);
				return m.save(p);
			}
		}
		return false;
	}
}
