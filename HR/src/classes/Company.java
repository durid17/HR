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

	@Override
	public String toString() {
		return "Company [account=" + account + ", profile=" + profile + "]";
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
		Company other = (Company) obj;
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
