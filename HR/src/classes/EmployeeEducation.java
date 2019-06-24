package classes;

import java.sql.Date;

public class EmployeeEducation {
	private int id;
	private Date startDate;
	private Date endDate;
	private String educationalInstitution;
	private String institutionName;
	private String degree;
	private String grade;
	private Language language;
	
	public EmployeeEducation(int id, Date start, Date end, String educationalInstitution, 
			String institutionName, String degree, String grade, Language language) {
		this.id = id;
		this.startDate = start;
		this.endDate = end;
		this.educationalInstitution = educationalInstitution;
		this.institutionName = institutionName;
		this.degree = degree;
		this.grade = grade;
		this.language = language;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEducationalInstitution() {
		return educationalInstitution;
	}

	public void setEducationalInstitution(String educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public int getId() {
		return id;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
