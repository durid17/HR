package classes;

import java.sql.Date;

public class CompanyProfile {
	private String name;
	private String description;
	private Date founded;
	private String logo;
	
	
	public CompanyProfile(String name, String description, Date founded, String logo) {
		this.setName(name);
		this.setDescription(description);
		this.setFounded(founded);
		this.setLogo(logo);
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
	
	
	
	
	
}
