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
	
	public EmployeeProfile(String name, String surname, String gender, Date birthDate, String majorProf,
				String minorProf, String email, String phoneNumber, String address, String description, String profilePicture) {
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.birthDate = birthDate;
		this.majorProfession = majorProf;
		this.minorProfession = minorProf;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.description = description;
		this.setProfilePicture(profilePicture);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getMajorProfession() {
		return majorProfession;
	}

	public void setMajorProfession(String majorProfession) {
		this.majorProfession = majorProfession;
	}

	public String getMinorProfession() {
		return minorProfession;
	}

	public void setMinorProfession(String minorProfession) {
		this.minorProfession = minorProfession;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
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
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
		
}
