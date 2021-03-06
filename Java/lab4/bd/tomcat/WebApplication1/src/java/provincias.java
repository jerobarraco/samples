import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Administrador
 */
public class provincias extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("text/javascript");
		PrintWriter out = response.getWriter();
		try {
			List<Provincia> provs = manager.nativeQueryList(Provincia.class, "Select * from Provincias;");
			out.println("{\n"+
							"totalCount:"+provs.size()+",\n"+
							"root:[");
							
			boolean notfirst = false;
			for (Provincia p: provs){
				if (notfirst) {
					out.println(",");
				}
				out.print(p.toJSON());
				notfirst = true;
			}
			out.println("]}");
			/* TODO output your page here
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet NewServlet2</title>");  
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet NewServlet2 at " + request.getContextPath () + "</h1>");
			out.println("</body>");
			out.println("</html>");
			 */
		}
		catch (Exception e) {
			out.append(e.getMessage());
		} finally {			
			out.close();
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/** 
	 * Handles the HTTP <code>GET</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}

	/** 
	 * Handles the HTTP <code>POST</code> method.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		processRequest(request, response);
	}

	/** 
	 * Returns a short description of the servlet.
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
