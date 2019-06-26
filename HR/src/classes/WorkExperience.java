package classes;

import java.sql.Date;

public class WorkExperience {
	private int id;
	private Date startDate;
	private Date endDate;
	private String companyName;
	private String position;
	private String jobType;//is it internship or not
	private String employmentType; //full-time, part-time
	private String duty;
	private String achievement;
	
	public WorkExperience(int id, Date start, Date end, String company, String pos, 
			String type, String empType, String duty, String award) {
		this.id = id;
		this.startDate = start;
		this.endDate = end;
		this.companyName = company;
		this.position = pos;
		this.jobType = type;
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

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
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
	
}
