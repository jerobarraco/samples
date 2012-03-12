<%@page import="java.net.URLEncoder"%>
<%@page import="form.Tema"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
		<%
			String sid = request.getParameter("id");
			Long id = null;
			if (sid != null){
				id = Long.valueOf(sid);
			}
			String mensaje = "";
		if (Tema.delete(id)){
			mensaje = "Eliminado correctamente";
		}else{
				mensaje = "Error al eliminar";
		}	
			mensaje = URLEncoder.encode(mensaje, "UTF-8");
			response.sendRedirect(Tema.getUrlLista() + "?mensaje=" + mensaje);
		%>
