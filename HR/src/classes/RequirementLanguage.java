package classes;

public class RequirementLanguage {
	public static final int DEFAULT_ID = 0;
	
	private int id;
	private String language;
	private String quality;
	
	public RequirementLanguage(int id, String language, String quality) {
		this.id = id;
		this.language = language;
		this.quality = quality;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
		return "RequirementLanguage [id=" + id + ", language=" + language + ", quality=" + quality + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
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
		RequirementLanguage other = (RequirementLanguage) obj;
		if (id != other.id)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (quality == null) {
			if (other.quality != null)
				return false;
		} else if (!quality.equals(other.quality))
			return false;
		return true;
	}
}
