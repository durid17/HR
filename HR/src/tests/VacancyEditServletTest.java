package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import classes.Account;
import classes.DBManager;
import classes.Language;
import classes.Requirement;
import classes.Vacancy;
import servlets.VacancyEditServlet;

// TODO: Auto-generated Javadoc
/**
 * The Class VacancyEditServletTest.
 */
class VacancyEditServletTest extends Mockito{

	/**
	 * Date is empty.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void dateIsEmpty() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        final ServletContext servletContext = mock(ServletContext.class);
        VacancyEditServlet serv = new VacancyEditServlet(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        serv = spy(serv);
        
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(acc.getID()).thenReturn(2);
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        
        when(request.getParameter("heading")).thenReturn("heading");
        when(request.getParameter("profession")).thenReturn("profession");
        when(request.getParameter("position")).thenReturn("position");
        when(request.getParameter("description")).thenReturn("description");
        when(request.getParameter("jobType")).thenReturn("jobType");
        when(request.getParameter("endDate")).thenReturn("");
        when(request.getParameter("yearsOfExperience")).thenReturn("1");
        when(request.getParameter("jobType")).thenReturn("jobType");
        
        when(request.getParameter("degree")).thenReturn("degree");
        when(request.getParameter("location")).thenReturn("location");
        when(request.getParameter("languages")).thenReturn("lan1,lan2,lan3");
        when(request.getParameter("tags")).thenReturn("tag1,tag2,tag3");
        
        JSONObject jobj = new JSONObject();
		jobj.put("vacancyId", 0);
        when(request.getParameter("vacancyId")).thenReturn(jobj.toString());
        
        when(request.getParameter("qualification_1")).thenReturn("qualification_1");
        when(request.getParameter("qualification_2")).thenReturn("qualification_2");
        when(request.getParameter("qualification_3")).thenReturn("qualification_3");
        
        when(manager.getVacancyTags(0)).thenReturn(Arrays.asList("tg1", "tg2"));
        when(manager.getRequirementLanguages(0)).thenReturn(Arrays.asList(new Language(0, "l1", null, null), new Language(0, "l2", null, null)));
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        Requirement req = new Requirement("location", 1, "degree", "profession", "qualification_1", "qualification_2", "qualification_3");
        Vacancy vac = new Vacancy(0, "heading", "position", "description", "jobType", 2, req, new Date(0),  null);
        when(serv.factor(0, "heading", "position", "description", "jobType", 2, req, null)).thenReturn(vac);
        when(manager.addVacancy(vac)).thenReturn(vac);
        
        jobj = new JSONObject();
		jobj.put("vacancyId", 0);
        
        serv.doPost(request, response);
        
        verify(manager).addReqLanguage(0, "lan1");
        verify(manager).addReqLanguage(0, "lan2");
        verify(manager).addReqLanguage(0, "lan3");
        
        verify(manager).addVacancyTag(0, "tag1");
        verify(manager).addVacancyTag(0, "tag2");
        verify(manager).addVacancyTag(0, "tag3");
        
        verify(manager).removeVacancyTag(0, "tg1");
        verify(manager).removeVacancyTag(0, "tg2");
        
        verify(manager).removeReqLanguage(0, "l1");
        verify(manager).removeReqLanguage(0, "l2");
        
        writer.flush();
        assertTrue(stringWriter.toString().equals(jobj.toString())); 
	}
	
	/**
	 * Date is not empty.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@Test
	void dateIsNotEmpty() throws ServletException, IOException, ParseException {
		HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        DBManager manager = mock(DBManager.class);
        Account acc = mock(Account.class);
        
        final ServletContext servletContext = mock(ServletContext.class);
        VacancyEditServlet serv = new VacancyEditServlet(){
            public ServletContext getServletContext() {
                return servletContext;
            }
        };
        serv = spy(serv);
        
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(acc);
        when(acc.getID()).thenReturn(2);
        when(servletContext.getAttribute("DBManager")).thenReturn(manager);
        
        when(request.getParameter("heading")).thenReturn("heading");
        when(request.getParameter("profession")).thenReturn("profession");
        when(request.getParameter("position")).thenReturn("position");
        when(request.getParameter("description")).thenReturn("description");
        when(request.getParameter("jobType")).thenReturn("jobType");
        when(request.getParameter("endDate")).thenReturn("2013-09-18");
        when(request.getParameter("yearsOfExperience")).thenReturn("1");
        when(request.getParameter("jobType")).thenReturn("jobType");
        
        when(request.getParameter("degree")).thenReturn("degree");
        when(request.getParameter("location")).thenReturn("location");
        when(request.getParameter("languages")).thenReturn("lan1,lan2,lan3");
        when(request.getParameter("tags")).thenReturn("tag1,tag2,tag3");
        
        when(manager.getVacancyTags(0)).thenReturn(Arrays.asList("tg1", "tg2"));
        when(manager.getRequirementLanguages(0)).thenReturn(Arrays.asList(new Language(0, "l1", null, null), new Language(0, "l2", null, null)));
        
        JSONObject jobj = new JSONObject();
		jobj.put("vacancyId", 0);
        when(request.getParameter("vacancyId")).thenReturn(jobj.toString());
        
        when(request.getParameter("qualification_1")).thenReturn("qualification_1");
        when(request.getParameter("qualification_2")).thenReturn("qualification_2");
        when(request.getParameter("qualification_3")).thenReturn("qualification_3");
        
        
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
        
        java.util.Date utilDate=new java.util.Date();
		utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-18");
		java.sql.Date expectsqlDate = new java.sql.Date(utilDate.getTime());
        
        Requirement req = new Requirement("location", 1, "degree", "profession", "qualification_1", "qualification_2", "qualification_3");
        Vacancy vac = new Vacancy(0, "heading", "position", "description", "jobType", 2, req, new Date(0),  expectsqlDate);
        when(serv.factor(0, "heading", "position", "description", "jobType", 2, req, expectsqlDate)).thenReturn(vac);
        when(manager.addVacancy(vac)).thenReturn(vac);
        
        jobj = new JSONObject();
		jobj.put("vacancyId", 0);
        
        serv.doPost(request, response);
        
        verify(manager).addReqLanguage(0, "lan1");
        verify(manager).addReqLanguage(0, "lan2");
        verify(manager).addReqLanguage(0, "lan3");
        
        verify(manager).addVacancyTag(0, "tag1");
        verify(manager).addVacancyTag(0, "tag2");
        verify(manager).addVacancyTag(0, "tag3");
        
        verify(manager).removeVacancyTag(0, "tg1");
        verify(manager).removeVacancyTag(0, "tg2");
        
        verify(manager).removeReqLanguage(0, "l1");
        verify(manager).removeReqLanguage(0, "l2");
        
        writer.flush();
        assertTrue(stringWriter.toString().equals(jobj.toString())); 
	}
	

}
