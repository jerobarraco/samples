<%@page import="java.net.URLEncoder"%>
<%@page import="form.Programa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
	String sid = request.getParameter("id");
	Long id =null;
	if (sid != null){
		id = Long.valueOf(sid);
	}
	
	String mensaje = "";
	if (id != null){
		if (Programa.delete(id)){
			mensaje = "Eliminando correctamente";
		}else{
			mensaje = "Error al eliminar";
		}	
	}else{
		mensaje = "Parámetros erroneos";
	}
	mensaje = URLEncoder.encode(mensaje);
	response.sendRedirect(Programa.getUrlLista() + "?mensaje="+ mensaje);
%>
