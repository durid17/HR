package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import java.text.ParseException;

import classes.*;

/**
 * Servlet implementation class VacancyAddServlet
 */
@WebServlet("/VacancyAddServlet")
public class VacancyAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacancyAddServlet() {
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
		String heading = request.getParameter("heading");
		String prof = request.getParameter("profession");
		String position = request.getParameter("position");
		String description = request.getParameter("description");
		String jobType = request.getParameter("jobType");
		String dt = request.getParameter("endDate");
		String years = request.getParameter("yearsOfExperience");
		if(years == "") years = "0"; 
		int yearsOfExp = Integer.parseInt(years);
		String degree = request.getParameter("degree");
		String location = request.getParameter("location");
		String l = request.getParameter("languages");
		String t = request.getParameter("tags");
		
		String [] languages = l.split(","); 
		String [] tags = t.split(","); 
		Requirement req = new Requirement(location, yearsOfExp, degree, prof);
		Date sqlDate = null;
		try {
			if(dt != null) {
				java.util.Date utilDate = new java.util.Date();
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
				sqlDate = new java.sql.Date(utilDate.getTime());
			
			} else {
				System.out.println("Date carielia!");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Account company = (Account) request.getSession().getAttribute("account");
		int companyId = 8;
		if(company != null) companyId = company.getID();
		Vacancy vacancy = new Vacancy(0, heading, position, description, jobType, companyId, req,
					new Date(System.currentTimeMillis()) ,  sqlDate);
		vacancy = manager.addVacancy(vacancy);
		for(int i = 0 ; i < languages.length; i++) {
			manager.addReqLanguage(vacancy.getId(), languages[i]);
		}
		for(int i = 0 ; i < tags.length; i++) {
			manager.addVacancyTag(vacancy.getId(), tags[i]);
		}
		
		
		JSONObject jobj = new JSONObject();
		jobj.put("vacancyId", vacancy.getId());
		//System.out.println(jobj.toString());
		response.getWriter().write(jobj.toString());
	}

}
