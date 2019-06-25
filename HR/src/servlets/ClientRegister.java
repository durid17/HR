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
import classes.Employee;
import classes.EmployeeProfile;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class ClientRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientRegister() {
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
		String username = request.getParameter("username");
		
		String password = request.getParameter("psw");
		
		if(manager.getAccount(username) != null) {
			request.getRequestDispatcher("JSP/ClientRegister.jsp").forward(request, response);
			return;
		} 	
		
		Account newAccount = new Account(Account.DEFAULT_ID, new Date(System.currentTimeMillis()),
											username, password, Account.EMPLOYEE_ACCOUNT_TYPE); 

		manager.addAccount(newAccount);
		
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		
		String about = request.getParameter("comments");
		
//		EmployeeProfile profile = new EmployeeProfile(firstname, lastname, gender, birthDate, majorProf,
//				minorProf, email, phoneNumber, address, description, profilePicture);
		
//		Employee employee = new Employee(newAccount, profile);
		
		request.getRequestDispatcher("JSP/UserProfile.jsp").forward(request, response);
		
	}

}
