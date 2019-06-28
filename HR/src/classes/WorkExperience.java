package classes;

import java.sql.Date;

public class WorkExperience {
	public static final int DEFAULT_ID = 0;
	
	private int id;
	private Date startDate;
	private Date endDate;
	private String companyName;
	private String position;
	private String employmentType; //full-time, part-time, internship
	private String duty;
	private String achievement;
	
	public WorkExperience(int id, Date start, Date end, String company, String pos, 
			String empType, String duty, String award) {
		this.id = id;
		this.startDate = MyDateFormatter.truncate(start);
		this.endDate = MyDateFormatter.truncate(end);
		this.companyName = company;
		this.position = pos;
		this.employmentType = empType;
		this.duty = duty;
		this.achievement = award;
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
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getPostition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public int getId() {
		return id;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	@Override
	public String toString() {
		return "WorkExperience [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", companyName="
				+ companyName + ", position=" + position + ", employmentType=" + employmentType + ", duty=" + duty
				+ ", achievement=" + achievement + "]";
	}

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
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
