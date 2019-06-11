package classes;

import java.util.List;

public class Company {

	private int id;
	private Account account;
	private CompanyProfile profile;
	
	public Company(int id, Account account, CompanyProfile profile) {
		this.id = id;
		this.account = account;
		this.profile = profile;
	}

	public int getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public CompanyProfile getProfile() {
		return profile;
	}

	public void setProfile(CompanyProfile profile) {
		this.profile = profile;
	}
	
	
}
