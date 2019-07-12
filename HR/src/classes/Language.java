package classes;

/**
 * The Class Language.
 */
public class Language {
	
	/** The Constant DEFAULT_ID. */
	public static final int DEFAULT_ID = 0;
	
	/** The id. */
	private int id;
	
	/** The language. */
	private String language;
	
	/** The quality. */
	private String quality;
	
	/** The certificate. */
	private String certificate;
	
	/**
	 * Instantiates a new language.
	 *
	 * @param id the id
	 * @param language the language
	 * @param quality the quality
	 * @param certificate the certificate
	 */
	public Language(int id, String language, String quality, String certificate) {
		this.id = id;
		this.language = language;
		this.quality = quality;
		this.certificate = certificate;
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
	 * Gets the language.
	 *
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets the quality.
	 *
	 * @return the quality
	 */
	public String getQuality() {
		return quality;
	}

	/**
	 * Sets the quality.
	 *
	 * @param quality the new quality
	 */
	public void setQuality(String quality) {
		this.quality = quality;
	}

	/**
	 * Gets the certificate.
	 *
	 * @return the certificate
	 */
	public String getCertificate() {
		return certificate;
	}

	/**
	 * Sets the certificate.
	 *
	 * @param certificate the new certificate
	 */
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Language [id=" + id + ", language=" + language + ", quality=" + quality + ", certificate=" + certificate
				+ "]";
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
		result = prime * result + ((certificate == null) ? 0 : certificate.hashCode());
		result = prime * result + id;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((quality == null) ? 0 : quality.hashCode());
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
		Language other = (Language) obj;
		if (certificate == null) {
			if (other.certificate != null)
				return false;
		} else if (!certificate.equals(other.certificate))
			return false;
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
