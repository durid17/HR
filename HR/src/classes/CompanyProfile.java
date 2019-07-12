package classes;

import java.sql.Date;

/**
 * The Class CompanyProfile.
 */
public class CompanyProfile {
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The essence. */
	private String essence;
	
	/** The founded. */
	private Date founded;
	
	/** The logo. */
	private String logo;
	
	/** The email. */
	private String email;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The address. */
	private String address;
	
	
	/**
	 * Instantiates a new company profile.
	 *
	 * @param name the name
	 * @param description the description
	 * @param essence the essence
	 * @param founded the founded
	 * @param logo the logo
	 * @param email the email
	 * @param phoneNumber the phone number
	 * @param address the address
	 */
	public CompanyProfile(String name, String description, String essence, Date founded, String logo, String email, String phoneNumber, String address) {
		this.setName(name);
		this.setDescription(description);
		this.setFounded(MyDateFormatter.truncate(founded));
		this.setLogo(logo);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
		this.setAddress(address);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		if(name == null) return "";
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		if(description == null) return "";
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the founded.
	 *
	 * @return the founded
	 */
	public Date getFounded() {
		return founded;
	}

	
	/**
	 * Sets the founded.
	 *
	 * @param founded the new founded
	 */
	public void setFounded(Date founded) {
		this.founded = founded;
	}

	
	/**
	 * Gets the logo.
	 *
	 * @return the logo
	 */
	public String getLogo() {
		if(logo == null) return "";
		return logo;
	}

	
	/**
	 * Sets the logo.
	 *
	 * @param logo the new logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		if(email == null) return "";
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		if(phoneNumber == null) return "";
		return phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		if(address == null) return "";
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the essence.
	 *
	 * @return the essence
	 */
	public String getEssence() {
		return essence;
	}

	/**
	 * Sets the essence.
	 *
	 * @param essence the new essence
	 */
	public void setEssence(String essence) {
		this.essence = essence;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CompanyProfile [name=" + name + ", description=" + description + ", essence=" + essence + ", founded="
				+ founded + ", logo=" + logo + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address="
				+ address + "]";
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
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((essence == null) ? 0 : essence.hashCode());
		result = prime * result + ((founded == null) ? 0 : founded.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
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
		CompanyProfile other = (CompanyProfile) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (essence == null) {
			if (other.essence != null)
				return false;
		} else if (!essence.equals(other.essence))
			return false;
		if (founded == null) {
			if (other.founded != null)
				return false;
		} else if (!founded.equals(other.founded))
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}
}
