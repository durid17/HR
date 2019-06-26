package classes;

import java.sql.Date;

public class CompanyProfile {
	private String name;
	private String description;
	private Date founded;
	private String logo;
	private String email;
	private String phoneNumber;
	private String address;
	
	
	public CompanyProfile(String name, String description, Date founded, String logo, String email, String phoneNumber, String address) {
		this.setName(name);
		this.setDescription(description);
		this.setFounded(founded);
		this.setLogo(logo);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getFounded() {
		return founded;
	}

	
	public void setFounded(Date founded) {
		this.founded = founded;
	}

	
	public String getLogo() {
		return logo;
	}

	
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	
	
}
