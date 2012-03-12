<%-- 
    Document   : edit
    Created on : 14-dic-2011, 18:15:54
    Author     : Administrador
--%>

<%@page import="form.Tema"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
String sid = request.getParameter("id");
Long id = null;
if (sid != null){
	id = Long.valueOf(sid);
	}
	String mensaje = request.getParameter("mensaje");
if(mensaje == null) mensaje = "";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
		<title><%
			if (id == null){
				out.print("Nuevo Tema");
			}else{
				out.print("Editar Tema");
			}%></title>
	</head>
	<body>
		<h5><%=mensaje%></h5>
		<%=Tema.editar(id)%>
	</body>
</html>
