<%@page import="java.net.URLEncoder"%>
<%@page import="form.Programa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
	String sid = request.getParameter("id");
	Long id = null;
	if (sid != null){
		id = Long.valueOf(sid);
	}

	String sidl = request.getParameter("idLocutor");
	Long idLocutor = null;
	if (sidl != null){
		idLocutor = Long.valueOf(sidl);
	}

	String mensaje = "";
	if (idLocutor!= null && id!=null){
		if (Programa.addLocutor(id, idLocutor)){
			mensaje = "Se ha agregado correctamente";
		}else{
			mensaje = "Ocurrió un error";
		}
	}else {
			mensaje = "Parámetros erroneos";
	};
	mensaje = URLEncoder.encode(mensaje, "UTF-8");
	response.sendRedirect(Programa.getUrlEdit(id) + "&mensaje="+ mensaje);
%>