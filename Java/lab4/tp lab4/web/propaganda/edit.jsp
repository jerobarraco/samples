<%-- 
    Document   : edit
    Created on : 14/12/2011, 19:51:49
    Author     : Ale
--%>

<%@page import="form.Propaganda"%>
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
				out.print("Nuevo Propaganda");
			}else{
				out.print("Editar Propaganda");
			}%></title>
	</head>
	<body>
		<h5><%=mensaje%></h5>
		<%=Propaganda.editar(id)%>
	</body>
</html>
