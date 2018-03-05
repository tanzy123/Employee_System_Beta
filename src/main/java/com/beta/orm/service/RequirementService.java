package com.beta.orm.service;

import java.util.List;

import com.beta.entity.ApplicationStatus;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;

public interface RequirementService extends BaseService<Long, Requirement> {

	List<Requirement> findByUsernameAndStatus(final String userName, final ApprovalStatus status);

	List<Requirement> findByApplicationRef(String applicationRef);

	List<Requirement> findByApplicationRefAndStatus(String applicationRef, ApprovalStatus status);
	
	public Requirement findByApplicationRefAndUser(String appRef, String user);

	public void removeVet(Long id);
}
