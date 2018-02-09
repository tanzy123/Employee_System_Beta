package com.beta.services;

import java.util.List;

import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;

public interface RequirementService extends BaseService<Long, Requirement> {

	List<Requirement> getRequirementByUsernameAndStatus(final String userName, final ApprovalStatus status);
}
