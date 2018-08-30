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

import pl.hubswi90.blog.interfaces.IProfile;

@WebServlet(name = "admin/users", urlPatterns = { "/admin/users" })
public class GetUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IProfile tools;

	public GetUsers() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		int numberOfRecords = tools.getCountProfile();
		int numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
		List<?> list = tools.getProfileList((page - 1) * recordsPerPage, recordsPerPage);
		request.setAttribute("usersList", list);
		request.setAttribute("noOfPages", numberOfPages);
		request.setAttribute("currentPage", page);
		RequestDispatcher view = request.getRequestDispatcher("./index.jsp?p=users");
		view.forward(request, response);
	}
}
