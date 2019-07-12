package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

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
import classes.Employee;
import classes.EmployeeProfile;
import classes.Language;
import servlets.UpdateInfo;
import servlets.UpdateInfoCompany;

class UpdateInfoCompanyTest extends Mockito{

	@Test
	void dtIsEmpty() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        final ServletContext servletContext = mock(ServletContext.class);
        UpdateInfoCompany serv = new UpdateInfoCompany(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
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
        when(manager.getCompany(2)).thenReturn(new Company(acc, new CompanyProfile(null, null, null, null, null, null, null, null)));
        
        serv.doPost(request, response);
        
        Company expectation = new Company(acc, new CompanyProfile("name", "description", "essence", null, 
        		"image", "email",  "phoneNumber", "address"));
        
        verify(manager).updateCompany(expectation);
        verify(dispatcher).forward(request,response);
        
	}
	
	
	@Test
	void dtIsNotEmpty() throws ServletException, IOException, ParseException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        final ServletContext servletContext = mock(ServletContext.class);
        UpdateInfoCompany serv = new UpdateInfoCompany(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
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
        when(manager.getCompany(2)).thenReturn(new Company(acc, new CompanyProfile(null, null, null, null, null, null, null, null)));
        
        serv.doPost(request, response);
        
        
        java.util.Date utilDate=new java.util.Date();
		utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-18");
		java.sql.Date expectsqlDate = new java.sql.Date(utilDate.getTime());
		
        Company expectation = new Company(acc, new CompanyProfile("name", "description", "essence", expectsqlDate, 
        		"image", "email",  "phoneNumber", "address"));
        
        verify(manager).updateCompany(expectation);
        verify(dispatcher).forward(request,response);
        
	}

}
