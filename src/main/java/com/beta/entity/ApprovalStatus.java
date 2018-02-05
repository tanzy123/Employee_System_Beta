package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ApprovalStatus {
	
	@Id
	private Long statusId;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getApprovalId() {
		return statusId;
	}

	public void setApprovalId(Long approvalId) {
		this.statusId = approvalId;
	}
}
