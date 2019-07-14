package tests;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import classes.Account;
import classes.DBManager;
import servlets.CompanyRegister;

/**
 * The Class CompanyRegisterTest.
 */
class CompanyRegisterTest extends Mockito {

	/**
	 * Method doGet. situation when session is null.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void doGetSessionNull() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		// override methods
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(null);
		when(request.getRequestDispatcher("/JSP/CompanyRegister.jsp")).thenReturn(dispatcher);

		// call servlet and check if forwarded correctly
		CompanyRegister serv = new CompanyRegister();
		serv.doGet(request, response);
		verify(dispatcher).forward(request, response);
	}

	/**
	 * Method doGet. situation when session is not null.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void doGetSessionIsNotNull() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		// override methods
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(new Account(0, null, "1", "1", "1"));
		when(request.getRequestDispatcher("/JSP/MainPage.jsp")).thenReturn(dispatcher);

		// call servlet and check if forwarded correctly
		CompanyRegister serv = new CompanyRegister();
		serv.doGet(request, response);
		verify(dispatcher).forward(request, response);
	}

	/**
	 * Gets the account null.
	 *
	 * @return the account null
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void getAccountNull() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);

		// override methods
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(new Account(0, null, "1", "1", "1"));
		when(request.getRequestDispatcher("/JSP/MainPage.jsp")).thenReturn(dispatcher);

		// call servlet and check if forwarded correctly
		CompanyRegister serv = new CompanyRegister();
		serv.doGet(request, response);
		verify(dispatcher).forward(request, response);
	}

	/**
	 * Situation when Account exists.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void accountExists() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		DBManager manager = mock(DBManager.class);

		// mock servletContext
		final ServletContext servletContext = mock(ServletContext.class);
		CompanyRegister serv = new CompanyRegister() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// override methods
		when(request.getParameter("username")).thenReturn("abba");
		when(request.getParameter("psw")).thenReturn("psw");
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getAccount("abba")).thenReturn(new Account(1, null, null, null, null));
		when(request.getRequestDispatcher("JSP/CompanyRegister.jsp")).thenReturn(dispatcher);

		//call servlet and check if used attribute is set true.
		serv.doPost(request, response);
		verify(request).setAttribute("used", true);
		verify(dispatcher).forward(request, response);
	}

	/**
	 * Account does not exist.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void accountDoesNotExist() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);

		// mock servletContext
		final ServletContext servletContext = mock(ServletContext.class);
		CompanyRegister serv = new CompanyRegister() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// override methods
		when(request.getSession()).thenReturn(session);
		when(request.getParameter("userName")).thenReturn("abba");
		when(request.getParameter("psw")).thenReturn("psw");
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getAccount("abba")).thenReturn(null);
		when(request.getContextPath()).thenReturn("a");

		//call servlet and check if methods called with correct parameters.
		serv.doPost(request, response);
		verify(request, atLeast(1)).getParameter("email");
		verify(request, atLeast(1)).getParameter("companyName");
		verify(response).sendRedirect("a/JSP/CompanyProfile.jsp");
	}

}
