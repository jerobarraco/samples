<%@page import="java.net.URLEncoder"%>
<%@page import="form.Programa"%>
<%@page import="form.Bloque"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String sid = request.getParameter("id");
	Long id = null;
	if (sid != null){
		id = Long.valueOf(sid);
	}
	
	String sidp = request.getParameter("idPrograma");
	Long idPrograma = null;
	if (sidp != null){
		idPrograma = Long.valueOf(sidp);
	}
	
	String mensaje = "";
	if (Bloque.delete(id)){
		mensaje = "Bloque eliminado correctamente";
	}else{
		mensaje = "Error al eliminar bloque";
	}	
	mensaje = URLEncoder.encode(mensaje, "UTF-8");
	response.sendRedirect(Programa.getUrlEdit(idPrograma)+"&mensaje="+ mensaje);
%>