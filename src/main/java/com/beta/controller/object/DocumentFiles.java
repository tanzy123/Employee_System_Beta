package com.beta.controller.object;

public class DocumentFiles {

	private String url;
	
	String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public DocumentFiles(String url, String filename) {
		super();
		this.url = url;
		this.filename = filename;
	}

	public DocumentFiles() {
		super();
	}
	
}
