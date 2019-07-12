package classes;

import java.sql.Date;

/**
 * The Class Account.
 */
public class Account {
	
	/** The Constant EMPLOYEE_ACCOUNT_TYPE. */
	public static final String EMPLOYEE_ACCOUNT_TYPE = "Employee";
	
	/** The Constant COMPANY_ACCOUNT_TYPE. */
	public static final String COMPANY_ACCOUNT_TYPE = "Company";
	
	/** The Constant DEFAULT_ID. */
	public static final int DEFAULT_ID = 0;
	
	/** The id. */
	private int id;
	
	/** The user name. */
	private String username;
	
	/** The pass hash. */
	private String passHash;
	
	/** The register date. */
	private Date regDate;
	
	/** The account type. */
	private String accountType;
	
	
	/**
	 * Instantiates a new account.
	 *
	 * @param id the id
	 * @param regDate the register date
	 * @param username the user name
	 * @param passHash the pass hash
	 * @param accountType the account type
	 */
	public Account(int id, Date regDate, String username, String passHash, String accountType) {
		this.id = id;
		this.username = username;
		this.passHash = passHash;
		this.regDate = regDate;
		this.accountType = accountType;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return id;
	}

	/**
	 * Gets the register date.
	 *
	 * @return the register date
	 */
	public Date getRegDate() {
		return regDate;
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the pass hash.
	 *
	 * @return the pass hash
	 */
	public String getPassHash() {
		return passHash;
	}

	/**
	 * Sets the pass hash.
	 *
	 * @param passHash the new pass hash
	 */
	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	/**
	 * Gets the account type.
	 *
	 * @return the account type
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", passHash=" + passHash + ", regDate=" + regDate
				+ ", accountType=" + accountType + "]";
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
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + id;
		result = prime * result + ((passHash == null) ? 0 : passHash.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Account other = (Account) obj;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (id != other.id)
			return false;
		if (passHash == null) {
			if (other.passHash != null)
				return false;
		} else if (!passHash.equals(other.passHash))
			return false;
		if (regDate == null) {
			if (other.regDate != null)
				return false;
		} else if (!regDate.equals(other.regDate))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
