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
import pl.hubswi90.blog.interfaces.IProfile;

@WebServlet(name = "admin/addUser", urlPatterns = { "/admin/addUser" })
public class AddNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IProfile tools;

	public AddNewUser() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		long group = Long.parseLong(request.getParameter("role"));
		long sessionUserId = Long.parseLong(request.getSession().getAttribute("userid").toString());

		Profile user = tools.getProfileFromDatabase(sessionUserId);

		if (StringUtils.equals(user.getProfileGroup(), "administrator")) {
			if (StringUtils.isNotBlank(login) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(email)) {
				tools.addNewProfileToDatabase(login, password, email, group);
			}
		}
		response.sendRedirect("./users");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionAttribute = request.getSession().getAttribute("userid").toString();

		if (StringUtils.isNotBlank(sessionAttribute)) {
			List<?> list = tools.getAllGrooupsFromDatabase();
			request.setAttribute("groupsList", list);
			RequestDispatcher view = request.getRequestDispatcher("./index.jsp?p=users&pp=addNew");
			view.forward(request, response);
		} else {
			response.sendRedirect("./login.jsp");
		}
	}
}
