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

/**
 * Servlet implementation class ChangeStatus
 */
@WebServlet(name = "admin/changeStatusUser", urlPatterns = { "/admin/changeStatusUser" })
public class ChangeStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private IProfile tools;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long profileId = Integer.parseInt(request.getParameter("id"));
		long sessionUserId = Long.parseLong(request.getSession().getAttribute("userid").toString());
		
		Profile user = tools.getProfileFromDatabase(sessionUserId);

		if (StringUtils.equals(user.getProfileGroup(), "administrator")) {
			Profile p = tools.getProfileFromDatabase(profileId);
			p.setProfileStatus(!p.getProfileStatus());
			tools.updateProfile(p);
		}
		response.sendRedirect("http://localhost:8080/BlogMeJava/admin/users");
	}

}
