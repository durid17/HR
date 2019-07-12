package classes;

import java.sql.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkExperience.
 */
public class WorkExperience {
	
	/** The Constant DEFAULT_ID. */
	public static final int DEFAULT_ID = 0;
	
	/** The id. */
	private int id;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	/** The company name. */
	private String companyName;
	
	/** The profession. */
	private String profession;
	
	/** The position. */
	private String position;
	
	/** The employment type. */
	private String employmentType; //full-time, part-time, internship
	
	/** The duty. */
	private String duty;
	
	/** The achievement. */
	private String achievement;
	
	/**
	 * Instantiates a new work experience.
	 *
	 * @param id the id
	 * @param start the start
	 * @param end the end
	 * @param company the company
	 * @param prof the prof
	 * @param pos the pos
	 * @param empType the emp type
	 * @param duty the duty
	 * @param award the award
	 */
	public WorkExperience(int id, Date start, Date end, String company, String prof, String pos, 
			String empType, String duty, String award) {
		this.id = id;
		this.startDate = MyDateFormatter.truncate(start);
		this.endDate = MyDateFormatter.truncate(end);
		this.companyName = company;
		this.profession = prof;
		this.position = pos;
		this.employmentType = empType;
		this.duty = duty;
		this.achievement = award;
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
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	/**
	 * Gets the postition.
	 *
	 * @return the postition
	 */
	public String getPostition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Gets the duty.
	 *
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}
	
	/**
	 * Sets the duty.
	 *
	 * @param duty the new duty
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	/**
	 * Gets the achievement.
	 *
	 * @return the achievement
	 */
	public String getAchievement() {
		return achievement;
	}
	
	/**
	 * Sets the achievement.
	 *
	 * @param achievement the new achievement
	 */
	public void setAchievement(String achievement) {
		this.achievement = achievement;
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
	 * Gets the employment type.
	 *
	 * @return the employment type
	 */
	public String getEmploymentType() {
		return employmentType;
	}

	/**
	 * Sets the employment type.
	 *
	 * @param employmentType the new employment type
	 */
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	
	/**
	 * Gets the profession.
	 *
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * Sets the profession.
	 *
	 * @param profession the new profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "WorkExperience [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", companyName="
				+ companyName + ", position=" + position + ", employmentType=" + employmentType + ", duty=" + duty
				+ ", achievement=" + achievement + "]";
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
		result = prime * result + ((achievement == null) ? 0 : achievement.hashCode());
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((duty == null) ? 0 : duty.hashCode());
		result = prime * result + ((employmentType == null) ? 0 : employmentType.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
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
		WorkExperience other = (WorkExperience) obj;
		if (achievement == null) {
			if (other.achievement != null)
				return false;
		} else if (!achievement.equals(other.achievement))
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (duty == null) {
			if (other.duty != null)
				return false;
		} else if (!duty.equals(other.duty))
			return false;
		if (employmentType == null) {
			if (other.employmentType != null)
				return false;
		} else if (!employmentType.equals(other.employmentType))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id != other.id)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	
}
