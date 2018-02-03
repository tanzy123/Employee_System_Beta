package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Official {

	@Id
	private String officialId;
	
	private String remarks;

	public String getOfficialId() {
		return officialId;
	}

	public void setOfficialId(String officialId) {
		this.officialId = officialId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
