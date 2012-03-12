<%@page import="java.net.URLEncoder"%>
<%@page import="form.Programa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String sid = request.getParameter("id");
	String horario = request.getParameter("horario");
	String sdia = request.getParameter("dia");
	String sidDirector = request.getParameter("idDirector");
	
	Long id = null;
	Long idDirector = null;
	Integer dia = 0;//no hacer default a null
	
	if (sid != null){
		id = Long.valueOf(sid);
	}
	if (sidDirector != null){
		if (!sidDirector.isEmpty())//importante porque sidDirector puede ser "" por el select
			idDirector = Long.valueOf(sidDirector);
	}
	if (sdia != null )
		if(!sdia.isEmpty()) 
			dia = Integer.valueOf(sdia);

	String mensaje = "";
	id = Programa.save(id, dia, horario, idDirector);	
	if(id != null){
		mensaje = URLEncoder.encode("Guardado correctamente", "UTF-8");
		response.sendRedirect(Programa.getUrlEdit(id) +"&mensaje="+ mensaje);
	}else{
		mensaje = URLEncoder.encode("Error al guardar", "UTF-8");
			response.sendRedirect(Programa.getUrlLista() +"?mensaje="+ mensaje);
	}	
%>