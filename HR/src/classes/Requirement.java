package classes;


// TODO: Auto-generated Javadoc
/**
 * The Class Requirement.
 */
public class Requirement {
	
	/** The location. */
	private String location;
	
	/** The degree. */
	private String degree; // bachelor or something like that
	
	/** The profession. */
	private String profession;
	
	/** The years of exp. */
	private int yearsOfExp;
	
	/** The qualification 1. */
	private String qualification1;
	
	/** The qualification 2. */
	private String qualification2;
	
	/** The qualification 3. */
	private String qualification3;
	
	/**
	 * Instantiates a new requirement.
	 *
	 * @param location the location
	 * @param yearsOfExp the years of exp
	 * @param degree the degree
	 * @param prof the prof
	 * @param qualification1 the qualification 1
	 * @param qualification2 the qualification 2
	 * @param qualification3 the qualification 3
	 */
	public Requirement(String location,	int yearsOfExp, String degree, String prof, String qualification1, String qualification2, String qualification3) {
		this.location = location;
		this.yearsOfExp = yearsOfExp;
		this.degree = degree;
		this.profession = prof;
		this.qualification1 = qualification1;
		this.qualification2 = qualification2;
		this.qualification3 = qualification3;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the years of exp.
	 *
	 * @return the years of exp
	 */
	public int getYearsOfExp() {
		return yearsOfExp;
	}

	/**
	 * Sets the years of exp.
	 *
	 * @param yearsOfExp the new years of exp
	 */
	public void setYearsOfExp(int yearsOfExp) {
		this.yearsOfExp = yearsOfExp;
	}

	/**
	 * Gets the degree.
	 *
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * Sets the degree.
	 *
	 * @param degree the new degree
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	/**
	 * Gets the profession.
	 *
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}

	/**
	 * Sets the profession.
	 *
	 * @param profession the new profession
	 */
	public void setProfession(String profession) {
		this.profession = profession;
	}

	/**
	 * Gets the qualification 1.
	 *
	 * @return the qualification 1
	 */
	public String getQualification1() {
		return qualification1;
	}

	/**
	 * Sets the qualification 1.
	 *
	 * @param qualification1 the new qualification 1
	 */
	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	/**
	 * Gets the qualification 2.
	 *
	 * @return the qualification 2
	 */
	public String getQualification2() {
		return qualification2;
	}

	/**
	 * Sets the qualification 2.
	 *
	 * @param qualification2 the new qualification 2
	 */
	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	/**
	 * Gets the qualification 3.
	 *
	 * @return the qualification 3
	 */
	public String getQualification3() {
		return qualification3;
	}

	/**
	 * Sets the qualification 3.
	 *
	 * @param qualification3 the new qualification 3
	 */
	public void setQualification3(String qualification3) {
		this.qualification3 = qualification3;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Requirement [location=" + location + ", degree=" + degree + ", profession=" + profession
				+ ", yearsOfExp=" + yearsOfExp + ", qualification1=" + qualification1 + ", qualification2="
				+ qualification2 + ", qualification3=" + qualification3 + "]";
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
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((profession == null) ? 0 : profession.hashCode());
		result = prime * result + ((qualification1 == null) ? 0 : qualification1.hashCode());
		result = prime * result + ((qualification2 == null) ? 0 : qualification2.hashCode());
		result = prime * result + ((qualification3 == null) ? 0 : qualification3.hashCode());
		result = prime * result + yearsOfExp;
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
