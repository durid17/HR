package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Account;
import classes.Company;
import classes.DBManager;
import classes.Vacancy;

/**
 * Servlet implementation class FilterServlet
 */
@WebServlet("/FilterServlet")
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager)getServletContext().getAttribute("DBManager");
		Account acc = (Account)request.getSession().getAttribute("account");
		
		String chosenProfessions = request.getParameter("professions");
		String chosenCompanies = request.getParameter("companies");
		String chosenLocations = request.getParameter("locations");
		String chosenTags = request.getParameter("tags");
		String chosen_jobs_type = request.getParameter("jobs_type");
		String chosenDegree = request.getParameter("degree");
				
		List<Vacancy> vacancies = manager.getFilterVacancies(chosenProfessions, chosenCompanies, 
				chosenLocations, chosenTags, chosen_jobs_type, chosenDegree);
		List<Company> companies = manager.getCompanies();
		List<String> professions = manager.getProfessions();
		List<String> locations = manager.getLocations();
		List<String> tags = manager.getTags();
		List<String> degrees = manager.getDegrees();
		
		
		request.setAttribute("vacancies", vacancies);
		request.setAttribute("companies", companies);
		request.setAttribute("professions", professions);
		request.setAttribute("locations", locations);
		request.setAttribute("tags", tags);
		request.setAttribute("degrees", degrees);
		request.setAttribute("employeeId", acc.getID());
		request.getRequestDispatcher("JSP/VacancyCart.jsp").forward(request, response);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
