package classes;

import java.sql.Date;

/**
 * The Class EmployeeProfile.
 */
public class EmployeeProfile {
	
	/** The name. */
	private String name;
	
	/** The surname. */
	private String surname;
	
	/** The gender. */
	private String gender;
	
	/** The birth date. */
	private Date birthDate;
	
	/** The major profession. */
	private String majorProfession;
	
	/** The minor profession. */
	private String minorProfession;
	
	/** The email. */
	private String email;
	
	/** The phone number. */
	private String phoneNumber;
	
	/** The address. */
	private String address;
	
	/** The description. */
	private String description;
	
	/** The profile picture. */
	private String profilePicture;
	
	/** The is working. */
	private boolean isWorking; // works currently or not
	
	/**
	 * Instantiates a new employee profile.
	 *
	 * @param name the name
	 * @param surname the surname
	 * @param gender the gender
	 * @param birthDate the birth date
	 * @param majorProf the major prof
	 * @param minorProf the minor prof
	 * @param email the email
	 * @param phoneNumber the phone number
	 * @param address the address
	 * @param description the description
	 * @param profilePicture the profile picture
	 * @param isWorking the is working
	 */
	public EmployeeProfile(String name, String surname, String gender, Date birthDate, String majorProf,
				String minorProf, String email, String phoneNumber, String address, 
				String description, String profilePicture, boolean isWorking) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDate = MyDateFormatter.truncate(birthDate);
		this.majorProfession = majorProf;
		this.minorProfession = minorProf;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.description = description;
		this.setProfilePicture(profilePicture);
		this.isWorking = isWorking;
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
	 * Gets the surname.
	 *
	 * @return the surname
	 */
	public String getSurname() {
		if(surname == null) return "";
		return surname;
	}
	
	/**
	 * Sets the surname.
	 *
	 * @param surname the new surname
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the major profession.
	 *
	 * @return the major profession
	 */
	public String getMajorProfession() {
		if(majorProfession == null) return "";
		return majorProfession;
	}

	/**
	 * Sets the major profession.
	 *
	 * @param majorProfession the new major profession
	 */
	public void setMajorProfession(String majorProfession) {
		this.majorProfession = majorProfession;
	}

	/**
	 * Gets the minor profession.
	 *
	 * @return the minor profession
	 */
	public String getMinorProfession() {
		if(minorProfession == null) return "";
		return minorProfession;
	}

	/**
	 * Sets the minor profession.
	 *
	 * @param minorProfession the new minor profession
	 */
	public void setMinorProfession(String minorProfession) {
		this.minorProfession = minorProfession;
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
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		if(gender == null) return "";
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * Gets the birth date.
	 *
	 * @return the birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the birth date.
	 *
	 * @param birthDate the new birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Gets the profile picture.
	 *
	 * @return the profile picture
	 */
	public String getProfilePicture() {
		if(profilePicture == null) return "";
		return profilePicture;
	}

	/**
	 * Sets the profile picture.
	 *
	 * @param profilePicture the new profile picture
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * Checks if is working.
	 *
	 * @return true, if is working
	 */
	public boolean isWorking() {
		if(majorProfession == null) return false;
		return isWorking;
	}

	/**
	 * Sets the working.
	 *
	 * @param isWorking the new working
	 */
	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EmployeeProfile [name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthDate="
				+ birthDate + ", majorProfession=" + majorProfession + ", minorProfession=" + minorProfession
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", description="
				+ description + ", profilePicture=" + profilePicture + ", isWorking=" + isWorking + "]";
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
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + (isWorking ? 1231 : 1237);
		result = prime * result + ((majorProfession == null) ? 0 : majorProfession.hashCode());
		result = prime * result + ((minorProfession == null) ? 0 : minorProfession.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((profilePicture == null) ? 0 : profilePicture.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		EmployeeProfile other = (EmployeeProfile) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
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
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (isWorking != other.isWorking)
			return false;
		if (majorProfession == null) {
			if (other.majorProfession != null)
				return false;
		} else if (!majorProfession.equals(other.majorProfession))
			return false;
		if (minorProfession == null) {
			if (other.minorProfession != null)
				return false;
		} else if (!minorProfession.equals(other.minorProfession))
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
		if (profilePicture == null) {
			if (other.profilePicture != null)
				return false;
		} else if (!profilePicture.equals(other.profilePicture))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
}
