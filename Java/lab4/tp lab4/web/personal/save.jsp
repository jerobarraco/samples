<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileItem"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="form.Personal"		%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
//Como el save usa un form multypart, esto no sirve
	String sid = request.getParameter("id");
	String sNombre = request.getParameter("nombre");
	String sApellido = request.getParameter("apellido");
	String sDNI = request.getParameter("dni");
	byte foto[]= new byte [1];
	String mensaje = "";
	
	//
	ServletRequestContext src=new ServletRequestContext(request);
	
	//Si el formulario es enviado con Multipart
	if(ServletFileUpload.isMultipartContent(src)){
		//Necesario para evitar errores de NullPointerException
		DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024), new File("c:\\TEMP"));
		//Creamos un FileUpload
		ServletFileUpload upload=new ServletFileUpload(factory);
		//Procesamos el request para que nos devuelva una lista
		//con los parametros y ficheros.
		List lista = upload.parseRequest(src);
		File file= null;
		for (Object obj : lista){
			FileItem fi = (FileItem) obj;
			String fieldName = fi.getFieldName();
			
			String fileName = fi.getName();
			boolean isInMemory = fi.isInMemory();
			if (fi.isFormField()){
				String value = fi.getString();
				//tomcat no deja hacer switch over string
				if (fieldName.equals("id"))
					sid = value;
				else if(fieldName.equals("nombre"))
					sNombre = value;
				else if (fieldName.equals("apellido"))
					sApellido = value;
				else if (fieldName.equals("dni"))
					sDNI = value;
				else if (fieldName.equals("mensaje"))
					mensaje = value;
			}
			else{
				//aca contamos conque el unico que no es formfield es foto
				
					Long sizeInBytes = fi.getSize();
					foto = fi.get();/*
					foto = new char[sizeInBytes.intValue()];					
					fi.write(fs);*/
			}
		}
	
	}
	Long id = null;
	if (sid != null){
		id = Long.valueOf(sid);
	}
	id=Personal.save(id, sNombre, sApellido, sDNI, foto);
		if(id != null){
			mensaje = "Guardado correctamente";
			response.sendRedirect(Personal.getUrlEdit(id) +"&mensaje="+ mensaje);
		}else{
				mensaje = "Error al guardar";
				response.sendRedirect(Personal.getUrlLista() +"?mensaje="+ mensaje);
		}	
		mensaje = URLEncoder.encode(mensaje, "UTF-8");
%>