package classes;

import java.util.List;

public class Company {

	private int id;
	private Account account;
	private CompanyProfile profile;
	private List<Vacancy> vacancies;
	
	public Company(int id, Account account, CompanyProfile profile, List<Vacancy> vacancies) {
		this.id = id;
		this.account = account;
		this.profile = profile;
		this.vacancies = vacancies;
	}

	public int getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public List<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(List<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}

	public CompanyProfile getProfile() {
		return profile;
	}

	public void setProfile(CompanyProfile profile) {
		this.profile = profile;
	}
	
	
	
	
	
	
	
	
}
