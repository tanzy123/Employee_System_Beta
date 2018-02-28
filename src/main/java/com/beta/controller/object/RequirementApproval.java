package com.beta.controller.object;

import com.beta.entity.ApprovalStatus;

public class RequirementApproval {

	String requirements;

	String approvalStatus;

	ApprovalStatus status;
	
	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
		if (approvalStatus.equalsIgnoreCase("approve"))
			status = ApprovalStatus.APPROVE;
		else if (approvalStatus.equalsIgnoreCase("reject"))
			status = ApprovalStatus.REJECT;
	}

	public ApprovalStatus getStatus() {
		return status;
	}
	
	
}
