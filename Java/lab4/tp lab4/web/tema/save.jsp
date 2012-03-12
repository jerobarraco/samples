<%-- 
    Document   : save
    Created on : 14-dic-2011, 18:16:10
    Author     : Administrador
--%>

<%@page import="form.Tema"%>
<%@page import="java.net.URLEncoder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
		<%
			String sid = request.getParameter("id");
			String sAutor = request.getParameter("autor");
			String sTitulo = request.getParameter("titulo");
			String sDur = request.getParameter("duracion");
			String mensaje = "";
			Long id = null;
			if (sid != null){
				id = Long.valueOf(sid);
			}
		id =Tema.save(id, sTitulo, sAutor, sDur);
		if(id != null){
			mensaje = "Guardado correctamente";
			response.sendRedirect(Tema.getUrlEdit(id) +"&mensaje="+ mensaje);
		}else{
				mensaje = "Error al guardar";
				response.sendRedirect(Tema.getUrlLista() +"?mensaje="+ mensaje);
		}
%>

