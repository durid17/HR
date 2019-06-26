package classes;


public class Requirement {
	
	private String location;
	private String degree; // bachelor or something like that
	private int yearsOfExp;
	
	public Requirement(String location,	int yearsOfExp, String degree) {
		this.location = location;
		this.yearsOfExp = yearsOfExp;
		this.degree = degree;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

	public int getYearsOfExp() {
		return yearsOfExp;
	}

	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
}
