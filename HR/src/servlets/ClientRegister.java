package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Account;
import classes.DBManager;
import classes.Employee;
import classes.EmployeeProfile;
import classes.Hash;

/**
 * Servlet implementation class Register
 */
@WebServlet("/ClientRegister")
public class ClientRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClientRegister() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      If already logged in redirects to MainPage.jsp else redirects to
	 *      ClientRegister.jsp
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = (Account) request.getSession().getAttribute("account");
		if (acc != null) {
			request.getRequestDispatcher("/JSP/MainPage.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/JSP/ClientRegister.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 *      Gets Parameters for client registration , registers user in base
	 *      redirects to UserProfile.jsp
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");

		String username = request.getParameter("userName");
		String password = request.getParameter("psw");
		String passHash = Hash.getHash(password);
		String img = "../Images/avatar.png";
		if (manager.getAccount(username) != null) {
			request.setAttribute("used", true);
			request.getRequestDispatcher("JSP/ClientRegister.jsp").forward(request, response);
			return;
		}

		Account newAccount = new Account(Account.DEFAULT_ID, new Date(System.currentTimeMillis()), username, passHash,
				Account.EMPLOYEE_ACCOUNT_TYPE);

		newAccount = manager.addAccount(newAccount);

		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("email");

		EmployeeProfile profile = new EmployeeProfile(firstname, lastname, null, null, null, null, email, null, null,
				null, img, false);
		Employee employee = new Employee(newAccount, profile);

		manager.updateEmployee(employee);
		request.getSession().setAttribute("account", newAccount);
		response.sendRedirect(request.getContextPath() + "/JSP/UserProfile.jsp");
	}

}
