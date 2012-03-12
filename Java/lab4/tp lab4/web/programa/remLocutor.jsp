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
		if (Programa.remLocutor(id, idLocutor)){
			mensaje = "Quitado correctamente";
		}else{
			mensaje = "Error al quitar";
		}
	}else{
		mensaje = "Parámetros incorrectos";
	};
	mensaje = URLEncoder.encode(mensaje, "UTF-8");
	response.sendRedirect(Programa.getUrlEdit(id) + "&mensaje=" +mensaje);
%>
