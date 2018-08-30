package pl.hubswi90.blog.front;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.hubswi90.blog.entity.Option;
import pl.hubswi90.blog.interfaces.IOption;
import pl.hubswi90.blog.interfaces.IPosts;
import pl.hubswi90.blog.interfaces.IProfile;

/**
 * Servlet implementation class GetArticle
 */
@WebServlet(name = "getarticle", urlPatterns = { "/getarticle" })
public class GetArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IPosts tools;
	
	@EJB 
	private IOption tools2;
	
	@EJB
	private IProfile tools3;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int countposts = tools.getCountPosts();
		int countProfile = tools3.getCountProfile();
		int countProfileGroup = tools3.getCountProfileGroups();
		int countOptions = tools2.getCountOptions();
		
		if(countposts == 0 && countProfile == 0 && countProfileGroup == 0 && countOptions == 0){
			RequestDispatcher view = request.getRequestDispatcher("./initdb.jsp");
			view.forward(request, response);
		} else {
		
		int article = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			article = Integer.parseInt(request.getParameter("page"));

		int numberOfRecords = tools.getCountPosts();
		int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
		List<?> list = tools.getArticlesFromDatabase((article - 1) * recordsPerPage, recordsPerPage);
		List<?> recentPosts = tools.getRecentPosts(5);
		request.setAttribute("articlesList", list);
		request.setAttribute("recentPosts", recentPosts);
		request.setAttribute("noOfPages", numberOfPages);
		request.setAttribute("currentPage", article);
		
		Option siteTitle =  tools2.getOptionValue("sitetitle");
		Option tagline = tools2.getOptionValue("tagline");
		
		request.setAttribute("sitetitle", siteTitle.getOptionValue());
		request.setAttribute("tagline", tagline.getOptionValue());
		
		RequestDispatcher view = request.getRequestDispatcher("./ListArticle.jsp");
		view.forward(request, response);
		}
	}

}
