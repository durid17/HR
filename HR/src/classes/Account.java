package classes;

import java.sql.Date;

public class Account {
	
	private String username;
	private String passHash;
	private Date regDate;
	
	public Account(Date regDate, String username, String passHash) {
		this.username = username;
		this.passHash = passHash;
		this.regDate = regDate;
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
	
	
}
