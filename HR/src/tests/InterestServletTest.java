package tests;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.mockito.Mockito;
import org.mockito.Mock;

import classes.Account;
import classes.DBManager;
import classes.Hash;
import servlets.InterestServlet;
import servlets.LoginServlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class InterestServletTest extends Mockito {

	@Test
	void removeInterest() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);
		
		final ServletContext servletContext = mock(ServletContext.class);
        InterestServlet serv = new InterestServlet(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(acc.getID()).thenReturn(1);
        when(request.getParameter("vacancyId")).thenReturn("2");
        when(manager.hasApplied(1, 2)).thenReturn(true);
        
		serv.doPost(request, response);
		verify(manager).removeApplication(1, 2);
	}
	
	@Test
	void addInterest() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);
		
		final ServletContext servletContext = mock(ServletContext.class);
        InterestServlet serv = new InterestServlet(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(acc.getID()).thenReturn(1);
        when(request.getParameter("vacancyId")).thenReturn("2");
        when(manager.hasApplied(1, 2)).thenReturn(false);
        
		serv.doPost(request, response);
		verify(manager).addApplication(1, 2);
	}
	
	

}
