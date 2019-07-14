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
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *      Gets parameters for adding new education, adds in base
	 *      redirects to AddEducation.jsp
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String institution = request.getParameter("institution");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String major = request.getParameter("major");
		String minor = request.getParameter("minor");
		String degree = request.getParameter("degree");
		java.sql.Date sqlStart = null;
		java.sql.Date sqlEnd = null;

		try {
			if (start != null) {
				java.util.Date utilDate = new java.util.Date();
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
				sqlStart = new java.sql.Date(utilDate.getTime());
			}
			if (end != null) {
				java.util.Date utilDate = new java.util.Date();
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
				sqlEnd = new java.sql.Date(utilDate.getTime());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Account account = (Account) request.getSession().getAttribute("account");
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
		EmployeeEducation education = new EmployeeEducation(account.getID(), sqlStart, sqlEnd, "", institution, 
				major, minor, degree, 0);
		manager.addEducation(account.getID(), education);
		request.getRequestDispatcher("/JSP/AddEducation.jsp").forward(request, response);
	}

}
