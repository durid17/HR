package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Account;
import classes.Company;
import classes.CompanyProfile;
import classes.DBManager;
import classes.Hash;

/**
 * Servlet implementation class CompanyRegister
 */
@WebServlet("/CompanyRegister")
public class CompanyRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyRegister() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      If already logged in redirects to MainPage.jsp 
	 *      else redirects to CompanyRegister.jsp
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account acc = (Account) request.getSession().getAttribute("account");
		if (acc != null) {
			request.getRequestDispatcher("/JSP/MainPage.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/JSP/CompanyRegister.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");

		// Get account info
		String username = request.getParameter("username");
		String password = request.getParameter("psw");
		String essence = request.getParameter("essence");
		String passHash = Hash.getHash(password);
		String img = "../Images/compImage.png";

		// Check account
		if (manager.getAccount(username) != null) {
			request.setAttribute("used", true);
			request.getRequestDispatcher("JSP/CompanyRegister.jsp").forward(request, response);
			return;
		}

		// Create account
		Account newAccount = new Account(Account.DEFAULT_ID, new Date(System.currentTimeMillis()), username, passHash,
				Account.COMPANY_ACCOUNT_TYPE);
		// Add account in base
		newAccount = manager.addAccount(newAccount);

		// Update personal info
		String email = request.getParameter("email");
		String companyName = request.getParameter("companyName");

		CompanyProfile cmpPrf = new CompanyProfile(companyName, null, essence, null, img, email, null, null);
		Company newCompany = new Company(newAccount, cmpPrf);

		// Add info in base
		manager.updateCompany(newCompany);
		request.getSession().setAttribute("account", newAccount);

		response.sendRedirect(request.getContextPath() + "/JSP/CompanyProfile.jsp");
	}
}