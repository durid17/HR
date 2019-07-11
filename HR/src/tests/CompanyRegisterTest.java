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
import servlets.ClientRegister;
import servlets.CompanyRegister;

class CompanyRegisterTest extends Mockito {

	@Test
	void doGetSessionNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(null);
        when(request.getRequestDispatcher("/JSP/CompanyRegister.jsp")).thenReturn(dispatcher);

        
        CompanyRegister serv = new CompanyRegister();
		serv.doGet(request, response);
		verify(dispatcher).forward(request,response);
	}
	
	@Test
	void doGetSessionIsNotNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(new Account(0, null, "1", "1", "1"));
        when(request.getRequestDispatcher("/JSP/MainPage.jsp")).thenReturn(dispatcher);
        
        CompanyRegister serv = new CompanyRegister();
		serv.doGet(request, response);
		verify(dispatcher).forward(request,response);
	}
	
	@Test
	void getAccountNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(new Account(0, null, "1", "1", "1"));
        when(request.getRequestDispatcher("/JSP/MainPage.jsp")).thenReturn(dispatcher);
        
        CompanyRegister serv = new CompanyRegister();
		serv.doGet(request, response);
		verify(dispatcher).forward(request,response);
	}
	
	@Test
	void accountExists() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        DBManager manager = mock(DBManager.class);
     
        final ServletContext servletContext = mock(ServletContext.class);
        CompanyRegister serv = new CompanyRegister(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
        when(request.getParameter("username")).thenReturn("abba");
        when(request.getParameter("psw")).thenReturn("psw");
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(manager.getAccount("abba")).thenReturn(new Account(1, null, null, null, null));
        when(request.getRequestDispatcher("JSP/CompanyRegister.jsp")).thenReturn(dispatcher);
        
        serv.doPost(request, response);
        verify(request).setAttribute("used", true);
        verify(dispatcher).forward(request,response);    
	}
	
	@Test
	void accountDoesNotExist() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
     
        final ServletContext servletContext = mock(ServletContext.class);
        CompanyRegister serv = new CompanyRegister(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("userName")).thenReturn("abba");
        when(request.getParameter("psw")).thenReturn("psw");
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(manager.getAccount("abba")).thenReturn(null);
        when(request.getContextPath()).thenReturn("a");

        serv.doPost(request, response);
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("companyName");
        verify(response).sendRedirect("a/JSP/CompanyProfile.jsp"); 
	}


}
