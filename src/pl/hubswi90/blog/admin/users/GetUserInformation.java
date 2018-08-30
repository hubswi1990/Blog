package pl.hubswi90.blog.admin.users;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import pl.hubswi90.blog.entity.Profile;
import pl.hubswi90.blog.entity.ProfileGroups;
import pl.hubswi90.blog.interfaces.IProfile;

@WebServlet(name = "admin/myProfile", urlPatterns = { "/admin/myProfile" })
public class GetUserInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IProfile tools;
	
    public GetUserInformation() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean changeInProfile = false;
		long id = Long.parseLong(request.getParameter("profileId"));
		String email = request.getParameter("email");
		String password = request.getParameter("newpasswd");
		String rePassword = request.getParameter("repasswd");
		String group = request.getParameter("role");
		long sessionUserId = Long.parseLong(request.getSession().getAttribute("userid").toString());
		
		Profile profile = tools.getProfileFromDatabase(id);
		Profile isAdmin = tools.getProfileFromDatabase(sessionUserId);
		
		if(StringUtils.isNotBlank(email) && !StringUtils.equals(email, profile.getProfileEmail())) {
			profile.setProfileEmail(email);
			changeInProfile = true;
		}
		
		if(StringUtils.isNotBlank(password) && StringUtils.isNotBlank(rePassword)) {
			if(StringUtils.equals(password, rePassword)) {
				profile.setProfilePassword(tools.hashPasswd(password));
				changeInProfile = true;
			}
		}
		
		if(StringUtils.isNotBlank(group) && !StringUtils.equals(group, profile.getProfileGroup())) {
			ProfileGroups profileGroup = tools.getGroup(group);
			profile.setProfileGroup(profileGroup);;
			changeInProfile = true;
		}
		
		if(changeInProfile && StringUtils.equals(isAdmin.getProfileGroup(), "administrator")) {
			tools.updateProfile(profile);
		} else if (changeInProfile && (id == sessionUserId)) {
			tools.updateProfile(profile);
		}
				
		response.sendRedirect("./users");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("profileId"));
		Profile user = tools.getProfileFromDatabase(id);
		List<?> list = tools.getAllGrooupsFromDatabase();
		request.setAttribute("profile", user);
		request.setAttribute("groupsList", list);
		RequestDispatcher view = request.getRequestDispatcher("./index.jsp?p=users&pp=profile");
		view.forward(request, response);
	}

}
