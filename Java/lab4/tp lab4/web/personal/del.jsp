<%@page import="java.net.URLEncoder"%>
<%@page import="form.Personal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String sid = request.getParameter("id");
	Long id = null;
	if (sid != null){
		id = Long.valueOf(sid);
	}
	String mensaje = "";
	if (Personal.delete(id)){
		mensaje = "Personal eliminado correctamente";
	}else{
		mensaje = "Error al intentar eliminar el personal";
	}	
	mensaje = URLEncoder.encode(mensaje, "UTF-8");
	response.sendRedirect(Personal.getUrlLista()+"?mensaje="+ mensaje);
%>