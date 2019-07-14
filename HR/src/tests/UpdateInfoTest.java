package tests;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import classes.Account;
import classes.DBManager;
import classes.Employee;
import classes.EmployeeProfile;
import classes.Language;
import servlets.UpdateInfo;


/**
 * The Class UpdateInfoTest.
 */
class UpdateInfoTest extends Mockito {

	/**
	 * Tests situation when Date is empty.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void dtIsEmpty() throws ServletException, IOException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		// mock servletContext
		final ServletContext servletContext = mock(ServletContext.class);
		UpdateInfo serv = new UpdateInfo() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// override return methods
		when(request.getParameter("firstName")).thenReturn("firstName");
		when(request.getParameter("lastName")).thenReturn("lastName");
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("image")).thenReturn("image");
		when(request.getParameter("majorProfession")).thenReturn("majorProfession");
		when(request.getParameter("gender")).thenReturn("gender");
		when(request.getParameter("phoneNumber")).thenReturn("phoneNumber");
		when(request.getParameter("address")).thenReturn("address");
		when(request.getParameter("profession")).thenReturn("profession");
		when(request.getParameter("description")).thenReturn("description");
		when(request.getParameter("bday")).thenReturn("");
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("tags")).thenReturn("tags1,tags2,tags3");
		when(request.getParameter("languages")).thenReturn("lang1,lang2,lang3");

		when(request.getSession()).thenReturn(session);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(acc);
		when(acc.getID()).thenReturn(2);
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getEmployeeTags(1)).thenReturn(Arrays.asList("a", "b", "c"));
		when(manager.getEmployeeLanguages(1))
				.thenReturn(Arrays.asList(new Language(0, "l1", null, null), new Language(0, "l2", null, null)));
		when(manager.getEmployee(2)).thenReturn(new Employee(acc,
				new EmployeeProfile(null, null, null, null, null, null, null, null, null, null, null, false)));

		// call the servlet
		serv.doPost(request, response);

		// check if method called with correct parameters
		verify(manager).removeEmployeeTag(1, "a");
		verify(manager).removeEmployeeTag(1, "b");
		verify(manager).removeEmployeeTag(1, "c");

		verify(manager).addEmployeeTag(1, "tags1");
		verify(manager).addEmployeeTag(1, "tags2");
		verify(manager).addEmployeeTag(1, "tags3");

		verify(manager).removeEmployeeLanguage(1, "l1");
		verify(manager).removeEmployeeLanguage(1, "l2");

		verify(manager).addEmployeeLanguage(1, new Language(0, "lang1", "", ""));
		verify(manager).addEmployeeLanguage(1, new Language(0, "lang2", "", ""));
		verify(manager).addEmployeeLanguage(1, new Language(0, "lang3", "", ""));

		Employee expectation = new Employee(acc, new EmployeeProfile("firstName", "lastName", "gender", null,
				"majorProfession", "", "email", "phoneNumber", "address", "description", "image", false));
		verify(manager).updateEmployee(expectation);
	}

	/**
	 * Tests situation when Date is not empty.
	 *
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 */
	@Test
	void dtIsNotEmpty() throws ServletException, IOException, ParseException {
		// mock classes
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		DBManager manager = mock(DBManager.class);
		Account acc = mock(Account.class);

		// mock serlvetContext
		final ServletContext servletContext = mock(ServletContext.class);
		UpdateInfo serv = new UpdateInfo() {
			public ServletContext getServletContext() {
				return servletContext;
			}
		};

		// override methods return statements
		when(request.getParameter("firstName")).thenReturn("firstName");
		when(request.getParameter("lastName")).thenReturn("lastName");
		when(request.getParameter("email")).thenReturn("email");
		when(request.getParameter("image")).thenReturn("image");
		when(request.getParameter("majorProfession")).thenReturn("majorProfession");
		when(request.getParameter("gender")).thenReturn("gender");
		when(request.getParameter("phoneNumber")).thenReturn("phoneNumber");
		when(request.getParameter("address")).thenReturn("address");
		when(request.getParameter("profession")).thenReturn("profession");
		when(request.getParameter("description")).thenReturn("description");
		when(request.getParameter("bday")).thenReturn("2013-09-18");
		when(request.getParameter("id")).thenReturn("1");
		when(request.getParameter("tags")).thenReturn("tags1,tags2,tags3");
		when(request.getParameter("languages")).thenReturn("lang1,lang2,lang3");

		when(request.getSession()).thenReturn(session);
		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("account")).thenReturn(acc);
		when(acc.getID()).thenReturn(2);
		when(servletContext.getAttribute("DBManager")).thenReturn(manager);
		when(manager.getEmployeeTags(1)).thenReturn(Arrays.asList("a", "b", "c"));
		when(manager.getEmployeeLanguages(1))
				.thenReturn(Arrays.asList(new Language(0, "l1", null, null), new Language(0, "l2", null, null)));
		when(manager.getEmployee(2)).thenReturn(new Employee(acc,
				new EmployeeProfile(null, null, null, null, null, null, null, null, null, null, null, false)));

		// call the servlet
		serv.doPost(request, response);

		// check if methods called with correct
		verify(manager).removeEmployeeTag(1, "a");
		verify(manager).removeEmployeeTag(1, "b");
		verify(manager).removeEmployeeTag(1, "c");

		verify(manager).addEmployeeTag(1, "tags1");
		verify(manager).addEmployeeTag(1, "tags2");
		verify(manager).addEmployeeTag(1, "tags3");

		verify(manager).removeEmployeeLanguage(1, "l1");
		verify(manager).removeEmployeeLanguage(1, "l2");

		verify(manager).addEmployeeLanguage(1, new Language(0, "lang1", "", ""));
		verify(manager).addEmployeeLanguage(1, new Language(0, "lang2", "", ""));
		verify(manager).addEmployeeLanguage(1, new Language(0, "lang3", "", ""));

		java.util.Date utilDate = new java.util.Date();
		utilDate = new SimpleDateFormat("yyyy-MM-dd").parse("2013-09-18");
		java.sql.Date expectsqlDate = new java.sql.Date(utilDate.getTime());

		Employee expectation = new Employee(acc, new EmployeeProfile("firstName", "lastName", "gender", expectsqlDate,
				"majorProfession", "", "email", "phoneNumber", "address", "description", "image", false));
		verify(manager).updateEmployee(expectation);
	}

}
