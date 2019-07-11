package tests;

import static org.junit.jupiter.api.Assertions.*;

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
import classes.Pairing;
import servlets.PairingServlet;
import servlets.VacanciesServlet;

class PairingServletTest extends Mockito{
	
	@Test
	void checkParameters() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        Pairing pair = mock(Pairing.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        final ServletContext servletContext = mock(ServletContext.class);
        PairingServlet serv = new PairingServlet(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
        serv = spy(serv);
           
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(serv.factor(manager)).thenReturn(pair);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(request.getRequestDispatcher("JSP/VacancyCart.jsp")).thenReturn(dispatcher);
        when(acc.getID()).thenReturn(1);
        when(pair.getVacancies(1)).thenReturn(null);
        
        serv.doGet(request, response);
        verify(pair).getVacancies(1);
        verify(manager).getCompanies();
        verify(manager).getProfessions();
        verify(manager).getLocations();
        verify(manager).getTags();
        verify(manager).getDegrees();
        verify(request).setAttribute("employeeId", 1);
        verify(dispatcher).forward(request,response);  
	}
}
