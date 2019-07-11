package tests;

import static org.junit.jupiter.api.Assertions.*;

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
import classes.DBManager;
import classes.EmployeeEducation;
import servlets.AddEducation;
import servlets.LoginServlet;

class AddEducationTest extends Mockito {

	@Test
	void bothNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        final ServletContext servletContext = mock(ServletContext.class);
        AddEducation serv = new AddEducation(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
        when(request.getParameter("institution")).thenReturn("institution");
        when(request.getParameter("start")).thenReturn(null);
        when(request.getParameter("end")).thenReturn(null);
        when(request.getParameter("major")).thenReturn("major");
        when(request.getParameter("minor")).thenReturn("minor");
        when(request.getParameter("degree")).thenReturn("degree");
        
        when(request.getRequestDispatcher("/JSP/AddEducation.jsp")).thenReturn(dispatcher);
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(acc.getID()).thenReturn(1);
        
        serv.doPost(request, response);
        EmployeeEducation testEd = new EmployeeEducation(1, null, null, "","institution", "major","minor", "degree", 0);
        verify(manager).addEducation(1, testEd);
        verify(dispatcher).forward(request,response);     
	}

	
	@Test
	void bothNotNull() throws ServletException, IOException, ParseException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        final ServletContext servletContext = mock(ServletContext.class);
        AddEducation serv = new AddEducation(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
        when(request.getParameter("institution")).thenReturn("institution");
        when(request.getParameter("start")).thenReturn("2013-09-18");
        when(request.getParameter("end")).thenReturn("2015-09-18");
        when(request.getParameter("major")).thenReturn("major");
        when(request.getParameter("minor")).thenReturn("minor");
        when(request.getParameter("degree")).thenReturn("degree");
        
        when(request.getRequestDispatcher("/JSP/AddEducation.jsp")).thenReturn(dispatcher);
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(acc.getID()).thenReturn(1);
        
        
        java.sql.Date sqlStart = null;
		java.sql.Date sqlEnd = null;
		
        java.util.Date utilDate=new java.util.Date();
		utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-18");
		sqlStart = new java.sql.Date(utilDate.getTime());
		
		utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2015-09-18");
		sqlEnd = new java.sql.Date(utilDate.getTime());
		
        serv.doPost(request, response);
        EmployeeEducation testEd = new EmployeeEducation(1, sqlStart, sqlEnd, "","institution", "major","minor", "degree", 0);
        verify(manager).addEducation(1, testEd);
        verify(dispatcher).forward(request,response);     
	}
}
