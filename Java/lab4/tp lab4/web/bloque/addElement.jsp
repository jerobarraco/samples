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

String sidt = request.getParameter("idTema");
Long idTema = null;
if (sidt != null){
	idTema = Long.valueOf(sidt);
}

String sidprop = request.getParameter("idPropaganda");
Long idPropaganda = null;
if (sidprop != null){
	idPropaganda = Long.valueOf(sidprop);
}

String action = request.getParameter("agregar");
if (action == null) action = "";

	String mensaje = "";
	if (action.equals("Agregar Tema")){
		if (Bloque.addTema(id, idTema)){
			mensaje = "Tema agregado correctamente";

		}else{
			mensaje = "Error al agregar el tema";
		}
	}else {
		if (Bloque.addPropaganda(id, idPropaganda)){
			mensaje = "Propaganda agregada correctamente";
		}else{
			mensaje = "Error al agregar la propaganda";
		}
	}

	mensaje = URLEncoder.encode(mensaje, "UTF-8");
	response.sendRedirect(Bloque.getUrlEdit(id, idPrograma)+"&mensaje="+ mensaje);
%>