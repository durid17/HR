package classes;


public class Requirement {
	
	private String location;
	private String degree; // bachelor or something like that
	private String profession;
	private int yearsOfExp;
	private String qualification1;
	private String qualification2;
	private String qualification3;
	
	public Requirement(String location,	int yearsOfExp, String degree, String prof, String qualification1, String qualification2, String qualification3) {
		this.location = location;
		this.yearsOfExp = yearsOfExp;
		this.degree = degree;
		this.profession = prof;
		this.qualification1 = qualification1;
		this.qualification2 = qualification2;
		this.qualification3 = qualification3;
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
	
	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	public String getQualification2() {
		return qualification2;
	}

	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	public String getQualification3() {
		return qualification3;
	}

	public void setQualification3(String qualification3) {
		this.qualification3 = qualification3;
	}

	@Override
	public String toString() {
		return "Requirement [location=" + location + ", degree=" + degree + ", profession=" + profession
				+ ", yearsOfExp=" + yearsOfExp + ", qualification1=" + qualification1 + ", qualification2="
				+ qualification2 + ", qualification3=" + qualification3 + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
		result = prime * result + ((qualification1 == null) ? 0 : qualification1.hashCode());
		result = prime * result + ((qualification2 == null) ? 0 : qualification2.hashCode());
		result = prime * result + ((qualification3 == null) ? 0 : qualification3.hashCode());
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
		if (profession == null) {
			if (other.profession != null)
				return false;
		} else if (!profession.equals(other.profession))
			return false;
		if (qualification1 == null) {
			if (other.qualification1 != null)
				return false;
		} else if (!qualification1.equals(other.qualification1))
			return false;
		if (qualification2 == null) {
			if (other.qualification2 != null)
				return false;
		} else if (!qualification2.equals(other.qualification2))
			return false;
		if (qualification3 == null) {
			if (other.qualification3 != null)
				return false;
		} else if (!qualification3.equals(other.qualification3))
			return false;
		if (yearsOfExp != other.yearsOfExp)
			return false;
		return true;
	}
}
