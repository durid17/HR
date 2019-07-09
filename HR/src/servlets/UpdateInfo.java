package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
import classes.Language;

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
		String img =  request.getParameter("image");
		String majorProfession =  request.getParameter("majorProfession");
		String gender = request.getParameter("gender");
		String phoneNumber = request.getParameter("phoneNumber");
		String address = request.getParameter("address");
		String description = request.getParameter("description");

		String dt = request.getParameter("bday");
		java.sql.Date sqlDate = null;
	
		try {
			if(dt != null) {
				java.util.Date utilDate=new java.util.Date();
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
				sqlDate = new java.sql.Date(utilDate.getTime());
			
			} else {
				System.out.println("Date carielia!");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		int id = Integer.parseInt(request.getParameter("id"));
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
		List<String> myTags = manager.getEmployeeTags(id);
		
		for(int i = 0 ; i < myTags.size(); i++) {
			manager.removeEmployeeTag(id, myTags.get(i));
		}
		String t = request.getParameter("tags");
		String [] tags = t.split(","); 
		for(int i = 0 ; i < tags.length; i++) {
			manager.addEmployeeTag(id, tags[i]);
		}
		
		String l = request.getParameter("languages");
		String [] languages = l.split(","); 
		List<Language> myLanguages = manager.getEmployeeLanguages(id);

		for(int i = 0 ; i < myLanguages.size(); i++) {
			manager.removeEmployeeLanguage(id, myLanguages.get(i).getLanguage());
		}
		
		for(int i = 0 ; i < languages.length; i++) {
			manager.addEmployeeLanguage(id, new Language(0, languages[i], "", ""));
		}
		
		Account account = (Account)request.getSession().getAttribute("account");
		Employee emp = manager.getEmployee(account.getID());
		EmployeeProfile profile = new EmployeeProfile(firstName, lastName, gender, sqlDate, majorProfession, "", email, phoneNumber, address, description, img, false);
		
		emp.setProfile(profile);
		manager.updateEmployee(emp);
		//request.getRequestDispatcher("/JSP/Settings-Info-User.jsp").forward(request, response);	
		//doGet(request, response);
	}

}
