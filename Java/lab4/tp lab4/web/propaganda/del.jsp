<%@page import="java.net.URLEncoder"%>
<%@page import="form.Propaganda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
		<%
			String sid = request.getParameter("id");
			Long id = null;
			if (sid != null){
				id = Long.valueOf(sid);
			}
			String mensaje = "";
		if (Propaganda.delete(id)){
			mensaje = "Eliminado correctamente";
		}else{
				mensaje = "Error al eliminar";
		}	
			mensaje = URLEncoder.encode(mensaje, "UTF-8");
			response.sendRedirect(Propaganda.getUrlLista() + "?mensaje=" + mensaje);
		%>

