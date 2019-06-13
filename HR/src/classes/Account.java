package classes;

import java.sql.Date;

public class Account {
	
	public static final String EMPLOYEE_ACCOUNT_TYPE = "employee";
	public static final String COMPANY_ACCOUNT_TYPE = "company";
	
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

	
	
}
