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
		String updateString = "update companies " + "set name = ?, description = ?, logo = ?, founded_date = ? "
				+ "where id = ?";

		try {
			updateCompany = con.prepareStatement(updateString);

			updateCompany.setString(1, profile.getName());
			updateCompany.setString(2, profile.getDescription());
			updateCompany.setString(3, profile.getLogo());
			updateCompany.setDate(4, profile.getFounded());
			updateCompany.setInt(5, company.getId());
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
				String jobType = resultSet.getString("job_type");
				String degree = resultSet.getString("degree");
				int yearsOfExp = resultSet.getInt("years_of_experince");

				Requirement req = new Requirement(location, yearsOfExp, degree);
				Vacancy vac = new Vacancy(id, title, position, description, empType,
						company_id, jobType, req, creationDate, expiryDate);
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
																			  + "isWorking = ?"
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
		String query = "insert into vacancies "
							+ "(company_id, heading,  description, expiry_date, "
									+ "emp_type, position, job_type, years_of_experience, location, degree) "
										+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
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
			stmt.setString(7,  vacancy.getJobType());
			stmt.setInt(8,  vacancy.getReq().getYearsOfExp());
			stmt.setString(9,  vacancy.getReq().getLocation());
			stmt.setString(10,  vacancy.getReq().getDegree());

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
												+ "emp_type = ?,  "
												+ "position = ?, "
												+ "job_type = ?, "
												+ "years_of_experience = ?,"
												+ "location = ?,"
												+ "degree = ? where id = ?";
		
//		PreparedStatement updateLanguage = null;
//		String updateQuery2 = "update ReqLanguages " + "set language_name = ?, quality = ? "
//								+ "where requirements_id = ?";
		
		try {
			updateVacancy = con.prepareStatement(updateQuery);

			updateVacancy.setString(1, vacancy.getHeading());
			updateVacancy.setString(2, vacancy.getDescription());
			updateVacancy.setDate(3, vacancy.getEndDate());
			updateVacancy.setString(4,  vacancy.getEmpType());
			updateVacancy.setString(5, vacancy.getPosition());
			updateVacancy.setString(6, vacancy.getJobType());
			updateVacancy.setInt(7, vacancy.getId());
			updateVacancy.setInt(8,  vacancy.getReq().getYearsOfExp());
			updateVacancy.setString(9,  vacancy.getReq().getLocation());
			updateVacancy.setString(10,  vacancy.getReq().getDegree());
			
			updateVacancy.executeUpdate();
//			
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
				String jobType = resultSet.getString("job_type");
				String degree = resultSet.getString("degree");
				int yearsOfExp = resultSet.getInt("years_of_experince");

				req = new Requirement(location, yearsOfExp, degree);
				vac = new Vacancy(vacId, title, position, description, empType,
						 company_id, jobType, req, creationDate, expiryDate);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vac;
	}

	//
	public void addFollower(int companyId, int employeeId) {
		PreparedStatement stmt = null;
		String query = "insert into followers " + "(employee_id, company_id) " + "VALUES (?,?)";

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
		String query = "insert into followers " + "(vacancy_id, employee_id) " + "VALUES (?,?)";

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
		String query = "select * from " + "employees join experiences " + 
							"on experiences.employee_id = employees.ID " 
								+ "where experiences.employee_id = ?";

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
				String type = resultSet.getString("job_type");
				String empType = resultSet.getString("emp_type");
				String duty = resultSet.getString("job_description");
				String award = resultSet.getString("achievement");

				WorkExperience exp = new WorkExperience(id, start, end, company, position, 
						type, empType, duty, award);
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
		String query = "select * from " + "employees join education "
							+ "on education.employee_id = employees.id "
								+ "where education.employee_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);
				
			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Date start  = resultSet.getDate("start_date");
				Date end = resultSet.getDate("end_date");
				String educationalInstitution = resultSet.getString("education_type");
				String institutionName = resultSet.getString("eductional_institution_name");
				String degree = resultSet.getString("degree");
				String grade = resultSet.getString("grade");
				
				EmployeeEducation edu = new EmployeeEducation(id, start, end, educationalInstitution, 
						institutionName, degree, grade);
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
		String query = "select * from " + "employees join Languages "
							+ "on languages.employee_id = employees.id "
								+ "where languages.employee_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, employeeId);
				
			ResultSet resultSet = stmt.executeQuery();
		
			while(resultSet.next()) {
				String language = resultSet.getString("language");
				String quality = resultSet.getString("quality");
				String certificate = resultSet.getString("certificate");
				Language lan = new Language(language, quality, certificate);
				res.add(lan);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
		
}
