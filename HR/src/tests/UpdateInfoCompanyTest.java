package tests;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import classes.Account;
import classes.Company;
import classes.CompanyProfile;
import classes.DBManager;
import servlets.UpdateInfoCompany;

/**
 * The Class UpdateInfoCompanyTest.
 */
class UpdateInfoCompanyTest extends Mockito {

	/**
	 * Test situation when Date is empty.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void dtIsEmpty() throws ServletException, IOException {
		// mocks classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		// mocks ServletContext
		final ServletContext servletContext = mock(ServletContext.class);
		UpdateInfoCompany serv = new UpdateInfoCompany() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// overrides methods 
		when(request.getParameter("name")).thenReturn("name");
		when(request.getParameter("description")).thenReturn("description");
		when(request.getParameter("essence")).thenReturn("essence");
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("image")).thenReturn("image");
		when(request.getParameter("phoneNumber")).thenReturn("phoneNumber");
		when(request.getParameter("address")).thenReturn("address");
		when(request.getParameter("date")).thenReturn("");
		when(request.getRequestDispatcher("/JSP/Settings-Info-Company.jsp")).thenReturn(dispatcher);

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(acc);
		when(acc.getID()).thenReturn(2);
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getCompany(2))
				.thenReturn(new Company(acc, new CompanyProfile(null, null, null, null, null, null, null, null)));

		// calls the servlet
		serv.doPost(request, response);

		Company expectation = new Company(acc,
				new CompanyProfile("name", "description", "essence", null, "image", "email", "phoneNumber", "address"));

		// checks if company profile contains correct parameters
		verify(manager).updateCompany(expectation);
		verify(dispatcher).forward(request, response);

	}

	/**
	 * Test situation when Date is not empty.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@Test
	void dtIsNotEmpty() throws ServletException, IOException, ParseException {
		// mocks classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		// mocks servletContext
		final ServletContext servletContext = mock(ServletContext.class);
		UpdateInfoCompany serv = new UpdateInfoCompany() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// overrides methods
		when(request.getParameter("name")).thenReturn("name");
		when(request.getParameter("description")).thenReturn("description");
		when(request.getParameter("essence")).thenReturn("essence");
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("image")).thenReturn("image");
		when(request.getParameter("phoneNumber")).thenReturn("phoneNumber");
		when(request.getParameter("address")).thenReturn("address");
		when(request.getParameter("date")).thenReturn("2013-09-18");
		when(request.getRequestDispatcher("/JSP/Settings-Info-Company.jsp")).thenReturn(dispatcher);

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(acc);
		when(acc.getID()).thenReturn(2);
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getCompany(2))
				.thenReturn(new Company(acc, new CompanyProfile(null, null, null, null, null, null, null, null)));

		// calls the servlet
		serv.doPost(request, response);

		java.util.Date utilDate = new java.util.Date();
		utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-18");
		java.sql.Date expectsqlDate = new java.sql.Date(utilDate.getTime());

		Company expectation = new Company(acc, new CompanyProfile("name", "description", "essence", expectsqlDate,
				"image", "email", "phoneNumber", "address"));

		// checks if company profile contains correct parameters
		verify(manager).updateCompany(expectation);
		verify(dispatcher).forward(request, response);

	}

}
