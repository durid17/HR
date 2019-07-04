package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.Account;
import classes.Company;
import classes.CompanyProfile;
import classes.DBConnection;
import classes.DBManager;
import classes.Employee;
import classes.EmployeeProfile;
import classes.Requirement;
import classes.Vacancy;

class DBManagerTests {
	DBManager manager = new DBManager(DBConnection.getCon());
	
	@BeforeEach
	void setUp() throws Exception {
//		String scriptFilePath = "src\\hr.sql";
//		BufferedReader reader = null;
//		Connection con = null;
//		Statement statement = null;
//		try {
//			con = DBConnection.getCon();
//			// create statement object
//			statement = con.createStatement();
//			// initialize file reader
//			reader = new BufferedReader(new FileReader(scriptFilePath));
//			String line = null;
//			// read script line by line
//			while (true) {
//				line = reader.readLine();
//				if (line == null)
//					break;
//				// execute query
//				statement.execute(line);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// close file reader
//			if (reader != null) {
//				reader.close();
//			}
//		}
	}
	
	@Test
	void accountTest() {
		Account account = new Account(0, new Date(System.currentTimeMillis()), "testUser", "password", Account.EMPLOYEE_ACCOUNT_TYPE);
		account = manager.addAccount(account);
		
		Account fromDBAccount = manager.getAccount(account.getUsername());
		assertEquals(account, fromDBAccount);

		account.setPassHash("newPassword");
		manager.updateAccount(account);
		fromDBAccount = manager.getAccount(account.getUsername());
		assertEquals(account, fromDBAccount);
		
		manager.deleteAccount(account);
	}
	
	@Test
	void companyTest() {
		Account account = new Account(0, new Date(System.currentTimeMillis()), "testCompany", "password", Account.COMPANY_ACCOUNT_TYPE);
		account = manager.addAccount(account);
		
		CompanyProfile profile = new CompanyProfile("Test University", "Desc", new Date(12371237), "logo.jpg", "email", "599111213", "Address 1");
		Company company = new Company(account, profile);
		manager.updateCompany(company);

		Company fromDBCompany = manager.getCompany(account.getID());
		assertEquals(company, fromDBCompany);
		manager.deleteAccount(account);
	}
	
	@Test
	void employeeTest() {
		Account account = new Account(0, new Date(System.currentTimeMillis()), "testEmployee", "password", Account.EMPLOYEE_ACCOUNT_TYPE);
		account = manager.addAccount(account);
		
		Employee employee = new Employee(account, new EmployeeProfile("Name", "Surname", "Sex", new Date(123133), 
				"Major", "Minor", "email@email.com", "579101010", "Tbilisi", "Desc",  "logo.jpg", false));
		manager.updateEmployee(employee);
		Employee fromDBEmployee = manager.getEmployee(account.getID());
		assertEquals(employee, fromDBEmployee);
		
		manager.deleteAccount(account);
	}
	
	@Test
	void vacancyTest() {
		Requirement requirement = new Requirement("Tbilisi", 0, "Bachelor", "finansisti");
		Vacancy vacancy = new Vacancy(7, "Heading", "Pos", "Desc", "Part-time", 7,
				requirement, null, new Date(System.currentTimeMillis() + 5000000));
		manager.addVacancy(vacancy);
		
		Vacancy fromDBVacancy = null;
		int id = 7;
		while (true) {
			fromDBVacancy = manager.getVacancy(id);
			if (fromDBVacancy != null) 
				break;
			id++;
		}
		assertEquals(vacancy.getCompanyId(), fromDBVacancy.getCompanyId());
		assertEquals(vacancy.getHeading(), fromDBVacancy.getHeading());
		assertEquals(vacancy.getDescription(), fromDBVacancy.getDescription());
//		assertEquals(vacancy.getStartDate(), fromDBVacancy.getStartDate());
//		assertEquals(vacancy.getEndDate(), fromDBVacancy.getEndDate());
		assertEquals(vacancy.getReq().getLocation(), fromDBVacancy.getReq().getLocation());
		assertEquals(vacancy.getPosition(), fromDBVacancy.getPosition());
//		assertEquals(vacancy, fromDBVacancy);
		
		Requirement updatedRequirement = new Requirement("Batumi", 5, "Bachelor", "finansisti");
		Vacancy updatedVacancy = new Vacancy(id, "UpdatedHeading", "Salesperson", "UpdatedDesc", "Full-time", 
				7, updatedRequirement, null, new Date(System.currentTimeMillis() + 10000));
		manager.updateVacancy(updatedVacancy);
		
		
		fromDBVacancy = manager.getVacancy(id);
		assertEquals(updatedVacancy.getCompanyId(), fromDBVacancy.getCompanyId());
		assertEquals(updatedVacancy.getHeading(), fromDBVacancy.getHeading());
		assertEquals(updatedVacancy.getDescription(), fromDBVacancy.getDescription());
//		assertEquals(updatedVacancy.getStartDate(), fromDBVacancy.getStartDate());
//		assertEquals(updatedVacancy.getEndDate(), fromDBVacancy.getEndDate());
		assertEquals(updatedVacancy.getReq().getLocation(), fromDBVacancy.getReq().getLocation());
		assertEquals(updatedVacancy.getPosition(), fromDBVacancy.getPosition());
//		assertEquals(updatedVacancy, fromDBVacancy);
		
		manager.deleteVacancy(fromDBVacancy.getId());
	}
	
	private static final int TEST_EMPLOYEE_ID = 3;
	private static final int TEST_VACANCY_ID = 4;
	private static final int TEST_COMPANY_ID = 8;
	@Test
	void followTest() {
		manager.addFollower(TEST_COMPANY_ID, TEST_EMPLOYEE_ID);
		
		manager.unFollow(TEST_COMPANY_ID, TEST_EMPLOYEE_ID);
	}
	
	@Test
	void applicationTest() {
		manager.addApplication(TEST_EMPLOYEE_ID, TEST_VACANCY_ID);
		
		manager.removeApplication(TEST_EMPLOYEE_ID, TEST_VACANCY_ID);
	}
	
	@Test
	void tagsTest() {
		manager.addVacancyTag(TEST_VACANCY_ID, "tag");
		manager.removeVacancyTag(TEST_VACANCY_ID, "tag");
		
		manager.addEmployeeTag(TEST_EMPLOYEE_ID, "tag");
		manager.removeEmployeeTag(TEST_EMPLOYEE_ID, "tag");
	}
	
	@Test
	void getDefaultsTest() {
		assertEquals(66, manager.getLocations().size());
		assertEquals(67, manager.getProfessions().size());
		assertEquals(6, manager.getEducationalInstitutionTypes().size());
		assertEquals(6, manager.getDegrees().size());
		assertEquals(4, manager.getQualities().size());
		assertEquals(33, manager.getTags().size());
		assertEquals(15, manager.getLanguages().size());
	}
	
	@Test
	void getEmployeeFollowingTest() {
		int otherCompanyId = 9;
		
		manager.addFollower(TEST_COMPANY_ID, TEST_EMPLOYEE_ID);
		manager.addFollower(otherCompanyId, TEST_EMPLOYEE_ID);
		
		Company company1 = manager.getCompany(TEST_COMPANY_ID);
		Company company2 = manager.getCompany(otherCompanyId);
		List<Company> list = manager.getEmployeeFollowing(TEST_EMPLOYEE_ID);
		assertEquals(2, list.size());
		assertEquals(company1, list.get(0));
		assertEquals(company2, list.get(1));
		
		manager.unFollow(TEST_COMPANY_ID, TEST_EMPLOYEE_ID);
		manager.unFollow(otherCompanyId, TEST_EMPLOYEE_ID);
	}
	
	@Test
	void getEmployeeTagsTest() {
		manager.addEmployeeTag(TEST_EMPLOYEE_ID, "tag1");
		manager.addEmployeeTag(TEST_EMPLOYEE_ID, "tag2");
		
		List<String> myList = new ArrayList<String>();
		myList.add("tag1");
		myList.add("tag2");
		
		List<String> list = manager.getEmployeeTags(TEST_EMPLOYEE_ID);
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		assertEquals(myList, list);
		
		manager.removeEmployeeTag(TEST_EMPLOYEE_ID, "tag1");
		manager.removeEmployeeTag(TEST_EMPLOYEE_ID, "tag2");
	}
	
	@Test
	void getVacancyTagsTest() {
		manager.addVacancyTag(TEST_VACANCY_ID, "tag1");
		manager.addVacancyTag(TEST_VACANCY_ID, "tag2");
		
		List<String> myList = new ArrayList<String>();
		myList.add("tag1");
		myList.add("tag2");
		
		List<String> list = manager.getVacancyTags(TEST_VACANCY_ID);
		Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
		assertEquals(myList, list);
		
		manager.removeVacancyTag(TEST_VACANCY_ID, "tag1");
		manager.removeVacancyTag(TEST_VACANCY_ID, "tag2");
	}
}
