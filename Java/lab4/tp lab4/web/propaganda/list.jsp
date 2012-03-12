<%-- 
    Document   : list.jsp
    Created on : 14/12/2011, 19:49:51
    Author     : Ale
--%>

<%@page import="form.Propaganda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
		<title>Lista de propagandas</title>
	</head>
	<body>
		<%
			String mensaje = request.getParameter("mensaje");
			if(mensaje == null) mensaje = "";
			out.println("<h5>"+ mensaje +"</h5>");
		%>
		<%=Propaganda.getLista()%>		
	</body>
</html>
