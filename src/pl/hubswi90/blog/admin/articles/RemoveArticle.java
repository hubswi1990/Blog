package pl.hubswi90.blog.admin.articles;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.hubswi90.blog.interfaces.IPosts;

/**
 * Servlet implementation class RemoveArticle
 */
@WebServlet(name = "admin/removearticle", urlPatterns = { "/admin/removearticle" })
public class RemoveArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IPosts tools;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("articleid"));
		tools.removeArticle(id);
		response.sendRedirect("./getarticles");
	}

}
