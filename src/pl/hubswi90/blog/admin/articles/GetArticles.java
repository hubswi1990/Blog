package pl.hubswi90.blog.admin.articles;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.hubswi90.blog.interfaces.IPosts;

@WebServlet(name = "admin/getarticles", urlPatterns = { "/admin/getarticles" })
public class GetArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IPosts tools;
       
    public GetArticles() {
    	super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int article = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			article = Integer.parseInt(request.getParameter("page"));

		int numberOfRecords = tools.getCountPosts();
		int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
		List<?> list = tools.getArticlesFromDatabase((article - 1) * recordsPerPage, recordsPerPage);
		request.setAttribute("articlesList", list);
		request.setAttribute("noOfPages", numberOfPages);
		request.setAttribute("currentPage", article);
		RequestDispatcher view = request.getRequestDispatcher("./index.jsp?p=articles");
		view.forward(request, response);
	}

}
