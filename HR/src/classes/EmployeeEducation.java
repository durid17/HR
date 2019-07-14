package classes;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeEducation.
 */
public class EmployeeEducation {
	
	/** The Constant DEFAULT_ID. */
	public static final int DEFAULT_ID = 0;
	
	/** The id. */
	private int id;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The educational institution. */
	private String educationalInstitution;
	
	/** The institution name. */
	private String institutionName;
	
	/** The major. */
	private String major;
	
	/** The minor. */
	private String minor;
	
	/** The degree. */
	private String degree;
	
	/** The grade. */
	private double grade;
	
	/**
	 * Instantiates a new employee education.
	 *
	 * @param id the id
	 * @param start the start
	 * @param end the end
	 * @param educationalInstitution the educational institution
	 * @param institutionName the institution name
	 * @param major the major
	 * @param minor the minor
	 * @param degree the degree
	 * @param grade the grade
	 */
	public EmployeeEducation(int id, Date start, Date end, String educationalInstitution, 
			String institutionName, String major, String minor, String degree, double grade) {
		this.id = id;
		this.startDate = MyDateFormatter.truncate(start);
		this.endDate = MyDateFormatter.truncate(end);
		this.educationalInstitution = educationalInstitution;
		this.institutionName = institutionName;
		this.major = major;
		this.minor = minor;
		this.degree = degree;
		this.grade = grade;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the educational institution.
	 *
	 * @return the educational institution
	 */
	public String getEducationalInstitution() {
		return educationalInstitution;
	}

	/**
	 * Sets the educational institution.
	 *
	 * @param educationalInstitution the new educational institution
	 */
	public void setEducationalInstitution(String educationalInstitution) {
		this.educationalInstitution = educationalInstitution;
	}

	/**
	 * Gets the institution name.
	 *
	 * @return the institution name
	 */
	public String getInstitutionName() {
		return institutionName;
	}

	/**
	 * Sets the institution name.
	 *
	 * @param institutionName the new institution name
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	/**
	 * Gets the degree.
	 *
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * Sets the degree.
	 *
	 * @param degree the new degree
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * Gets the grade.
	 *
	 * @return the grade
	 */
	public double getGrade() {
		return grade;
	}

	/**
	 * Sets the grade.
	 *
	 * @param grade the new grade
	 */
	public void setGrade(double grade) {
		this.grade = grade;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the major.
	 *
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * Sets the major.
	 *
	 * @param major the new major
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * Gets the minor.
	 *
	 * @return the minor
	 */
	public String getMinor() {
		return minor;
	}

	/**
	 * Sets the minor.
	 *
	 * @param minor the new minor
	 */
	public void setMinor(String minor) {
		this.minor = minor;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EmployeeEducation [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", educationalInstitution=" + educationalInstitution + ", institutionName=" + institutionName
				+ ", major=" + major + ", minor=" + minor + ", degree=" + degree + ", grade=" + grade + "]";
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
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((educationalInstitution == null) ? 0 : educationalInstitution.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(grade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((institutionName == null) ? 0 : institutionName.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((minor == null) ? 0 : minor.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		EmployeeEducation other = (EmployeeEducation) obj;
		if (degree == null) {
			if (other.degree != null)
				return false;
		} else if (!degree.equals(other.degree))
			return false;
		if (educationalInstitution == null) {
			if (other.educationalInstitution != null)
				return false;
		} else if (!educationalInstitution.equals(other.educationalInstitution))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (Double.doubleToLongBits(grade) != Double.doubleToLongBits(other.grade))
			return false;
		if (id != other.id)
			return false;
		if (institutionName == null) {
			if (other.institutionName != null)
				return false;
		} else if (!institutionName.equals(other.institutionName))
			return false;
		if (major == null) {
			if (other.major != null)
				return false;
		} else if (!major.equals(other.major))
			return false;
		if (minor == null) {
			if (other.minor != null)
				return false;
		} else if (!minor.equals(other.minor))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
