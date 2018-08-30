package pl.hubswi90.blog.admin.users;

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

@WebServlet(name = "admin/removeUser", urlPatterns = { "/admin/removeUser" })
public class RemoveUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IProfile tools;

	public RemoveUsers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long sessionUserId = Long.parseLong(request.getSession().getAttribute("userid").toString());

		Profile isAdmin = tools.getProfileFromDatabase(sessionUserId);

		if (StringUtils.equals(isAdmin.getProfileGroup(), "administrator")) {
			long profileId = Integer.parseInt(request.getParameter("id"));
			tools.removeExistingProfileInDatabase(profileId);
		}
		response.sendRedirect("http://localhost:8080/BlogMeJava/admin/users");
	}

}
