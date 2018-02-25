package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="Documents.findByApplicationRef",
                query="SELECT d FROM Documents d where d.applicationRef = :applicationRef"),
    @NamedQuery(name="Documents.findByApplicationRefAndFilePath",
    query="SELECT d FROM Documents d where d.applicationRef = :applicationRef AND d.filePath = :filePath"),
})
public class Documents {
	
	@Id
	@GeneratedValue
	private Long documentId;
	
	private String applicationRef;
	
	private String filePath;
	
	private String originalFileName;
	
	private String remarks;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getApplicationRef() {
		return applicationRef;
	}

	public void setApplicationRef(String applicationRef) {
		this.applicationRef = applicationRef;
	}
	
	
}