package tests;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import classes.Account;
import classes.DBManager;
import servlets.InterestServlet;

/**
 * The Class InterestServletTest.
 */
class InterestServletTest extends Mockito {

	/**
	 * Removes the interest.
	 *	
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void removeInterest() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		// mock servletContext
		final ServletContext servletContext = mock(ServletContext.class);
		InterestServlet serv = new InterestServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// override methods
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(acc);
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(acc.getID()).thenReturn(1);
		when(request.getParameter("vacancyId")).thenReturn("2");
		when(manager.hasApplied(1, 2)).thenReturn(true);

		// call the servlet and check if removeApplication is called with correct parameters
		serv.doPost(request, response);
		verify(manager).removeApplication(1, 2);
	}

	/**
	 * Adds the interest.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void addInterest() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		// mock servletContext
		final ServletContext servletContext = mock(ServletContext.class);
		InterestServlet serv = new InterestServlet() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// override methods
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(acc);
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(acc.getID()).thenReturn(1);
		when(request.getParameter("vacancyId")).thenReturn("2");
		when(manager.hasApplied(1, 2)).thenReturn(false);

		// call the servlet and check if addApplication is called with correct parameters
		serv.doPost(request, response);
		verify(manager).addApplication(1, 2);
	}

}
