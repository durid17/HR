package classes;

import java.util.List;

public class Company {

	private Account account;
	private CompanyProfile profile;
	
	public Company(Account account, CompanyProfile profile) {
		this.account = account;
		this.profile = profile;
	}

	public int getId() {
		return account.getID();
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
