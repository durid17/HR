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

	@Override
	public String toString() {
		return "Employee [account=" + account + ", profile=" + profile + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}
}
