package pl.hubswi90.blog.admin.settings;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.hubswi90.blog.entity.Option;
import pl.hubswi90.blog.interfaces.IOption;

/**
 * Servlet implementation class GetOption
 */
@WebServlet("/admin/settings")
public class GetOption extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IOption tools;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOption() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Option siteTitle =  tools.getOptionValue("sitetitle");
		Option tagline = tools.getOptionValue("tagline");
		
		request.setAttribute("sitetitle", siteTitle.getOptionValue());
		request.setAttribute("tagline", tagline.getOptionValue());
		RequestDispatcher view = request.getRequestDispatcher("./index.jsp?p=settings");
		view.forward(request, response);
	}

}
