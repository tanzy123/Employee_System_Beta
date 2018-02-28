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
    @NamedQuery(name="Requirement.findByApplicationRefAndUsername",
    query="SELECT r FROM Requirement r where r.userName = :userName AND r.applicationRef = :applicationRef"),
    @NamedQuery(name="Requirement.findByApplicationRef",
    query="SELECT r FROM Requirement r where r.applicationRef = :applicationRef"),
    @NamedQuery(name="Requirement.findByApplicationRefAndStatus",
    query="SELECT r FROM Requirement r where r.applicationRef = :applicationRef AND r.status = :status"),
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

	@Override
	public String toString() {
		return "Requirement [requirementId=" + requirementId + ", applicationRef=" + applicationRef + ", userName="
				+ userName + ", requirement=" + requirement + ", statusUpdateDate=" + statusUpdateDate + ", sequence="
				+ sequence + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((applicationRef == null) ? 0 : applicationRef.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requirement other = (Requirement) obj;
		if (applicationRef == null) {
			if (other.applicationRef != null)
				return false;
		} else if (!applicationRef.equals(other.applicationRef))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
}
