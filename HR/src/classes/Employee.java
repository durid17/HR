package classes;

import java.util.List;

public class Employee {
	
	private Account account;
	private EmployeeProfile profile;
	
	public Employee(Account account, EmployeeProfile profile) {
		this.account = account;
		this.profile = profile;
	}

	public int getId() {
		return account.getID();
	}

	public Account getAccount() {
		return account;
	}

	public EmployeeProfile getProfile() {
		return profile;
	}

	public void setProfile(EmployeeProfile profile) {
		this.profile = profile;
	}

}
