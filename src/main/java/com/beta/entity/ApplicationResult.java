package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ApplicationResult {
	
	@Id
	@GeneratedValue
	private Long applicationResultId;

	@OneToOne
	@JoinColumn(name = "applicationId")
	private Application application;
	
	private String remarks;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getApplicationId() {
		return applicationResultId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationResultId = applicationId;
	}
}
