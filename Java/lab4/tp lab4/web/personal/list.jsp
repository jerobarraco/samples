<%@page import="form.Personal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
		<title>Lista de personal</title>
	</head>
	<body>
		<%
			String mensaje = request.getParameter("mensaje");
			if (mensaje == null) mensaje ="";
			out.println("<h5>"+mensaje+"</h5>");
		%>
		<%=Personal.getLista()%>
	</body>
</html>
