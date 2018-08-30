package pl.hubswi90.blog.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "admin/logout", urlPatterns = { "/admin/logout" })
public class LogOutUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogOutUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("userid");
		request.getSession().invalidate();
		response.sendRedirect("./login.jsp");
	}
}
