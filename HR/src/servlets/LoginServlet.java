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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("agaeg ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String passHash = Hash.getHash(password);		
		Account acc = manager.getAccount(userName);
		
		if(acc == null || !acc.getPassHash().equals(passHash)) {
			request.getRequestDispatcher("/JSP/LogInPage.jsp").forward(request, response);
			System.out.println("aaaaa");
		} else {
			//request.getRequestDispatcher("/welcomeUser.jsp").forward(request, response);
			System.out.println("bbbb");
			request.getRequestDispatcher("/JSP/UserProfile.jsp").forward(request, response);
		}
		
	}

}