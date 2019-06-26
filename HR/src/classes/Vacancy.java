package classes;

import java.sql.Date;

public class Vacancy {
	private int id;
	private String heading; // prosta satauri
	private String position; // aq ra poziciaze edzebs tanamshromels
	private String description; 
	private String empType; // part-time, full-time
	private int companyId;
	private String jobType; // is it internship or not
	private Requirement req;
	private Date startDate;
	private Date endDate;
	
	
	public Vacancy(int id, String heading, String position, String description, String empType, 
			int companyId, String jobType, Requirement req, Date startDate, Date endDate) {
		this.id = id;
		this.heading = heading;
		this.position = position;
		this.description = description;
		this.empType = empType;
		this.companyId = companyId;
		this.jobType = jobType;
		this.req = req;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getHeading() {
		return heading;
	}
	
	public void setHeading(String heading) {
		this.heading = heading;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Requirement getReq() {
		return req;
	}
	
	public void setReq(Requirement req) {
		this.req = req;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public int getId() {
		return id;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
