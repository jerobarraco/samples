<%@page import="model.Personal"%><%@page import="model.manager"%><%////MUcHO OJO CON ESTO!!! SI DEJAN UN ENTER SE VAN A METER 2 caracteres DE ENTER ANTES DE LOS DATOS! y ropen todo!!
//le decimos al BrOWSER (opera) a travez de la respuesta (response) que le vamos a devolver un binario(octet-stream) y no un texto
response.setContentType("application/octet-stream");
//response.setCharacterEncoding("UTF-8"); //ojo funciona con latin1
//le decimos que el binario viene adjunto y se llama foto.jpg
response.setHeader("Content-Disposition","attachment;filename=foto.jpg");


String sid = request.getParameter("id");
if (sid == null) return;
Long id = Long.valueOf(sid);
//no es bueno acceder al modelo desde acá pero es lo que hay
manager m = new manager();

Personal p = m.getById(Personal.class, id);
//Esto es importante, out.write requiere un char[] que no se puede convertir directo desde byte[]
//1º cnoertimos el byte[] a String usando el encoding isoxxxx (latin-1 creo) no se porque usa ese encoding.. aparentemente es por tomcat
byte[] bfoto = p.getFoto();
response.setContentLength(bfoto.length);
String foto = new String(bfoto, "ISO8859-1");
//Y ahora convertimos el string a char[] lo cual no solo es ineficiente (por copiar 3 veces el string) sino que peligroso por que se 
//convierte el char en varios encodings
char [] cfoto = foto.toCharArray();
out.clear();
out.clearBuffer();
out.write(cfoto);
return;
%>