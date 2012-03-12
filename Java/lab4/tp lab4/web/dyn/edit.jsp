
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
		String object = "model."+request.getParameter("object");
		String sid = request.getParameter("id");
		if (sid == null || sid.equals("-1")) {
			out.print ("error");
			return;
			}
		Long id = Long.valueOf(sid);
		
		out.write(Dyn.getForm(object, id).toString());
		%>
	</body>
</html>
