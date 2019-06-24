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

				profile = new CompanyProfile(name, description, foundedDate, logo);
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
		String query = "select * from " + "vacancies join companies " + "on vacancies.company_id = companies.id "
				+ "where vacancies.company_id = ?";

		try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1, companyId);

			ResultSet resultSet = stmt.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String heading = resultSet.getString("heading");
				int company_id = resultSet.getInt("company_id");
				String description = resultSet.getString("description");
				Date creation_date = resultSet.getDate("creation_date");
				Date expiry_date = resultSet.getDate("expiry_date");
				String position = resultSet.getString("position");
				String location = resultSet.getString("location");
				String job_type = resultSet.getString("job_type");

				Requirement req = new Requirement(location, position, job_type);
				Vacancy vac = new Vacancy(id, heading, description, company_id, req, creation_date, expiry_date);
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
															  resultSet.getString("profile_picture"));
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
																			  + "birth_date = ?," 
																			  + "major_profession = ?" 
																			  + "minor_profession = ?" 
																			  + "email = ?" 
																			  + "phone = ?"
																			  + "adress = ?,"
																			  + "profile_picture = ?"
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
			stmt1.setInt(11, employee.getAccount().getID());
			
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
				+ "(heading, company_id, description, expiry_date, position, location, job_type) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try {
			stmt = con.prepareStatement(query);
			stmt.setString(1, vacancy.getHeading());
			stmt.setInt(2, vacancy.getCompanyId());
			stmt.setString(3, vacancy.getDescription());
			stmt.setDate(4, vacancy.getEndDate());
			stmt.setString(5, req.getPosition());
			stmt.setString(6, req.getLocation());
			stmt.setString(7, req.getJobType());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateVacancy(Vacancy vacancy) {
		Requirement req = vacancy.getReq();
		PreparedStatement updateVacancy = null;
		String updateString = "update vacancies " + "set heading = ?, description = ?, expiry_date = ?, "
				+ "position = ?, location = ?, job_type = ?" + "where id = ?";

		try {
			updateVacancy = con.prepareStatement(updateString);

			updateVacancy.setString(1, vacancy.getHeading());
			updateVacancy.setString(2, vacancy.getDescription());
			updateVacancy.setDate(3, vacancy.getEndDate());
			updateVacancy.setString(4, req.getPosition());
			updateVacancy.setString(5, req.getLocation());
			updateVacancy.setString(6, req.getJobType());
			updateVacancy.setInt(7, vacancy.getId());
			
			updateVacancy.executeUpdate();
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
	
	public Vacancy getVacancy(int id) {
		PreparedStatement getCompany = null;
		Vacancy vacancy = null;
		Requirement req = null;
		String query = "select * from vacancies " + "where id = ?";

		try {
			getCompany = con.prepareStatement(query);
			getCompany.setInt(1, id);
			ResultSet resultSet = getCompany.executeQuery();

			if (resultSet.next()) {
				String heading = resultSet.getString("heading");
				int companyId = resultSet.getInt("company_id");
				String description = resultSet.getString("description");
				Date creation_date = resultSet.getDate("creation_date");
				Date expiry_date = resultSet.getDate("expiry_date");
				String position = resultSet.getString("position");
				String location = resultSet.getString("location");
				String job_type = resultSet.getString("job_type");	

				req = new Requirement(location, position, job_type);
				vacancy = new Vacancy(id, heading, description, companyId, req, creation_date, expiry_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacancy;
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
}
