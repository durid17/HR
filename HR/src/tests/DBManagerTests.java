package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

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
		manager.addAccount(account);
		
		Account fromDBAccount = manager.getAccount(account.getUsername());
		assertEquals(account.getUsername(), fromDBAccount.getUsername());
		assertEquals(account.getPassHash(), fromDBAccount.getPassHash());
//		assertEquals(account.getRegDate(), fromDBAccount.getRegDate());
		assertEquals(account.getAccountType(), fromDBAccount.getAccountType());

		account.setPassHash("newPassword");
		manager.updateAccount(account);
		fromDBAccount = manager.getAccount(account.getUsername());
		assertEquals(fromDBAccount.getPassHash(), account.getPassHash());
		
		manager.deleteAccount(account);
	}
	
	@Test
	void companyTest() {
		Account account = new Account(0, new Date(System.currentTimeMillis()), "testCompany", "password", Account.COMPANY_ACCOUNT_TYPE);
		account = manager.addAccount(account);
		
		CompanyProfile profile = new CompanyProfile("Test University", "Desc", new Date(System.currentTimeMillis()), "logo.jpg", "em", "num", "ad");
		Company company = new Company(account, profile);
		manager.updateCompany(company);
		Company fromDBCompany = manager.getCompany(account.getID());
		assertEquals(account.getUsername(), fromDBCompany.getAccount().getUsername());
		assertEquals(account.getPassHash(), fromDBCompany.getAccount().getPassHash());
		assertEquals(account.getAccountType(), fromDBCompany.getAccount().getAccountType());
		assertEquals(company.getProfile().getName(), fromDBCompany.getProfile().getName());
		assertEquals(company.getProfile().getDescription(), fromDBCompany.getProfile().getDescription());
//		assertEquals(company.getProfile().getFounded(), fromDBCompany.getProfile().getFounded());
		assertEquals(company.getProfile().getLogo(), fromDBCompany.getProfile().getLogo());
		
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
		assertEquals(account.getUsername(), fromDBEmployee.getAccount().getUsername());
		assertEquals(account.getPassHash(), fromDBEmployee.getAccount().getPassHash());
		assertEquals(account.getAccountType(), fromDBEmployee.getAccount().getAccountType());
		assertEquals(employee.getProfile().getName(), fromDBEmployee.getProfile().getName());
		assertEquals(employee.getProfile().getSurname(), fromDBEmployee.getProfile().getSurname());
		assertEquals(employee.getProfile().getGender(), fromDBEmployee.getProfile().getGender());
		assertEquals(employee.getProfile().getMajorProfession(), fromDBEmployee.getProfile().getMajorProfession());
		assertEquals(employee.getProfile().getMinorProfession(), fromDBEmployee.getProfile().getMinorProfession());
		assertEquals(employee.getProfile().getEmail(), fromDBEmployee.getProfile().getEmail());
		assertEquals(employee.getProfile().getPhoneNumber(), fromDBEmployee.getProfile().getPhoneNumber());
		assertEquals(employee.getProfile().getAddress(), fromDBEmployee.getProfile().getAddress());
		assertEquals(employee.getProfile().getDescription(), fromDBEmployee.getProfile().getDescription());
		assertEquals(employee.getProfile().getProfilePicture(), fromDBEmployee.getProfile().getProfilePicture());
//		assertEquals(employee.getProfile().getBirthDate(), fromDBEmployee.getProfile().getBirthDate());
		
		manager.deleteAccount(account);
	}
	
	@Test
	void vacancyTest() {
		Requirement requirement = new Requirement("Tbilisi", 0, "Bachelor");
		Vacancy vacancy = new Vacancy(0, "Heading", "pos", "Desc", "Part Time", 7, "intership",
				requirement, null, new Date(System.currentTimeMillis() + 5000000));
		manager.addVacancy(vacancy);
		
		Vacancy fromDBVacancy = manager.getVacancy(7);
		assertEquals(vacancy.getCompanyId(), fromDBVacancy.getCompanyId());
		assertEquals(vacancy.getHeading(), fromDBVacancy.getHeading());
		assertEquals(vacancy.getDescription(), fromDBVacancy.getDescription());
//		assertEquals(vacancy.getStartDate(), fromDBVacancy.getStartDate());
//		assertEquals(vacancy.getEndDate(), fromDBVacancy.getEndDate());
		assertEquals(vacancy.getReq().getLocation(), fromDBVacancy.getReq().getLocation());
		assertEquals(vacancy.getPosition(), fromDBVacancy.getPosition());
		assertEquals(vacancy.getJobType(), fromDBVacancy.getJobType());
		
		Requirement updatedRequirement = new Requirement("Batumi", 5, "Bachelor");
		Vacancy updatedVacancy = new Vacancy(7, "UpdatedHeading", "Salesperson",  "UpdatedDesc", "Part Time", 
				7, "intership", updatedRequirement, null, new Date(System.currentTimeMillis() + 10000));
		manager.updateVacancy(updatedVacancy);
		
		fromDBVacancy = manager.getVacancy(7);
		assertEquals(updatedVacancy.getCompanyId(), fromDBVacancy.getCompanyId());
		assertEquals(updatedVacancy.getHeading(), fromDBVacancy.getHeading());
		assertEquals(updatedVacancy.getDescription(), fromDBVacancy.getDescription());
//		assertEquals(updatedVacancy.getStartDate(), fromDBVacancy.getStartDate());
//		assertEquals(updatedVacancy.getEndDate(), fromDBVacancy.getEndDate());
		assertEquals(updatedVacancy.getReq().getLocation(), fromDBVacancy.getReq().getLocation());
		assertEquals(updatedVacancy.getPosition(), fromDBVacancy.getPosition());
		assertEquals(updatedVacancy.getJobType(), fromDBVacancy.getJobType());
		
		manager.deleteVacancy(fromDBVacancy.getId());
	}
} 
