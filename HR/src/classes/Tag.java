package classes;

/**
 * The Class Tag.
 */
public class Tag {
	
	/** The Constant DEFAULT_ID. */
	public static final int DEFAULT_ID = 0;
	
	/** The id. */
	private int id;
	
	/** The tag name. */
	private String tagName;
	
	/**
	 * Instantiates a new tag.
	 *
	 * @param tagName the tag name
	 */
	public Tag(String tagName) {
		this.tagName = tagName;
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
	 * Gets the tag name.
	 *
	 * @return the tag name
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * Gets the tag name.
	 *
	 * @param tag the tag
	 * @return the tag name
	 */
	public void getTagName(String tag) {
		this.tagName = tag;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Tag [id=" + id + ", tag=" + tagName + "]";
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
		result = prime * result + id;
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
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
