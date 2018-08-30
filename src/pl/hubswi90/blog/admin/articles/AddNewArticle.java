package pl.hubswi90.blog.admin.articles;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import pl.hubswi90.blog.interfaces.IPosts;

@WebServlet(name = "admin/newarticle", urlPatterns = { "/admin/newarticle" })
public class AddNewArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IPosts tools;
       
    public AddNewArticle() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String postTitle = request.getParameter("articlename");
		String postContent = request.getParameter("textarea");
		
		if(StringUtils.isNotBlank(postTitle) && StringUtils.isNotBlank(postContent))
			tools.addNewArticleToDatabase(postContent, postTitle);
		
		response.sendRedirect("./getarticles");
	}
}
