	package servlets;

import java.io.IOException;
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
import classes.EmployeeEducation;;

/**
 * Servlet implementation class AddWorkExperience
 */
@WebServlet("/AddEducation")
public class AddEducation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEducation() {
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
		String institution = request.getParameter("institution");
		String subject = request.getParameter("subject");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		java.sql.Date sqlStart = null;
		java.sql.Date sqlEnd = null;
		
		try {
			if(start != null) {
				java.util.Date utilDate=new java.util.Date();
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
				sqlStart = new java.sql.Date(utilDate.getTime());
			
			} else {
				System.out.println("Date start carielia!");
			}
			if(end != null) {
				java.util.Date utilDate=new java.util.Date();
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
				sqlEnd = new java.sql.Date(utilDate.getTime());
			
			} else {
				System.out.println("Date end carielia!");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		Account account = (Account)request.getSession().getAttribute("account");
		DBManager manager = (DBManager)getServletContext().getAttribute("DBManager");
		System.out.println("Me vamateb1 " + institution + " - " + subject);
		EmployeeEducation education = new EmployeeEducation(account.getID(), sqlStart, sqlEnd, "School",institution, subject, "", "", 0);
		System.out.println("Me vamateb2 " + institution + " - " + subject);
		System.out.println("Chaemata " + education.getInstitutionName() + " - " + education.getMajor());
		manager.addEducation(account.getID(), education);
		request.getRequestDispatcher("/JSP/AddEducation.jsp").forward(request, response);	
		//doGet(request, response);
	}

}
