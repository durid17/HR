package classes;

import java.sql.Date;

public class Account {
	
	public static final String EMPLOYEE_ACCOUNT_TYPE = "Employee";
	public static final String COMPANY_ACCOUNT_TYPE = "Company";
	public static final int DEFAULT_ID = 0;

	
	private int id;
	private String username;
	private String passHash;
	private Date regDate;
	private String accountType;
	
	public Account(int id, Date regDate, String username, String passHash, String accountType) {
		this.id = id;
		this.username = username;
		this.passHash = passHash;
		this.regDate = regDate;
		this.accountType = accountType;
	}
	
	public int getID() {
		return id;
	}

	public Date getRegDate() {
		return regDate;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public String getAccountType() {
		return accountType;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", passHash=" + passHash + ", regDate=" + regDate
				+ ", accountType=" + accountType + "]";
	}

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
