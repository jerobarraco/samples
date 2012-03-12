<%@page import="java.net.URLEncoder"%>
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

	String side = request.getParameter("idElement");
	Long idElement = null;
	if (side != null){
		idElement = Long.valueOf(side);
	}	
	
	String mensaje = "";
	if (Bloque.remElement(id, idElement)){
		mensaje = "Elemento quitado correctamente";
	}else{
		mensaje = "Error al intentar quitar el elemento";
	}
	
	mensaje = URLEncoder.encode(mensaje, "UTF-8");
	response.sendRedirect(Bloque.getUrlEdit(id, idPrograma)+"&mensaje="+ mensaje);
%>

