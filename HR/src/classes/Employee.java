package classes;

import java.util.List;

public class Employee {
	private int id;
	private Account account;
	private EmployeeProfile profile;
	
	public Employee(int id, Account account, EmployeeProfile profile) {
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

	public EmployeeProfile getProfile() {
		return profile;
	}

	public void setProfile(EmployeeProfile profile) {
		this.profile = profile;
	}

}
