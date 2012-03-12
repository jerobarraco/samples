<%-- 
    Document   : list
    Created on : 14-dic-2011, 18:15:47
    Author     : Administrador
--%>

<%@page import="form.Tema"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
		<title>Lista de temas</title>
	</head>
	<body>
		<%
			String mensaje = request.getParameter("mensaje");
			if(mensaje == null) mensaje = "";
			out.println("<h5>"+ mensaje +"</h5>");
		%>
		<%=Tema.getLista()%>	
	</body>
</html>
