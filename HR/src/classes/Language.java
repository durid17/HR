package classes;

public class Language {
	

	private String language;
	private String quality;
	private String certificate;
	
	public Language(String language, String quality, String certificate) {
		this.language = language;
		this.quality = quality;
		this.certificate = certificate;
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

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}


}
