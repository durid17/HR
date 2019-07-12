package classes;

import java.sql.Date;

/**
 * The Class Vacancy.
 */
public class Vacancy {
	
	/** The Constant PART_TIME_EMP_TYPE. */
	public static final String PART_TIME_EMP_TYPE = "Part-time";
	
	/** The Constant FULL_TIME_EMP_TYPE. */
	public static final String FULL_TIME_EMP_TYPE = "Full-time";
	
	/** The Constant INTERNSHIP_EMP_TYPE. */
	public static final String INTERNSHIP_EMP_TYPE = "Internship";
	
	/** The id. */
	private int id;
	
	/** The heading. */
	private String heading;
	
	/** The position. */
	private String position;
	
	/** The description. */
	private String description; 
	
	/** The emp type. */
	private String empType;
	
	/** The company id. */
	private int companyId;
	
	/** The req. */
	private Requirement req;
	
	/** The start date. */
	private Date startDate;
	
	/** The end date. */
	private Date endDate;
	
	
	/**
	 * Instantiates a new vacancy.
	 *
	 * @param id the id
	 * @param heading the heading
	 * @param position the position
	 * @param description the description
	 * @param empType the emp type
	 * @param companyId the company id
	 * @param req the req
	 * @param startDate the start date
	 * @param endDate the end date
	 */
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
	
	/**
	 * Gets the heading.
	 *
	 * @return the heading
	 */
	public String getHeading() {
		return heading;
	}
	
	/**
	 * Sets the heading.
	 *
	 * @param heading the new heading
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the req.
	 *
	 * @return the req
	 */
	public Requirement getReq() {
		return req;
	}
	
	/**
	 * Sets the req.
	 *
	 * @param req the new req
	 */
	public void setReq(Requirement req) {
		this.req = req;
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
	 * Gets the company id.
	 *
	 * @return the company id
	 */
	public int getCompanyId() {
		return companyId;
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
	 * Gets the emp type.
	 *
	 * @return the emp type
	 */
	public String getEmpType() {
		return empType;
	}

	/**
	 * Sets the emp type.
	 *
	 * @param empType the new emp type
	 */
	public void setEmpType(String empType) {
		this.empType = empType;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public String getPosition() {
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Vacancy [id=" + id + ", heading=" + heading + ", position=" + position + ", description=" + description
				+ ", empType=" + empType + ", companyId=" + companyId + ", req=" + req + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
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
