package classes;

import java.sql.Date;

public class Vacancy {
	private int id;
	private String heading;
	private String description;
	private int companyId;
	private Requirement req;
	private Date startDate;
	private Date endDate;
	
	
	public Vacancy(int id, String heading, String description, int companyId, Requirement req, Date startDate, Date endDate) {
		this.id = id;
		this.heading = heading;
		this.description = description;
		this.companyId = companyId;
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
	
}