package classes;

import java.sql.Date;

public class Vacancy {
	public static final String PART_TIME_EMP_TYPE = "Part-time";
	public static final String FULL_TIME_EMP_TYPE = "Full-time";
	public static final String INTERNSHIP_EMP_TYPE = "Internship";
	
	private int id;
	private String heading; // prosta satauri
	private String position; // aq ra poziciaze edzebs tanamshromels
	private String description; 
	private String empType; // part-time, full-time
	private int companyId;
	private Requirement req;
	private Date startDate;
	private Date endDate;
	
	
	public Vacancy(int id, String heading, String position, String description, String empType, 
			int companyId, Requirement req, Date startDate, Date endDate) {
		this.id = id;
		this.heading = heading;
		this.position = position;
		this.description = description;
		this.empType = empType;
		this.companyId = companyId;
		this.req = req;
		this.startDate = startDate;
		this.endDate = MyDateFormatter.truncate(endDate);
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Vacancy [id=" + id + ", heading=" + heading + ", position=" + position + ", description=" + description
				+ ", empType=" + empType + ", companyId=" + companyId + ", req=" + req + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + companyId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((empType == null) ? 0 : empType.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((heading == null) ? 0 : heading.hashCode());
		result = prime * result + id;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + ((req == null) ? 0 : req.hashCode());
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
		Vacancy other = (Vacancy) obj;
		if (companyId != other.companyId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (empType == null) {
			if (other.empType != null)
				return false;
		} else if (!empType.equals(other.empType))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (heading == null) {
			if (other.heading != null)
				return false;
		} else if (!heading.equals(other.heading))
			return false;
		if (id != other.id)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (req == null) {
			if (other.req != null)
				return false;
		} else if (!req.equals(other.req))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
