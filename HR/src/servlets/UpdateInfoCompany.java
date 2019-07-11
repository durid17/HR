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
import classes.Company;
import classes.CompanyProfile;
import classes.Hash;

/**
 * Servlet implementation class UpdateInfo
 */
@WebServlet("/UpdateInfoCompany")
public class UpdateInfoCompany extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoCompany() {
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
		String name =  request.getParameter("name");
		String description =  request.getParameter("description"); 
		String email =  request.getParameter("email");
		String img =  request.getParameter("image");
		String phoneNumber =  request.getParameter("phoneNumber");
		String address =  request.getParameter("address");
		
		String dt =request.getParameter("date");
		java.sql.Date sqlDate = null;
		
		try {
			if(! dt.equals("")) {
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
		
		Account account = (Account)request.getSession().getAttribute("account");
		DBManager manager = (DBManager)getServletContext().getAttribute("DBManager");
		Company company = manager.getCompany(account.getID());
		CompanyProfile profile = new CompanyProfile(name, description, sqlDate, img, email, phoneNumber, address);

		company.setProfile(profile);
		manager.updateCompany(company);	
		
		request.getRequestDispatcher("/JSP/Settings-Info-Company.jsp").forward(request, response);	
		//doGet(request, response);
	}

}
