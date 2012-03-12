<%@page import="form.Programa"%>
<%@page import="form.Bloque"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
String sid = request.getParameter("id");
Long id = null;
if (sid != null){
	id = Long.valueOf(sid);
}
String sidp = request.getParameter("idPrograma");
Long idPrograma = null;
if (sidp != null){
	idPrograma = Long.valueOf(sidp);
}

String mensaje = request.getParameter("mensaje");
if (mensaje == null) mensaje ="";
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
		<%=Bloque.editar(id, idPrograma)%> <br /> 
		<%="Editar programa:"+Programa.getLinkEdit(idPrograma)%>
	</body>
</html>
