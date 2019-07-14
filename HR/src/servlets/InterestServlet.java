package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Account;
import classes.DBManager;

/**
 * Servlet implementation class InterestServlet
 */
@WebServlet("/InterestServlet")
public class InterestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InterestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      If users shows interest in vacancy , add in base information
	 *      about it.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = (Account) request.getSession().getAttribute("account");
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");

		int employeeId = acc.getID();
		int vacancyId = Integer.parseInt(request.getParameter("vacancyId"));

		if (manager.hasApplied(employeeId, vacancyId)) {
			manager.removeApplication(employeeId, vacancyId);
		} else {
			manager.addApplication(employeeId, vacancyId);
		}

	}

}
