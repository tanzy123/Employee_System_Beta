package com.beta.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({
    @NamedQuery(name="Requirement.findByStatusAndUsername",
                query="SELECT r FROM Requirement r where r.userName = :userName AND r.status = :status"),
})
public class Requirement {
	
	@Id
	@GeneratedValue
	private Long requirementId;
	
	private String applicationRef;
	
	private String userName;
	
	private String requirement;
	
	@Temporal(TemporalType.DATE)
	private Date statusUpdateDate;
	
	private Integer sequence;
	
	@Enumerated(EnumType.STRING)
	private ApprovalStatus status;

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

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getStatusUpdateDate() {
		return statusUpdateDate;
	}

	public void setStatusUpdateDate(Date statusUpdateDate) {
		this.statusUpdateDate = statusUpdateDate;
	}

	public Requirement(String userName, String requirement, ApprovalStatus status) {
		super();
		this.userName = userName;
		this.requirement = requirement;
		this.status = status;
	}

	public Requirement() {
		super();
	}

	public String getApplicationRef() {
		return applicationRef;
	}

	public void setApplicationRef(String applicationRef) {
		this.applicationRef = applicationRef;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	
}
