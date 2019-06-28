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

	@Override
	public String toString() {
		return "Requirement [location=" + location + ", degree=" + degree + ", yearsOfExp=" + yearsOfExp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + yearsOfExp;
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
		Requirement other = (Requirement) obj;
		if (degree == null) {
			if (other.degree != null)
				return false;
		} else if (!degree.equals(other.degree))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (yearsOfExp != other.yearsOfExp)
			return false;
		return true;
	}
}