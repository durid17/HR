package classes;


public class Requirement {
	
	private String location;
	private String position;
	private String jobType;
	
	public Requirement(String location, String position, String jobType) {
		this.location = location;
		this.position = position;
		this.jobType = jobType;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getJobType() {
		return jobType;
	}
	
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
}
