package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Documents {
	
	@Id
	@GeneratedValue
	private Long documentId;
	
	private String applicationRef;
	
	private byte[] documents;

	public byte[] getDocuments() {
		return documents;
	}

	public void setDocuments(byte[] documents) {
		this.documents = documents;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Documents(byte[] bs) {
		super();
		this.documents = bs;
	}

	public Documents() {
		super();
	}

	public String getApplicationRef() {
		return applicationRef;
	}

	public void setApplicationRef(String applicationRef) {
		this.applicationRef = applicationRef;
	}
	
	
}