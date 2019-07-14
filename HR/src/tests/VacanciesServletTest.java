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
import servlets.VacanciesServlet;

/**
 * The Class VacanciesServletTest.
 */
class VacanciesServletTest extends Mockito {

	/**
	 * This test Checks parameters.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void checkParameters() throws ServletException, IOException {
		// mocks classes
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        // mock servletContext
        final ServletContext servletContext = mock(ServletContext.class);
        VacanciesServlet serv = new VacanciesServlet(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
        // override return methods
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(request.getRequestDispatcher("JSP/VacancyCart.jsp")).thenReturn(dispatcher);
        when(acc.getID()).thenReturn(1);
        
        // call servlet and check if parameters is correct
        serv.doGet(request, response);
        verify(manager).getVacancies();
        verify(manager).getCompanies();
        verify(manager).getProfessions();
        verify(manager).getLocations();
        verify(manager).getTags();
        verify(manager).getDegrees();
        verify(request).setAttribute("employeeId", 1);
        verify(dispatcher).forward(request,response);  
	}
}
