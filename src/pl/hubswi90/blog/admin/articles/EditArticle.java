package pl.hubswi90.blog.admin.articles;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import pl.hubswi90.blog.entity.Posts;
import pl.hubswi90.blog.interfaces.IPosts;

/**
 * Servlet implementation class EditArticle
 */
@WebServlet(name = "admin/editarticle", urlPatterns = { "/admin/editarticle" })
public class EditArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IPosts tools;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("articleId"));
		Posts article = tools.getArticle(id);
		request.setAttribute("article", article);
		RequestDispatcher view = request.getRequestDispatcher("./index.jsp?p=articles&pp=editor");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean changeInProfile = false;
		long id = Long.parseLong(request.getParameter("article"));
		String title = request.getParameter("articlename");
		String postContent = request.getParameter("textarea");
		
		Posts article = tools.getArticle(id);
		
		if(StringUtils.isNotBlank(title) && !StringUtils.equals(title, article.getPostTitle())) {
			article.setPostTitle(title);
			changeInProfile = true;
		}
		
		if(StringUtils.isNotBlank(postContent) && !StringUtils.equals(postContent, article.getPostContent())) {
			article.setPostContent(postContent);
			changeInProfile = true;
		}
		
		if(changeInProfile)
			tools.editArticle(article);
				
		response.sendRedirect("./getarticles");
	}

}
