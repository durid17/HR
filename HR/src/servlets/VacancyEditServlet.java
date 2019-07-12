package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import classes.Account;
import classes.DBManager;
import classes.Language;
import classes.Requirement;
import classes.Vacancy;

/**
 * Servlet implementation class VacancyEditServlet
 */
@WebServlet("/VacancyEditServlet")
public class VacancyEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacancyEditServlet() {
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
	
	public Vacancy factor(int id, String heading, String position, String description, String jobType, int companyId, Requirement req, Date sqlDate) {
		return new Vacancy(id, heading, position, description, jobType, companyId, req,
				new Date(System.currentTimeMillis()),  sqlDate);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		JSONObject jsonid = new JSONObject(request.getParameter("vacancyId"));
		int id = Integer.parseInt(jsonid.get("vacancyId").toString());
		String qualification1 = request.getParameter("qualification_1");
		String qualification2 = request.getParameter("qualification_2");
		String qualification3 = request.getParameter("qualification_3");
		
		String [] languages = l.split(","); 
		String [] tags = t.split(","); 
		Requirement req = new Requirement(location, yearsOfExp, degree, prof, qualification1, qualification2, qualification3);
		Date sqlDate = null;
		try {
			if(dt != null) {
				java.util.Date utilDate = new java.util.Date();
				utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(dt);
				sqlDate = new java.sql.Date(utilDate.getTime());
			
			} 
			
		} catch (ParseException e) {
			
		}
		
		Account company = (Account) request.getSession().getAttribute("account");
		int companyId = company.getID();
		
		Vacancy vacancy = factor(id, heading, position, description, jobType, companyId, req, sqlDate);
		manager.updateVacancy(vacancy);
		List<String> myTags = manager.getVacancyTags(vacancy.getId());
		List<Language> myLanguages = manager.getRequirementLanguages(vacancy.getId());
		
		for(int i = 0 ; i < myTags.size(); i++) {
			manager.removeVacancyTag(vacancy.getId(), myTags.get(i));
		}
		
		for(int i = 0 ; i < myLanguages.size(); i++) {
			manager.removeReqLanguage(vacancy.getId(), myLanguages.get(i).getLanguage());
		}
		
		for(int i = 0 ; i < languages.length; i++) {
			manager.addReqLanguage(vacancy.getId(), languages[i]);
		}
		
		for(int i = 0 ; i < tags.length; i++) {
			manager.addVacancyTag(vacancy.getId(), tags[i]);
		}
		
		
		JSONObject jobj = new JSONObject();
		jobj.put("vacancyId", vacancy.getId());
		response.getWriter().write(jobj.toString());
	}

}
