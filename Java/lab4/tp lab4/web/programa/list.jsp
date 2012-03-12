<%@page contentType="text/html" pageEncoding="UTF-8" import ="form.*"%>
<!DOCTYPE html>
<% 
String dia = request.getParameter("dia");
Integer iDia =null;
if ((dia != null) && (!dia.isEmpty())){
	iDia = Integer.valueOf(dia);
}
String sidl = request.getParameter("idLocutor");
Long idLocutor = null;
if ((sidl != null) && (!sidl.isEmpty())){
	idLocutor = Long.valueOf(sidl);
}
String mensaje = request.getParameter("mensaje");
if (mensaje == null) mensaje = "";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
		<title>Listar Programas</title>
	</head>
	<body>
		<h5><%=mensaje%></h5>
		<%=Programa.getFormFilter(iDia, idLocutor)%>
		<%=Programa.getLista(iDia, idLocutor)%>
	</body>
</html>
