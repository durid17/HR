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
import classes.WorkExperience;

/**
 * Servlet implementation class AddWorkExperience
 */
@WebServlet("/AddWorkExperience")
public class AddWorkExperience extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddWorkExperience() {
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
		String company = request.getParameter("company");
		String position = request.getParameter("position");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String empType = request.getParameter("emptype");
		String achievement = request.getParameter("achievement");
		String duty = request.getParameter("duty");
		String profession = request.getParameter("profession");
		
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
		WorkExperience workExperience = new WorkExperience(account.getID(), sqlStart, sqlEnd, company, profession, position, empType, duty, achievement);
		System.out.println("Added " + workExperience.getEmploymentType());
		System.out.println("Added " + workExperience.getDuty());
		System.out.println("Added " + workExperience.getAchievement());
		System.out.println("Pro " + workExperience.getProfession());
		manager.addWorkExp(account.getID(), workExperience);
		request.getRequestDispatcher("/JSP/AddWorkExperience.jsp").forward(request, response);	
		//doGet(request, response);
	}

}
