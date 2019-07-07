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
 * Servlet implementation class VacanciesServlet
 */
@WebServlet("/VacanciesServlet")
public class VacanciesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VacanciesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager)getServletContext().getAttribute("DBManager");
		Account acc = (Account)request.getSession().getAttribute("account");
		
		List<Vacancy> vacancies = manager.getVacancies();
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
		System.out.println(request.getParameter("jobs_type"));
	}

}
