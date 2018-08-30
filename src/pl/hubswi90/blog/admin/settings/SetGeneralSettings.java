package pl.hubswi90.blog.admin.settings;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import pl.hubswi90.blog.entity.Profile;
import pl.hubswi90.blog.interfaces.IOption;
import pl.hubswi90.blog.interfaces.IProfile;

/**
 * Servlet implementation class SetGeneralSettings
 */
@WebServlet("/admin/setglobalsettings")
public class SetGeneralSettings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private IOption tools;
	
	@EJB
	private IProfile tools2;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetGeneralSettings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long sessionUserId = Long.parseLong(request.getSession().getAttribute("userid").toString());
		
		Profile isAdmin = tools2.getProfileFromDatabase(sessionUserId);
		
		if (StringUtils.equals(isAdmin.getProfileGroup(), "administrator")) {
			String sitetitle = request.getParameter("sitetitle");
			tools.setOptionValue("sitetitle", sitetitle);
		
			String tagLine = request.getParameter("tagline");
			tools.setOptionValue("tagline", tagLine);
		}
		
		response.sendRedirect("./index.jsp");
	}

}
