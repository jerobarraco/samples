<%-- 
    Document   : index
    Created on : 13-dic-2011, 20:57:31
    Author     : Administrador
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"
				import="form.*;"
				%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CoolRadio</title>
		<link rel="stylesheet" href="/estilo.css" type="text/css"></link>
	</head>
	<body>
		<!-- session : <%=session.getId()%>
		<br />te puedo esperar hasta <%=String.valueOf(session.getMaxInactiveInterval())%> segundos, pasado ese tiempo me olvido quien sos. -->
		<div><table class="ini"><tr>
					<td class="ini"><%=Tema.getLinkLista("centro")%></td>
					<td class="ini"><%=Propaganda.getLinkLista("centro")%> </td>
					<td class="ini"><%=Personal.getLinkLista("centro")%></td> 
					<td class="ini"><%=Programa.getLinkLista("centro")%> </td>
				</tr>
				<tr>
					<td class="ini"><%=Tema.getLinkNew("centro")%></td>
					<td class="ini"><%=Propaganda.getLinkNew("centro")%></td>
					<td class="ini"><%=Personal.getLinkNew("centro")%></td> 
					<td class="ini"><%=Programa.getLinkNew("centro")%></td>
				</tr>
			</table></div>
		<iframe src="Wellcome.jsp" name="centro"></iframe>
	</body>
</html>
