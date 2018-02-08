package com.beta.service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.services.ApplicationService;

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DefaultVendorVettingProcess implements VendorVettingProcess {

	@Autowired
	ApplicationService applicationService;

	@Override
	public void vetVendor(String userName, Application application, ApprovalStatus updatedStatus, String requirements) {
		List<Requirement> requirementList = application.getVettorRequirement();
		Requirement requirementToUpdate = validateUserInRequirementList(userName, requirementList);

		requirementToUpdate.setRequirement(requirements);
		requirementToUpdate.setStatus(updatedStatus);
		requirementToUpdate.setStatusUpdateDate(Calendar.getInstance().getTime());
		
		application.setVettorRequirement(requirementList);
		try {
			if (!checkIfMoreValidationRequired(requirementList)) {
				sendApplicationToCompanyVendorManagement(application);
			}
		} finally {
//			applicationService.saveOrUpdate(requirementToUpdate);
		}
	}

	private void sendApplicationToCompanyVendorManagement(Application application) {
		//Add notification service
	}

	private boolean checkIfMoreValidationRequired(List<Requirement> requirementList) {
		return requirementList.stream()
				.anyMatch(r -> r.getStatus()
				.equals(ApprovalStatus.PENDING));
	}

	private Requirement validateUserInRequirementList(final String userName, List<Requirement> requirementList) {
		List<Requirement> filteredList = requirementList.stream()
				.filter(r -> r.getUserName().equals(userName))
				.collect(Collectors.toList());
		
		if (filteredList.size() != 1)
			throw new VendorMgmtException("Username not found/Duplicate usernames in Requirement List");

		return filteredList.get(0);
	}

}
