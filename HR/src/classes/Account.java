package classes;

import java.sql.Date;

public class Account {
	
	private Date regDate;
	private String username;
	private String passHash;
	
	public Account(Date regDate, String username, String passHash) {
		this.regDate = regDate;
		this.username = username;
		this.passHash = passHash;
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
