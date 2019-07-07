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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(1313515);
		Account acc = (Account)request.getSession().getAttribute("account");
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
		
		int employeeId = acc.getID();
	  	int vacancyId = Integer.parseInt(request.getParameter("vacancyId"));
	  	
	  	
	  	if(manager.hasApplied(employeeId, vacancyId)) {
	  		manager.removeApplication(employeeId, vacancyId);
	  	} else {
	  		manager.addApplication(employeeId, vacancyId);
	  	}
		
	}

}
