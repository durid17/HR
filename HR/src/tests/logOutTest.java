package tests;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import servlets.logOut;

class logOutTest extends Mockito {

	@Test
	void setSessionNull() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        logOut serv = new logOut();
     
        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn("");
        
        serv.doGet(request, response);
        verify(session).setAttribute("account", null);
        verify(response).sendRedirect("/JSP/MainPage.jsp");    
	}

}
