package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.*;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager)getServletContext().getAttribute("DBManager");
		
		//Get account info
		String username = request.getParameter("username");
		String password = request.getParameter("psw");
		String passHash = Hash.getHash(password);
		
		
		//Check account
		if(manager.getAccount(username) != null) {
			request.getRequestDispatcher("JSP/CompanyRegister.jsp").forward(request, response);
			return;
		}
		
		//Create account
		Account newAccount = new Account(Account.DEFAULT_ID, new Date(System.currentTimeMillis()),
											username, passHash, Account.COMPANY_ACCOUNT_TYPE); 
		//Add account in base
		manager.addAccount(newAccount);
		
		//Update personal info
		String email = request.getParameter("email");
		String companyName = request.getParameter("companyName");
		String description = request.getParameter("description");
		
		CompanyProfile cmpPrf = new CompanyProfile(companyName, description,  null, null); 
		Company newCompany = new  Company(newAccount, cmpPrf);
		//Add info in base
		manager.updateCompany(newCompany);
		request.getRequestDispatcher("JSP/UserProfile.jsp").forward(request, response);
		//doGet(request, response);
	}
}