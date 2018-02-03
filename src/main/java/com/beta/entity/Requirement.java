package com.beta.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Requirement {
	
	@Id
	private Long requirementId;
	
	private String requirement;

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public Long getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(Long requirementId) {
		this.requirementId = requirementId;
	}
}
