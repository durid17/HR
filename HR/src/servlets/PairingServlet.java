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
import classes.Pairing;
import classes.Vacancy;

/**
 * Servlet implementation class PairingServlet
 */
@WebServlet("/PairingServlet")
public class PairingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PairingServlet() {
		super();
	}

	/**
	 * Factor.
	 *
	 * @param manager the manager
	 * @return the pairing
	 * creating new Pairing object
	 */
	public Pairing factor(DBManager manager) {
		return new Pairing(manager);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *     	This Servlet is called when user wants program to sort vacancies.
	 *     	Sets parameters required in VacancyCart.jsp.
	 *     	Redirects to VacancyCart.jsp.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBManager manager = (DBManager) getServletContext().getAttribute("DBManager");
		Account acc = (Account) request.getSession().getAttribute("account");
		Pairing pair = factor(manager);

		List<Vacancy> vacancies = pair.getVacancies(acc.getID());
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
