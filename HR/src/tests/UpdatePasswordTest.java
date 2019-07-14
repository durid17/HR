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
import servlets.UpdatePassword;

/**
 * The Class UpdatePasswordTest.
 */
class UpdatePasswordTest extends Mockito {

	/**
	 * This method checks if Password updated correctly.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void passwordUpdated() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
	    HttpServletResponse response = mock(HttpServletResponse.class);
	    HttpSession session = mock(HttpSession.class);
	    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        // mock servlet context
        final ServletContext servletContext = mock(ServletContext.class);
        UpdatePassword serv = new UpdatePassword(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        
        // override return statements
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("newPassword")).thenReturn("hola");
        when(session.getAttribute("account")).thenReturn(acc);
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        when(request.getRequestDispatcher("/JSP/Settings.jsp")).thenReturn(dispatcher);
        
        // call servlet and check if new password is correct 
        serv.doPost(request, response);
        verify(acc).setPassHash(Hash.getHash("hola"));
        verify(manager).updateAccount(acc);
        verify(dispatcher).forward(request,response);
	}

}
