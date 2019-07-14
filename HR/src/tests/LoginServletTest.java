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
import classes.Hash;
import servlets.LoginServlet;


/**
 * The Class LoginServletTest.
 */
class LoginServletTest extends Mockito {

	/**
	 * This test checks situation when session is null.
	 * If user is forwarded to LogInPage
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void doGetSessionNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(null);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/JSP/LogInPage.jsp")).thenReturn(dispatcher);

		LoginServlet serv = new LoginServlet();

		serv.doGet(request, response);
		verify(dispatcher).forward(request, response);

	}

	/**
	 * This test checks situation when session is not null.
	 * method checks if user is forwarded to MainPage
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void doGetSessionIsNotNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(new Account(0, null, "1", "1", "1"));
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		when(request.getRequestDispatcher("/JSP/MainPage.jsp")).thenReturn(dispatcher);

		LoginServlet serv = new LoginServlet();

		serv.doGet(request, response);
		verify(dispatcher).forward(request, response);
	}

	/**
	 * Check if Account is null.
	 * after this call attribute "wrong" should be true.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void accountNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		DBManager manager = mock(DBManager.class);

		final ServletContext servletContext = mock(ServletContext.class);
		LoginServlet serv = new LoginServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		when(request.getParameter("username")).thenReturn("Deme");
		when(request.getParameter("password")).thenReturn("Deme");
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getAccount("Deme")).thenReturn(null);
		when(request.getRequestDispatcher("/JSP/LogInPage.jsp")).thenReturn(dispatcher);

		serv.doPost(request, response);
		verify(dispatcher).forward(request, response);
		verify(request).setAttribute("wrong", true);
	}

	/**
	 * when password is incorrect.
	 * after this call attribute "wrong" should be true.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void incorectPassword() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher dispatcher = mock(RequestDispatcher.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		final ServletContext servletContext = mock(ServletContext.class);
		LoginServlet serv = new LoginServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		when(request.getParameter("username")).thenReturn("Deme");
		when(request.getParameter("password")).thenReturn("Deme");
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getAccount("Deme")).thenReturn(acc);
		when(acc.getPassHash()).thenReturn("d");
		when(request.getRequestDispatcher("/JSP/LogInPage.jsp")).thenReturn(dispatcher);

		serv.doPost(request, response);
		verify(dispatcher).forward(request, response);
		verify(request).setAttribute("wrong", true);

	}

	/**
	 * Situation when password is correct and user is company.
	 * checks if after this call, user is forwarded to CompanyProfile page.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void correctPasswordCompany() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		final ServletContext servletContext = mock(ServletContext.class);
		LoginServlet serv = new LoginServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		when(request.getParameter("username")).thenReturn("Deme");
		when(request.getParameter("password")).thenReturn("Deme");
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getAccount("Deme")).thenReturn(acc);
		when(acc.getPassHash()).thenReturn(Hash.getHash("Deme"));
		when(request.getSession()).thenReturn(session);
		when(acc.getAccountType()).thenReturn(Account.COMPANY_ACCOUNT_TYPE);
		when(request.getContextPath()).thenReturn("a");

		serv.doPost(request, response);
		verify(response).sendRedirect("a/JSP/CompanyProfile.jsp");
		verify(session).setAttribute("account", acc);

	}

	/**
	 * Situation when password is correct and user is client.
	 * checks if after this call, user is forwarded to UserProfile page.
	 * 
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void correctPasswordClient() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		final ServletContext servletContext = mock(ServletContext.class);
		LoginServlet serv = new LoginServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		when(request.getParameter("username")).thenReturn("Deme");
		when(request.getParameter("password")).thenReturn("Deme");
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getAccount("Deme")).thenReturn(acc);
		when(acc.getPassHash()).thenReturn(Hash.getHash("Deme"));
		when(request.getSession()).thenReturn(session);
		when(acc.getAccountType()).thenReturn(Account.EMPLOYEE_ACCOUNT_TYPE);
		when(request.getContextPath()).thenReturn("a");

		serv.doPost(request, response);
		verify(response).sendRedirect("a/JSP/UserProfile.jsp");
		verify(session).setAttribute("account", acc);
	}

	/**
	 * Correct password else.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void correctPasswordElse() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		final ServletContext servletContext = mock(ServletContext.class);
		LoginServlet serv = new LoginServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		when(request.getParameter("username")).thenReturn("Deme");
		when(request.getParameter("password")).thenReturn("Deme");
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getAccount("Deme")).thenReturn(acc);
		when(acc.getPassHash()).thenReturn(Hash.getHash("Deme"));
		when(request.getSession()).thenReturn(session);
		when(acc.getAccountType()).thenReturn("noone");
		when(request.getContextPath()).thenReturn("a");

		serv.doPost(request, response);
		verify(session).setAttribute("account", acc);

	}

}
