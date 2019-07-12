package classes;

/**
 * The Class Employee.
 */
public class Employee {
	
	/** The account. */
	private Account account;
	
	/** The profile. */
	private EmployeeProfile profile;
	
	/**
	 * Instantiates a new employee.
	 *
	 * @param account the account
	 * @param profile the profile
	 */
	public Employee(Account account, EmployeeProfile profile) {
		this.account = account;
		this.profile = profile;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return account.getID();
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * Gets the profile.
	 *
	 * @return the profile
	 */
	public EmployeeProfile getProfile() {
		return profile;
	}

	/**
	 * Sets the profile.
	 *
	 * @param profile the new profile
	 */
	public void setProfile(EmployeeProfile profile) {
		this.profile = profile;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Employee [account=" + account + ", profile=" + profile + "]";
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
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
