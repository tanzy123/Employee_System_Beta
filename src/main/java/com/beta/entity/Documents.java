package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Documents {
	
	@Id
	private Long documentId;
	
	private Byte[] documents;

	public Byte[] getDocuments() {
		return documents;
	}

	public void setDocuments(Byte[] documents) {
		this.documents = documents;
	}
	
	
}
