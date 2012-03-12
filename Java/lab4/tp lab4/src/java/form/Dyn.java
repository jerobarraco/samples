package form;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.manager;

public class Dyn {

	public static StringBuilder getList(String Classname){
		StringBuilder s = new StringBuilder();
	 //creamos una lista de metodos
	 List<Method> metodos = new ArrayList();
	 //lista de items en la base de datos
	 List<Object> items = null;

	 List<StringBuilder> heads = new ArrayList();
	 List<StringBuilder> rows = new ArrayList();
		try {
			//obtenemos la clase
			Class c = Class.forName(Classname);
			 
			 for (Method m: c.getMethods()){
				String name = m.getName();
			  if (name.startsWith("get")){
					//obtenemos el metodo para obtener todos (ojo esto rompe todo si no es una clase nuestra q implemente esto)
					//llamamos al metodo getAll y lo casteamos a list (obvio que si no devuelve un list rompemos todo)
					switch (name) {
						case "getAll":
							items = (List)(m.invoke(null, null));
							break;
						case "getClass":
							continue;
						default:
							metodos.add(m);
							String h = m.getName().substring(3);
							heads.add(Item.TableHeader(h));
							break;
					}
			  }
			 }
			heads.add(Item.TableHeader("Editar"));
			heads.add(Item.TableHeader("Eliminar"));
			rows.add(Item.TableRow(heads));
			
			for (Object o : items){
				List<StringBuilder> cells = new ArrayList();
				String oid = "";
				for(Method m: metodos){
					
					Object val = m.invoke(o, null);
					if (val == null) val = "";
					if (m.getName().equals("getId"))
						oid = val.toString();
					
					String tname = m.getReturnType().getName();
					if (tname.equals("java.util.Date"))
						cells.add(Item.TableCell(Item.DateASTR((Date) val)));
					else
						cells.add(Item.TableCell(val.toString()));
				}
				String obj = Classname.substring(Classname.indexOf(".")+1);
				cells.add(Item.TableCell(Item.Link("Editar", "/dyn/edit.jsp?object="+obj+"&id="+oid, null, null).toString()));
				cells.add(Item.TableCell(Item.Link("Eliminar", "/dyn/del.jsp?object="+obj+"&id="+oid, null, null).toString()));
				rows.add(Item.TableRow(cells));
			}
		} catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
			ex.printStackTrace();
		}
		s.append(Item.Table(rows));
		return s;
	}
	public static StringBuilder getForm(String Classname , Long id){
		StringBuilder s = new StringBuilder();
		List<StringBuilder> items = new ArrayList();
		manager m = new manager();
		try {
			Class c = Class.forName(Classname);
			String obj = Classname.substring(Classname.indexOf(".")+1);
			items.add(Item.Hidden("object", obj));
			Object rec = m.getById(c, id);
			for (Method me : c.getMethods()){
				String name = me.getName();
				if (!name.startsWith("get") || name.equals("getClass") || name.equals("getAll"))
					continue;
				name = name.substring(3);
				Object val = me.invoke(rec,  null);
				if (name.equalsIgnoreCase("id"))
					items.add(Item.Hidden("id", val.toString()));//notar que id lo pongo a mano y en MINUSCULA
				else {
					String sval= "";
					if (val!=null){
						String tname = me.getReturnType().getName();
						if (tname.equals("java.util.Date"))
							sval = Item.DateASTR((Date)val);
						else
							sval = val.toString();
						
					}
					items.add(Item.Edit(name, name, sval));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		s.append(Item.Form(items, "/dyn/save.jsp", "Guardar"));
		return s;
	}
	public static Long save(String Classname , Long pid, javax.servlet.http.HttpServletRequest req){
		Long id = -1L;
		manager m = new manager();
		Method getId = null;
		try {
			Class c = Class.forName(Classname);
			Object rec=null;
			if (pid == null)
				rec = c.newInstance();
			else
				rec = m.getById(c, pid);
			for (Method me : c.getMethods()){
				String name = me.getName();
				if (name.equals("getId")){
					getId=me;
					continue;
				}
				if (!name.startsWith("set") || name.equals("setId"))
					continue;
				name = name.substring(3);//quitamos el set
				String val = req.getParameter(name);
				if (val!=null){
					Class pt = me.getParameterTypes()[0];//contamos conque tenga un solo parametro
					String tname = pt.getName();
					if (tname.equals("java.lang.String"))
		    			me.invoke(rec, me.getParameterTypes()[0].cast(val));
					if (tname.equals("java.util.Date"))
						me.invoke(rec, Item.STRADate(val));
					
				
				}
			}
			m.save(rec);
			id = (Long) getId.invoke(rec,null);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		};
	return id;
	}
}