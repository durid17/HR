package classes;

import java.util.List;

public class DBManager {

	//
	public void addAccount (Account account) {
		
	}
	
	public void updateAccount(Account account) {
		
	}
	
	public void deleteAccount(Account account) { 
		
	}
	
	public Account getAccount(String username) {
		
		return null;
	}
	
	//
	public void updateCompany(Company company) {
		
	}
	
	public Company getCompany(Account account) {
		
		return null;
	}
	
	public List<Vacancy> getCompanyVacancies(int companyId) {
		
		return null;
	}
	
	
	//
	public Employee getEmployee(Account account) {
		
		return null;
	}
	
	public void updateEmployee(Employee employee) {
		
	}
	
	public List<Vacancy> getEmployeeApplications(int employeeId) {
		
		return null;
	}
	
	public List<Company> getEmployeeFollowing(int employeeId) {	
		
		return null;
	}
	
	
	//
	public void addVacancy(Vacancy vacancy) {
		
	}
	
	public void updateVacancy(Vacancy vacancy) {
		
	}
	
	public void deleteVacancy(Vacancy vacancy) {
		
	}
	
	
	//
	public void addFollower(int companyId, int employeeId) {
		
	}
	
	public void addApplication(int employeeId, int vacancyId) {
		
	}
	
	
	
	
	
}
