<%@page import="form.Dyn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String obj = request.getParameter("object");
String sid = request.getParameter("id");
Long id = null;
if (sid != null)
	id = Long.valueOf(sid);

id = Dyn.save("model."+obj, id, request);
response.sendRedirect("/dyn/edit.jsp?object="+obj+"&id="+id.toString());
%>