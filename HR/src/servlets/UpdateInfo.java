package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
 * Servlet implementation class UpdateInfo
 */
@WebServlet("/UpdateInfo")
public class UpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfo() {
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
		// TODO Auto-generated method stub
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
//		String phoneNumber = request.getParameter("phoneNumber");
//		String address = request.getParameter("address");
//		String description = request.getParameter("description");
//		String gender = request.getParameter("gender");
//		String dt = request.getParameter("bday");
//		java.sql.Date sqlDate = null;
//		String working = request.getParameter("work");
//		boolean work = false;
//		if(working != null) {
//			work = true;
//		}
//		
//		System.out.println("##################################");
//		System.out.println("description " + description);
//		System.out.println("Gender " + gender);
//		System.out.println("dt " + dt);
//		System.out.println("working" + working + ": boolean - " + work);
//		System.out.println("*********************************");
//		
//		try {
//			if(dt != null) {
//				java.util.Date utilDate=new java.util.Date();
//				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
//				sqlDate = new java.sql.Date(utilDate.getTime());
//			
//			} else {
//				System.out.println("Date carielia!");
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
		
		Account account = (Account)request.getSession().getAttribute("account");
		DBManager manager = (DBManager)getServletContext().getAttribute("DBManager");
		Employee emp = manager.getEmployee(account.getID());
		EmployeeProfile profile = new EmployeeProfile(firstName, lastName, null, null, null, null, email, null, null, null, null, false);
			
		emp.setProfile(profile);
		manager.updateEmployee(emp);
		request.getRequestDispatcher("/JSP/Settings-Info-User.jsp").forward(request, response);	
		//doGet(request, response);
	}

}
