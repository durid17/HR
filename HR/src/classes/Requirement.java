package classes;


public class Requirement {
	
	private int id;
	private String location;
	private String tag;
	private String degree; // bachelor or something like that
	private Language language;
	private int yearsOfExp;
	
	public Requirement(int id, String location, String tag, Language language,
			int yearsOfExp, String degree) {
		this.setId(id);
		this.location = location;
		this.tag = tag;
		this.language = language;
		this.yearsOfExp = yearsOfExp;
		this.degree = degree;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
