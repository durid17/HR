package classes;

public class Tag {
	public static final int DEFAULT_ID = 0;
	
	private int id;
	private String tagName;
	
	public Tag(String tagName) {
		this.tagName = tagName;
	}
	
	public int getId() {
		return id;
	}

	public String getTagName() {
		return tagName;
	}

	public void getTagName(String tag) {
		this.tagName = tag;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", tag=" + tagName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}
}
