package tests;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import servlets.logOut;

// TODO: Auto-generated Javadoc
/**
 * The Class logOutTest.
 */
class logOutTest extends Mockito {

	/**
	 * This test checks if session is null after this servlet call;
	 * 
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void setSessionNull() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        logOut serv = new logOut();
     
        // override methods
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
        
        // call the servlet and check if session is null
        serv.doGet(request, response);
        verify(session).setAttribute("account", null);
        verify(response).sendRedirect("/JSP/MainPage.jsp");    
	}

}
