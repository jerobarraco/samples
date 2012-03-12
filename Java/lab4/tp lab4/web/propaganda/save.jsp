<%-- 
    Document   : save
    Created on : 14/12/2011, 19:53:02
    Author     : Ale
--%>

<%@page import="java.net.URLEncoder"%>
<%@page import="form.Propaganda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
		<%
			String sid = request.getParameter("id");
			String sEmpresa = request.getParameter("empresa");
			String sDur = request.getParameter("duracion");
			String mensaje = "";
			Long id = null;
			if (sid != null){
				id = Long.valueOf(sid);
			}
		id = Propaganda.save(id, sEmpresa, sDur);
		if(id != null){
			mensaje = URLEncoder.encode("Guardado correctamente", "UTF-8");			
			response.sendRedirect(Propaganda.getUrlEdit(id) +"&mensaje="+ mensaje);
		}else{
			mensaje = URLEncoder.encode("Error al guardar", "UTF-8");				
			response.sendRedirect(Propaganda.getUrlLista() +"?mensaje="+ mensaje);
		}			
		%>

