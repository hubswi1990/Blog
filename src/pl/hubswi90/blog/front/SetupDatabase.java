package pl.hubswi90.blog.front;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.hubswi90.blog.interfaces.IOption;
import pl.hubswi90.blog.interfaces.IPosts;
import pl.hubswi90.blog.interfaces.IProfile;

/**
 * Servlet implementation class SetupDatabase
 */
@WebServlet(name = "setupDatabase", urlPatterns = { "/setupDatabase" })
public class SetupDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IProfile tools;
	
	@EJB
	private IPosts tools2;
	
	@EJB
	private IOption tools3;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupDatabase() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		String adminEmail = request.getParameter("adminEmail");
		String siteTitle = request.getParameter("siteTitle");
		String tagline = request.getParameter("tagline");
		
		tools.createGroups();
		tools.createAdmin(adminName, adminPassword, adminEmail);
		tools2.addNewArticleToDatabase("This is first article", "Hello!");
		tools3.addOption("sitetitle", siteTitle);
		tools3.addOption("tagline", tagline);
		
		response.sendRedirect("./admin/login.jsp");
	}

}
