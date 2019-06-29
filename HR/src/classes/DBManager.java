package classes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

	private Connection con;

	public DBManager(Connection con) {
		this.con = con;
	}

	//
	public Account addAccount(Account account) {
		try {
			PreparedStatement stmt1 = con.prepareStatement("INSERT INTO accounts (username, password_hash, account_type) VALUES (?, ?, ?)");
			stmt1.setString(1, account.getUsername());
			stmt1.setString(2, account.getPassHash());
			stmt1.setString(3, account.getAccountType());
			stmt1.executeUpdate();

			int accountID = getAccountID(account);

			if (account.getAccountType().equals(Account.EMPLOYEE_ACCOUNT_TYPE)) {
				PreparedStatement stmt2 = con.prepareStatement("INSERT INTO employees (id) VALUES (?)");
				stmt2.setInt(1, accountID);
				stmt2.executeUpdate();
			} else if (account.getAccountType().equals(Account.COMPANY_ACCOUNT_TYPE)) {
				PreparedStatement stmt3 = con.prepareStatement("INSERT INTO companies (id) VALUES (?)");
				stmt3.setInt(1, accountID);
				stmt3.executeUpdate();
			}
			return getAccount(accountID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private int getAccountID (Account account) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT id FROM accounts WHERE username = ?");
			stmt.setString(1, account.getUsername());
			ResultSet resultSet = stmt.executeQuery();
			int accountID = 0;
			if (resultSet.next()) {
				accountID = resultSet.getInt("id");
			}
			return accountID;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public void updateAccount(Account account) {
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE accounts SET password_hash = ? WHERE username = ?");
			stmt.setString(1, account.getPassHash());
			stmt.setString(2, account.getUsername());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAccount(Account account) {
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE from accounts WHERE username = ?");
			stmt.setString(1, account.getUsername());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Account getAccount(String username) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM accounts WHERE username = ?");
			stmt.setString(1, username);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return new Account(resultSet.getInt("id"), 
								   resultSet.getDate("registration_date"),
								   resultSet.getString("username"), 
								   resultSet.getString("password_hash"),
								   resultSet.getString("account_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccount(int id) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM accounts WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				return new Account(resultSet.getInt("id"), resultSet.getDate("registration_date"),
						resultSet.getString("username"), resultSet.getString("password_hash"),
						resultSet.getString("account_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//
	public void updateCompany(Company company) {
		CompanyProfile profile = company.getProfile();
		PreparedStatement updateCompany = null;
		String updateString = "UPDATE companies SET name = ?, "
												 + "description = ?, "
												 + "logo = ?, "
												 + "founded_date = ?, "
												 + "email = ?, "
												 + "phone = ?, "
												 + "address = ? WHERE id = ?";

		try {
			updateCompany = con.prepareStatement(updateString);

			updateCompany.setString(1, profile.getName());
			updateCompany.setString(2, profile.getDescription());
			updateCompany.setString(3, profile.getLogo());
			updateCompany.setDate(4, profile.getFounded());
			updateCompany.setString(5, profile.getEmail());
			updateCompany.setString(6, profile.getPhoneNumber());
			updateCompany.setString(7, profile.getAddress());
			updateCompany.setInt(8, company.getId());
			updateCompany.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Company getCompany(int companyId) {
		PreparedStatement getCompany = null;
		Company company = null;
		CompanyProfile profile = null;

		String query = "select * from companies " + "where id = ?";

		try {
			getCompany = con.prepareStatement(query);
			getCompany.setInt(1, companyId);
			ResultSet resultSet = getCompany.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				String logo = resultSet.getString("logo");
				Date foundedDate = resultSet.getDate("founded_date");
				String email = resultSet.getString("email");
				String phone = resultSet.getString("phone");
				String address = resultSet.getString("address");

				profile = new CompanyProfile(name, description, foundedDate, logo, email, phone, address);
				company = new Company(getAccount(companyId), profile);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return company;
	}

	public List<Vacancy> getCompanyVacancies(int companyId) {
		List<Vacancy> res = new ArrayList<Vacancy>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM vacancise WHERE company_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, companyId);

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("heading");
				String position = resultSet.getString("position");
				int company_id = resultSet.getInt("company_id");
				String description = resultSet.getString("description");
				String empType = resultSet.getString("emp_type");
				Date creationDate = resultSet.getDate("creation_date");
				Date expiryDate = resultSet.getDate("expiry_date");
				String location = resultSet.getString("location");
				String degree = resultSet.getString("degree");
				int yearsOfExp = resultSet.getInt("years_of_experince");

				Requirement req = new Requirement(location, yearsOfExp, degree);
				Vacancy vac = new Vacancy(id, title, position, description, empType,
						company_id, req, creationDate, expiryDate);
				res.add(vac);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	//
	public Employee getEmployee(int employeeId) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM employees WHERE id = ?");
			stmt.setInt(1, employeeId);
			ResultSet resultSet = stmt.executeQuery();
			
			if (resultSet.next()) {
				EmployeeProfile profile = new EmployeeProfile(resultSet.getString("firstname"),
															  resultSet.getString("lastname"),
															  resultSet.getString("gender"),
															  resultSet.getDate("birth_date"),
															  resultSet.getString("major_profession"),
															  resultSet.getString("minor_profession"),
															  resultSet.getString("email"),
															  resultSet.getString("phone"),
															  resultSet.getString("address"),
															  resultSet.getString("description"),
															  resultSet.getString("profile_picture"),
															  resultSet.getBoolean("isWorking"));
				Employee employee = new Employee(getAccount(employeeId), profile);
				return employee;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateEmployee(Employee employee) {
		try {
			PreparedStatement stmt1 = con.prepareStatement("UPDATE employees SET firstname = ?, " 
																			  + "lastname = ?, "
																			  + "gender = ?, " 
																			  + "birth_date = ?, " 
																			  + "major_profession = ?, " 
																			  + "minor_profession = ?, " 
																			  + "email = ?, " 
																			  + "phone = ?, "
																			  + "address = ?, "
																			  + "profile_picture = ?, "
																			  + "description = ?, "
																			  + "isWorking = ? "
																			  + "WHERE id = ?");
			stmt1.setString(1, employee.getProfile().getName());
			stmt1.setString(2, employee.getProfile().getSurname());
			stmt1.setString(3, employee.getProfile().getGender());
			stmt1.setDate(4, employee.getProfile().getBirthDate());
			stmt1.setString(5, employee.getProfile().getMajorProfession());
			stmt1.setString(6, employee.getProfile().getMinorProfession());
			stmt1.setString(7, employee.getProfile().getEmail());
			stmt1.setString(8, employee.getProfile().getPhoneNumber());
			stmt1.setString(9, employee.getProfile().getAddress());
			stmt1.setString(10, employee.getProfile().getProfilePicture());
			stmt1.setString(11, employee.getProfile().getDescription());
			stmt1.setBoolean(12, employee.getProfile().isWorking());
			stmt1.setInt(13, employee.getAccount().getID());

			stmt1.executeUpdate();

			/*
			 * PreparedStatement stmt2 =
			 * con.prepareStatement("UPDATE employees SET firstname = ?");
			 * 
			 * stmt2.setInt(1, employee.getId()); // TODO set?s stmt2.executeUpdate();
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Vacancy> getEmployeeApplications(int employeeId) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM applicants WHERE employee_id = ?");
			stmt.setInt(1, employeeId);
			ResultSet resultSet = stmt.executeQuery();

			List<Vacancy> vacancies = new ArrayList<Vacancy>();
			while (resultSet.next()) {
				Vacancy vacancy = getVacancy(resultSet.getInt("vacancy_id"));
				vacancies.add(vacancy);
			}
			return vacancies;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Company> getEmployeeFollowing(int employeeId) {
		try {
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM followers WHERE employee_id = ?");
			stmt.setInt(1, employeeId);
			ResultSet resultSet = stmt.executeQuery();

			List<Company> companies = new ArrayList<Company>();
			while (resultSet.next()) {
				Company company = getCompany(resultSet.getInt("company_id"));
				companies.add(company);
			}
			return companies;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//
	public void addVacancy(Vacancy vacancy) {
		Requirement req = vacancy.getReq();
		PreparedStatement stmt = null;
		String query = "insert into vacancies (company_id, "
											+ "heading, "
											+ "description, "
											+ "expiry_date, "
											+ "emp_type, "
											+ "position, "
											+ "years_of_experience, "
											+ "location, "
											+ "degree) VALUES (?,?,?,?,?,?,?,?,?)";
//	
//		PreparedStatement stmt2 = null;
//		String query2 = "insert into ReqLanguages "
//							+ "(requirements_id, language_name, quality) "
//								+ "VALUES (?, ?, ?)";
//		
		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, vacancy.getCompanyId());
			stmt.setString(2, vacancy.getHeading());
			stmt.setString(3, vacancy.getDescription());
			stmt.setDate(4, vacancy.getEndDate());
			stmt.setString(5, vacancy.getEmpType());
			stmt.setString(6, vacancy.getPosition());
			stmt.setInt(7,  vacancy.getReq().getYearsOfExp());
			stmt.setString(8,  vacancy.getReq().getLocation());
			stmt.setString(9,  vacancy.getReq().getDegree());

			stmt.executeUpdate();
			
//			stmt2 = con.prepareStatement(query2);
//			stmt2.setInt(1, req.getId());
//			stmt2.setString(2, lan.getLanguage());
//			stmt2.setString(3, lan.getQuality());
//			
//			stmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateVacancy(Vacancy vacancy) {
		Requirement req = vacancy.getReq();
		PreparedStatement updateVacancy = null;
		String updateQuery = "UPDATE vacancies SET heading = ?, "
												+ "description = ?, "
												+ "expiry_date = ?, "
												+ "emp_type = ?, "
												+ "position = ?, "
												+ "years_of_experience = ?, "
												+ "location = ?, "
												+ "degree = ? WHERE id = ?";
		
//		PreparedStatement updateLanguage = null;
//		String updateQuery2 = "update ReqLanguages " + "set language_name = ?, quality = ? "
//								+ "where requirements_id = ?";
		
		try {
			updateVacancy = con.prepareStatement(updateQuery);

			updateVacancy.setString(1, vacancy.getHeading());
			updateVacancy.setString(2, vacancy.getDescription());
			updateVacancy.setDate(3, vacancy.getEndDate());
			updateVacancy.setString(4, vacancy.getEmpType());
			updateVacancy.setString(5, vacancy.getPosition());
			updateVacancy.setInt(6, vacancy.getReq().getYearsOfExp());
			updateVacancy.setString(7, vacancy.getReq().getLocation());
			updateVacancy.setString(8, vacancy.getReq().getDegree());
			updateVacancy.setInt(9, vacancy.getId());

			updateVacancy.executeUpdate();
			
//			updateLanguage = con.prepareStatement(updateQuery2);
//			
//			updateLanguage.setString(1, lan.getLanguage());
//			updateLanguage.setString(2, lan.getQuality());
//			updateLanguage.setInt(3, req.getId());
//			
//			updateLanguage.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteVacancy(int id) {
		PreparedStatement stmt = null;
		String query = "delete from vacancies where id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//
	public Vacancy getVacancy(int id) {
		PreparedStatement getCompany = null;
		Requirement req = null;
		Vacancy vac = null;
		String query = "SELECT * FROM vacancies WHERE id = ?";

		try {
			getCompany = con.prepareStatement(query);
			getCompany.setInt(1, id);
			ResultSet resultSet = getCompany.executeQuery();

			if (resultSet.next()) {
				int vacId = resultSet.getInt("id");
				String title = resultSet.getString("heading");
				String position = resultSet.getString("position");
				int company_id = resultSet.getInt("company_id");
				String description = resultSet.getString("description");
				String empType = resultSet.getString("emp_type");
				Date creationDate = resultSet.getDate("creation_date");
				Date expiryDate = resultSet.getDate("expiry_date");
				String location = resultSet.getString("location");
				String degree = resultSet.getString("degree");
				int yearsOfExp = resultSet.getInt("years_of_experience");

				req = new Requirement(location, yearsOfExp, degree);
				vac = new Vacancy(vacId, title, position, description, empType,
						 company_id, req, creationDate, expiryDate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vac;
	}

	//
	public void addFollower(int companyId, int employeeId) {
		PreparedStatement stmt = null;
		String query = "insert into followers (employee_id, company_id) " + "VALUES (?,?)";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);
			stmt.setInt(2, companyId);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void unFollow(int companyId, int employeeId) {
		PreparedStatement stmt = null;
		String query = "delete from followers where employee_id = ? and company_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);
			stmt.setInt(2, companyId);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addApplication(int employeeId, int vacancyId) {
		PreparedStatement stmt = null;
		String query = "insert into followers (vacancy_id, employee_id) " + "VALUES (?,?)";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, vacancyId);
			stmt.setInt(2, employeeId);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeApplication(int employeeId, int vacancyId) {
		PreparedStatement stmt = null;
		String query = "delete from followers where employee_id = ? and company_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, vacancyId);
			stmt.setInt(2, employeeId);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public List<WorkExperience> getWorkExps(int employeeId) {
		List<WorkExperience> res = new ArrayList<WorkExperience>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM experiences WHERE employee_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Date start  = resultSet.getDate("start_date");
				Date end = resultSet.getDate("end_date");
				String company = resultSet.getString("company_name");
				String position = resultSet.getString("position");
				String empType = resultSet.getString("emp_type");
				String duty = resultSet.getString("job_description");
				String award = resultSet.getString("achievement");

				WorkExperience exp = new WorkExperience(id, start, end, company, position, empType, duty, award);
				res.add(exp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	public List<EmployeeEducation> getEducation(int employeeId) {
		List<EmployeeEducation> res = new ArrayList<EmployeeEducation>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM education WHERE employee_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);
				
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Date start  = resultSet.getDate("start_date");
				Date end = resultSet.getDate("end_date");
				String educationalInstitution = resultSet.getString("educational_institution");
				String institutionName = resultSet.getString("eductional_institution_name");
				String major = resultSet.getString("major");
				String minor = resultSet.getString("minor");
				String degree = resultSet.getString("degree");
				double grade = resultSet.getDouble("grade");
				
				EmployeeEducation edu = new EmployeeEducation(id, start, end, educationalInstitution, institutionName,
						major, minor, degree, grade);
				res.add(edu);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	
	public List<Language> getLanguages(int employeeId){
		List<Language> res = new ArrayList<Language>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM languages WHERE employee_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String language = resultSet.getString("language");
				String quality = resultSet.getString("quality");
				String certificate = resultSet.getString("certificate");
				Language lan = new Language(id, language, quality, certificate);
				res.add(lan);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Language> getRequirementLanguages(int vacancyId){
		List<Language> res = new ArrayList<Language>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM requirement_languages WHERE vacancy_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, vacancyId);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String language = resultSet.getString("language_name");
				String quality = resultSet.getString("quality");
				Language lan = new Language(id, language, quality, "");
				res.add(lan);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getEmployeeTags(int employeeId){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM EmployeeTags join tag_types on "
							+ "EmployeeTags.tag_type_id = tag_types.id WHERE employee_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String tag = resultSet.getString("name");
				Tag empTag = new Tag(tag);
				res.add(empTag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getLocations(){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM Locations";

		try {
			stmt = con.prepareStatement(query);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String location = resultSet.getString("city");
				Tag loc = new Tag(location);
				
				res.add(loc);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getVacancyTags(int vacancyId){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM VacancyTags join tag_types on "
							+ "VacancyTags.tag_type_id = tag_types.id WHERE vacancy_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, vacancyId);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String tag = resultSet.getString("name");
				Tag vacTag = new Tag(tag);
				res.add(vacTag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getLanguages(){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM DefaultLanguages";

		try {
			stmt = con.prepareStatement(query);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String language = resultSet.getString("language_name");
				Tag lan = new Tag(language);
				
				res.add(lan);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getInterestingFields(){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM Fields";

		try {
			stmt = con.prepareStatement(query);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String field = resultSet.getString("industry");
				Tag fieldTag = new Tag(field);
				
				res.add(fieldTag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getQualities(){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM DefaultQuality";

		try {
			stmt = con.prepareStatement(query);
			
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String quality = resultSet.getString("quality");
				Tag qualityTag = new Tag(quality);
				
				res.add(qualityTag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getDegrees(){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM degrees";

		try {
			stmt = con.prepareStatement(query);
			
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String deg = resultSet.getString("degree_title");
				Tag degreeTag = new Tag(deg);
				
				res.add(degreeTag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getInstitutionTypes(){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM InstitutionTypes";

		try {
			stmt = con.prepareStatement(query);
			
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String type = resultSet.getString("institutionTypes");
				Tag typeTag = new Tag(type);
				
				res.add(typeTag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public List<Tag> getProfessions(){
		List<Tag> res = new ArrayList<Tag>();

		PreparedStatement stmt = null;
		String query = "SELECT * FROM professions";

		try {
			stmt = con.prepareStatement(query);
			
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String prof = resultSet.getString("profession");
				Tag profTag = new Tag(prof);
				
				res.add(profTag);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public void addWorkExp(int employeeId, WorkExperience workExp) {

	}
	
	
	public void addEducation(int employeeId, EmployeeEducation empEdu) {
		
	}
	
	public void addLanguage(int employeeId, Language lan) {
		
	}
	
	public void addReqLanguage(int vacancyId, Language lan) {
		
	}
	
	public void removeWorkExp(int employeeId, int workExpId) {
		
	}
	
	public void removeEducation(int employeeId, int empEduId) {
		
	}
	
	public void removeLanguage(int employeeId, int lanId) {
		
	}
	
	public void removeReqLanguage(int vacancyId, int lanId) {
		
	}
	
	
}
