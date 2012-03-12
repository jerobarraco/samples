<%-- 
    Document   : edit
    Created on : 14-dic-2011, 15:51:59
    Author     : Administrador
--%>

<%@page import="form.Programa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
String sid = request.getParameter("id");
Long id =null;
if (sid != null){
	id = Long.valueOf(sid);
}%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
		<title>
			<%
			if (id == null){
				%>Nuevo Programa<%
			}else{
			%> 
			Editar Programa<%
					}%>
		</title>
	</head>
	<body>
		<%
			String mensaje = request.getParameter("mensaje");
			if (mensaje==null) mensaje = "";
			out.print("<h5>"+mensaje+"</h5>");
			out.print(Programa.editar(id));
			%>
	</body>
</html>
