package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
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
import classes.EmployeeEducation;
import classes.EmployeeProfile;
import classes.Language;
import classes.Requirement;
import classes.Vacancy;
import classes.WorkExperience;


/**
 * The Class DBManagerTests.
 */
class DBManagerTests {
	
	/** The manager. */
	DBManager manager = new DBManager(DBConnection.getTestCon());

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {

	}

	/** The Constant TEST_EMPLOYEE_ID. */
	private static final int TEST_EMPLOYEE_ID = 3;
	
	/** The Constant TEST_VACANCY_ID. */
	private static final int TEST_VACANCY_ID = 4;
	
	/** The Constant TEST_COMPANY_ID. */
	private static final int TEST_COMPANY_ID = 8;

	/**
	 * Account tests.
	 * Tests Methods: addAccount, getAccount, updateAccount, deleteAccount
	 * 
	 */
	@Test
	void accountTest() {
		Account account = new Account(0, new Date(System.currentTimeMillis()), "testUser", "password",
				Account.EMPLOYEE_ACCOUNT_TYPE);
		account = manager.addAccount(account);

		Account fromDBAccount = manager.getAccount(account.getUsername());
		assertEquals(account, fromDBAccount);

		account.setPassHash("newPassword");
		manager.updateAccount(account);
		fromDBAccount = manager.getAccount(account.getUsername());
		assertEquals(account, fromDBAccount);

		manager.deleteAccount(account);
	}

	/**
	 * Company tests.
	 * Tests Methods: addAccount, updateCompany, getCompany, deleteAccount.
	 * 
	 */
	@Test
	void companyTest() {
		Account account = new Account(0, new Date(System.currentTimeMillis()), "testCompany", "password",
				Account.COMPANY_ACCOUNT_TYPE);
		account = manager.addAccount(account);

		CompanyProfile profile = new CompanyProfile("Test University", "Desc", "Social Network", new Date(12371237),
				"logo.jpg", "email", "599111213", "Address 1");
		Company company = new Company(account, profile);
		manager.updateCompany(company);

		Company fromDBCompany = manager.getCompany(account.getID());
		assertEquals(company, fromDBCompany);
		manager.deleteAccount(account);
	}

	/**
	 * Employee test.
	 * Tests Methods: addAccount, updateEmployee, getEmployee, deleteAccount.
	 */
	@Test
	void employeeTest() {
		Account account = new Account(0, new Date(System.currentTimeMillis()), "testEmployee", "password",
				Account.EMPLOYEE_ACCOUNT_TYPE);
		account = manager.addAccount(account);

		Employee employee = new Employee(account, new EmployeeProfile("Name", "Surname", "Sex", new Date(123133),
				"Major", "Minor", "email@email.com", "579101010", "Tbilisi", "Desc", "logo.jpg", false));
		manager.updateEmployee(employee);
		Employee fromDBEmployee = manager.getEmployee(account.getID());
		assertEquals(employee, fromDBEmployee);

		manager.deleteAccount(account);
	}

	/**
	 * Vacancy test.
	 * Tests Methods: getVacancy, updateVacancy, deleteVacancy.
	 */
	@Test
	void vacancyTest() {
		Requirement requirement = new Requirement("Tbilisi", 0, "Bachelor", "Chef", "qualification1", "qualification2",
				"qualification3");
		Vacancy vacancy = new Vacancy(7, "Heading", "Pos", "Desc", "Part-time", TEST_COMPANY_ID, requirement, null,
				new Date(System.currentTimeMillis() + 5000000));
		int id = manager.addVacancy(vacancy).getId();
		Vacancy fromDBVacancy = manager.getVacancy(id);

		assertEquals(vacancy.getCompanyId(), fromDBVacancy.getCompanyId());
		assertEquals(vacancy.getHeading(), fromDBVacancy.getHeading());
		assertEquals(vacancy.getDescription(), fromDBVacancy.getDescription());
		assertEquals(vacancy.getEndDate(), fromDBVacancy.getEndDate());
		assertEquals(vacancy.getReq().getLocation(), fromDBVacancy.getReq().getLocation());
		assertEquals(vacancy.getReq().getQualification1(), fromDBVacancy.getReq().getQualification1());
		assertEquals(vacancy.getReq().getQualification2(), fromDBVacancy.getReq().getQualification2());
		assertEquals(vacancy.getReq().getQualification3(), fromDBVacancy.getReq().getQualification3());
		assertEquals(vacancy.getPosition(), fromDBVacancy.getPosition());

		Requirement updatedRequirement = new Requirement("Batumi", 5, "Bachelor", "Driver", "new qualification1",
				"new qualification2", "new qualification3");
		Vacancy updatedVacancy = new Vacancy(id, "UpdatedHeading", "Salesperson", "UpdatedDesc", "Full-time",
				TEST_COMPANY_ID, updatedRequirement, null, new Date(System.currentTimeMillis() + 10000));
		manager.updateVacancy(updatedVacancy);

		fromDBVacancy = manager.getVacancy(id);
		assertEquals(updatedVacancy.getCompanyId(), fromDBVacancy.getCompanyId());
		assertEquals(updatedVacancy.getHeading(), fromDBVacancy.getHeading());
		assertEquals(updatedVacancy.getDescription(), fromDBVacancy.getDescription());
		assertEquals(updatedVacancy.getEndDate(), fromDBVacancy.getEndDate());
		assertEquals(updatedVacancy.getReq().getLocation(), fromDBVacancy.getReq().getLocation());
		assertEquals(updatedVacancy.getReq().getQualification1(), fromDBVacancy.getReq().getQualification1());
		assertEquals(updatedVacancy.getReq().getQualification2(), fromDBVacancy.getReq().getQualification2());
		assertEquals(updatedVacancy.getReq().getQualification3(), fromDBVacancy.getReq().getQualification3());
		assertEquals(updatedVacancy.getPosition(), fromDBVacancy.getPosition());

		assertEquals(3, manager.getCompanyVacancies(TEST_COMPANY_ID).size());

		manager.deleteVacancy(fromDBVacancy.getId());
	}

	/**
	 * Follow test.
	 * Tests Methods: addFollower, unFollow
	 */
	@Test
	void followTest() {
		manager.addFollower(TEST_COMPANY_ID, TEST_EMPLOYEE_ID);
		manager.unFollow(TEST_COMPANY_ID, TEST_EMPLOYEE_ID);
	}

	/**
	 * Application test.
	 * Tests Methods: addApplication, removeApplication.
	 */
	@Test
	void applicationTest() {
		manager.addApplication(TEST_EMPLOYEE_ID, TEST_VACANCY_ID);
		assertEquals(manager.getVacancy(TEST_VACANCY_ID), manager.getEmployeeApplications(TEST_EMPLOYEE_ID).get(0));
		assertEquals(manager.getEmployee(TEST_EMPLOYEE_ID), manager.getVacancyApplicants(TEST_VACANCY_ID).get(0));
		assertTrue(manager.hasApplied(TEST_EMPLOYEE_ID, TEST_VACANCY_ID));
		manager.removeApplication(TEST_EMPLOYEE_ID, TEST_VACANCY_ID);
		assertFalse(manager.hasApplied(TEST_EMPLOYEE_ID, TEST_VACANCY_ID));
	}

	/**
	 * Tags test.
	 * Tests Methods: addVacancyTag, removeVacancyTag, addEmployeeTag, removeEmployeeTag.
	 */
	@Test
	void tagsTest() {
		manager.addVacancyTag(TEST_VACANCY_ID, "tag");
		manager.removeVacancyTag(TEST_VACANCY_ID, "tag");

		manager.addEmployeeTag(TEST_EMPLOYEE_ID, "tag");
		manager.removeEmployeeTag(TEST_EMPLOYEE_ID, "tag");
	}

	/**
	 * Gets the default lists test.
	 *
	 * @return the default lists test
	 */
	@Test
	void getDefaultListsTest() {
		assertEquals(66, manager.getLocations().size());
		assertEquals(67, manager.getProfessions().size());
		assertEquals(5, manager.getEducationalInstitutionTypes().size());
		assertEquals(5, manager.getDegrees().size());
		assertEquals(4, manager.getQualities().size());
		assertEquals(31, manager.getTags().size());
		assertEquals(15, manager.getLanguages().size());
	}

	/**
	 * Gets the employee following test.
	 *
	 * @return the employee following test
	 */
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

	/**
	 * Gets the employee tags test.
	 *
	 * @return the employee tags test
	 */
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

	/**
	 * Gets the vacancy tags test.
	 *
	 * @return the vacancy tags test
	 */
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

	/**
	 * Languages test.
	 */
	@Test
	void languagesTest() {
		Language language = new Language(Language.DEFAULT_ID, "Georgian", "Great", "B2");
		manager.addEmployeeLanguage(TEST_EMPLOYEE_ID, language);
		manager.removeEmployeeLanguage(TEST_EMPLOYEE_ID, language.getLanguage());

		manager.addReqLanguage(TEST_VACANCY_ID, language.getLanguage());
		manager.removeReqLanguage(TEST_VACANCY_ID, language.getLanguage());
	}

	/**
	 * Gets the employee languages test.
	 *
	 * @return the employee languages test
	 */
	@Test
	void getEmployeeLanguagesTest() {
		Language language1 = new Language(Language.DEFAULT_ID, "Georgian", "Great", "B2");
		Language language2 = new Language(Language.DEFAULT_ID, "Arabic", "Good", "C1");
		manager.addEmployeeLanguage(TEST_EMPLOYEE_ID, language1);
		manager.addEmployeeLanguage(TEST_EMPLOYEE_ID, language2);

		List<Language> list = manager.getEmployeeLanguages(TEST_EMPLOYEE_ID);
		assertEquals(2, list.size());

		manager.removeEmployeeLanguage(TEST_EMPLOYEE_ID, language1.getLanguage());
		manager.removeEmployeeLanguage(TEST_EMPLOYEE_ID, language2.getLanguage());
	}

	/**
	 * Gets the vacancy languages test.
	 *
	 * @return the vacancy languages test
	 */
	@Test
	void getVacancyLanguagesTest() {
		Language language1 = new Language(Language.DEFAULT_ID, "Georgian", "Great", "B2");
		Language language2 = new Language(Language.DEFAULT_ID, "Arabic", "Good", "C1");
		manager.addReqLanguage(TEST_VACANCY_ID, language1.getLanguage());
		manager.addReqLanguage(TEST_VACANCY_ID, language2.getLanguage());

		List<Language> list = manager.getRequirementLanguages(TEST_VACANCY_ID);
		assertEquals(2, list.size());

		manager.removeReqLanguage(TEST_VACANCY_ID, language1.getLanguage());
		manager.removeReqLanguage(TEST_VACANCY_ID, language2.getLanguage());
	}

	/**
	 * Gets the lists test.
	 *
	 * @return the lists test
	 */
	@Test
	void getListsTest() {
		assertEquals(6, manager.getCompanies().size());
		assertEquals(6, manager.getEmployees().size());
		assertEquals(6, manager.getVacancies().size());
	}

	/**
	 * Employee education test.
	 */
	@Test
	void employeeEducationTest() {
		EmployeeEducation education = new EmployeeEducation(EmployeeEducation.DEFAULT_ID, new Date(1241415215),
				new Date(1244415215), "University", "Free University", "Computer Science", "Philosophy", "Bachelor",
				3.5);
		manager.addEducation(TEST_EMPLOYEE_ID, education);

		List<EmployeeEducation> list = manager.getEducation(TEST_EMPLOYEE_ID);
		EmployeeEducation fromDBEducation = list.get(0);
		assertEquals(1, list.size());
		assertEquals(education.getInstitutionName(), fromDBEducation.getInstitutionName());
		assertEquals(education.getDegree(), fromDBEducation.getDegree());
		assertEquals(education.getMajor(), fromDBEducation.getMajor());
		assertEquals(education.getMinor(), fromDBEducation.getMinor());
		assertEquals(education.getEducationalInstitution(), fromDBEducation.getEducationalInstitution());
		assertEquals(education.getStartDate(), fromDBEducation.getStartDate());
		assertEquals(education.getEndDate(), fromDBEducation.getEndDate());
		assertEquals(education.getGrade(), fromDBEducation.getGrade());

		manager.removeEducation(fromDBEducation.getId());
	}

	/**
	 * Employee work exp test.
	 */
	@Test
	void employeeWorkExpTest() {
		WorkExperience experience = new WorkExperience(WorkExperience.DEFAULT_ID, new Date(1241415215),
				new Date(1244415215), "Free University", "Data Scientist", "SQL Developer", Vacancy.FULL_TIME_EMP_TYPE,
				"Scientia, Labor, Libertas", "Medal of Honor");
		manager.addWorkExp(TEST_EMPLOYEE_ID, experience);

		List<WorkExperience> list = manager.getWorkExps(TEST_EMPLOYEE_ID);
		WorkExperience fromDBExperience = list.get(0);
		assertEquals(1, list.size());
		assertEquals(experience.getCompanyName(), fromDBExperience.getCompanyName());
		assertEquals(experience.getStartDate(), fromDBExperience.getStartDate());
		assertEquals(experience.getEndDate(), fromDBExperience.getEndDate());
		assertEquals(experience.getEmploymentType(), fromDBExperience.getEmploymentType());
		assertEquals(experience.getPostition(), fromDBExperience.getPostition());
		assertEquals(experience.getProfession(), fromDBExperience.getProfession());
		assertEquals(experience.getDuty(), fromDBExperience.getDuty());
		assertEquals(experience.getAchievement(), fromDBExperience.getAchievement());

		manager.removeWorkExp(fromDBExperience.getId());
	}
}
