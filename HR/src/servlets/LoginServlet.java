package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Account;
import classes.DBManager;
import classes.Hash;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      If user is already logged in redirects to MainPage.jsp
	 *      else redirects LogInPage.jsp
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = (Account) request.getSession().getAttribute("account");
		if (acc != null) {
			request.getRequestDispatcher("/JSP/MainPage.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/JSP/LogInPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String passHash = Hash.getHash(password);
		Account acc = manager.getAccount(userName);

		if (acc == null || !acc.getPassHash().equals(passHash)) {
			request.setAttribute("wrong", true);
			request.getRequestDispatcher("/JSP/LogInPage.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("account", acc);
			if (acc.getAccountType().equals(Account.COMPANY_ACCOUNT_TYPE)) {
				response.sendRedirect(request.getContextPath() + "/JSP/CompanyProfile.jsp");
			} else if (acc.getAccountType().equals(Account.EMPLOYEE_ACCOUNT_TYPE)) {
				response.sendRedirect(request.getContextPath() + "/JSP/UserProfile.jsp");
			}
		}
	}
}