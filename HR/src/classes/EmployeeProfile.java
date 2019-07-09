package classes;

import java.sql.Date;
import java.util.ArrayList;

public class EmployeeProfile {
	
	private String name;
	private String surname;
	private String gender;
	private Date birthDate;
	private String majorProfession;
	private String minorProfession;
	private String email;
	private String phoneNumber;
	private String address;
	private String description;
	private String profilePicture;
	private boolean isWorking; // works currently or not
	
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
	
	public String getName() {
		if(name == null) return "";
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		if(surname == null) return "";
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMajorProfession() {
		if(majorProfession == null) return "";
		return majorProfession;
	}

	public void setMajorProfession(String majorProfession) {
		this.majorProfession = majorProfession;
	}

	public String getMinorProfession() {
		if(minorProfession == null) return "";
		return minorProfession;
	}

	public void setMinorProfession(String minorProfession) {
		this.minorProfession = minorProfession;
	}

	public String getEmail() {
		if(email == null) return "";
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		if(phoneNumber == null) return "";
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		if(description == null) return "";
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		if(gender == null) return "";
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		if(address == null) return "";
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getProfilePicture() {
		if(profilePicture == null) return "";
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public boolean isWorking() {
		if(majorProfession == null) return false;
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}

	@Override
	public String toString() {
		return "EmployeeProfile [name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthDate="
				+ birthDate + ", majorProfession=" + majorProfession + ", minorProfession=" + minorProfession
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", description="
				+ description + ", profilePicture=" + profilePicture + ", isWorking=" + isWorking + "]";
	}

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
