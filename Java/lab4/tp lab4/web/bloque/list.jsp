<%-- OJO; este no se usa es solo para debug--%>
<%@page import="form.Bloque"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String sidp = request.getParameter("idPrograma");
	Long idPrograma = 0L;
	if (sidp != null) idPrograma = Long.parseLong(sidp);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
		<title>Lista de temas</title>
	</head>
	<body>
		<%=Bloque.getLista(idPrograma)%>
	</body>
</html>
