package com.beta.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.PurposeType;
import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Company;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.service.NotificationService;
import com.beta.service.VendorVettingProcess;
import com.beta.services.ApplicationService;
import com.beta.services.CompanyService;

@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DefaultVendorVettingProcess implements VendorVettingProcess {

	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	NotificationService notificationService;

	@Override
	public void vetVendor(String userName, Application application, ApprovalStatus updatedStatus, String requirements) throws Exception {
		List<Requirement> requirementList = application.getVettorRequirement();
		Requirement requirementToUpdate = validateUserAndStatusInRequirementList(userName, requirementList);

		requirementToUpdate.setRequirement(requirements);
		requirementToUpdate.setStatus(updatedStatus);
		requirementToUpdate.setStatusUpdateDate(Calendar.getInstance().getTime());
		
		application.setVettorRequirement(requirementList);
		try {
			if (!checkIfMoreValidationRequired(requirementList)) {
				
				sendApplicationToCompanyVendorManagement(application);
			}
		} finally {
			applicationService.saveOrUpdate(application);
		}
	}

	private void sendApplicationToCompanyVendorManagement(Application application) throws Exception {
		Company company = companyService.findbyRefNo(application.getCompanyReferenceNumber());
		String email = company.getCompanyEmail();
		List<Requirement> vetterRequirement = application.getVettorRequirement();
		String msg = getRequirements(vetterRequirement);
			String[] vettersEmail = {};
		notificationService.sendEmailWithPurposeCC(email, vettersEmail , "Vendor Acceptance", msg, application.getRemarks(), PurposeType.VendorApplicationAccepted);
	}

	private String getRequirements(List<Requirement> vettorRequirement) {
		String msg = "";
		for (Requirement r: vettorRequirement) {
			msg += r.getRequirement();
			msg += "\n";
		}
		return msg;
	}

	private boolean checkIfMoreValidationRequired(List<Requirement> requirementList) {
		return requirementList.stream()
				.anyMatch(r -> r.getStatus().equals(ApprovalStatus.PENDING));
	}

	private Requirement validateUserAndStatusInRequirementList(final String userName, List<Requirement> requirementList) {
		List<Requirement> filteredList = requirementList.stream()
				.filter(r -> r.getUserName().equals(userName) && r.getStatus().equals(ApprovalStatus.PENDING))
				.collect(Collectors.toList());
		
		if (filteredList.size() != 1)
			throw new VendorMgmtException("Username not found/Duplicate usernames in Requirement List");

		return filteredList.get(0);
	}

}
