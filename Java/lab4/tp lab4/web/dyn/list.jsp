<%-- 
    Document   : list
    Created on : 17-dic-2011, 17:49:42
    Author     : Administrador
--%>

<%@page import="form.Dyn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<%
		String ClName = "model."+request.getParameter("object");
		String sid = request.getParameter("id");
		Long id = null;
		if (sid!=null) id = Long.valueOf(sid);
		%>
		<%=Dyn.getList(ClName)%>
	</body>
</html>
