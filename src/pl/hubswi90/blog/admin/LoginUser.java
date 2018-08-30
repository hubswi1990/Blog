package pl.hubswi90.blog.admin;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import pl.hubswi90.blog.entity.Profile;
import pl.hubswi90.blog.interfaces.IProfile;

@WebServlet(name = "admin/login", urlPatterns = { "/admin/login" })
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IProfile tools;
	
    public LoginUser() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		
		login = StringUtils.trim(login);
		password = StringUtils.trim(password);
		
		Profile admin = tools.authenticateAdmin(login, password);
		if (admin == null) {
			response.sendRedirect("./login.jsp");
		} else {
			request.getSession().setAttribute("userid", admin.getProfileId());
			response.sendRedirect("http://localhost:8080/BlogMeJava/admin/index.jsp");
		}
	}

}
