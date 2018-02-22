package com.beta.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.service.VetterAssignmentService;
import com.beta.services.ApplicationService;
import com.beta.services.EmployeeAccountService;
import com.beta.services.RequirementService;

@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DefaultVetterAssignment implements VetterAssignmentService {

	
	@Autowired
	ApplicationService applicationService;
	
	@Autowired
	RequirementService requirementService;
	
	@Autowired
	EmployeeAccountService employeeAccountService;
	
	@Override
	public void assignVettersByApplicationRef(String companyReferenceNumber, List<Requirement> listOfVetters) {
		String applicationRef = listOfVetters.get(0).getApplicationRef();
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		if (application.getApplicationStatus()!=ApplicationStatus.VETTING) {
			throw new VendorMgmtException("Attempting to set vetters when application status is no longer VETTING!");
		}
		for (Requirement r: listOfVetters) {
			if (!applicationRef.equals(r.getApplicationRef())) {
				throw new VendorMgmtException("List of Vetters do not contain the same Application Reference Number!");
			}
			if (employeeAccountService.findByUserName(r.getUserName())==null) {
				throw new VendorMgmtException("Username does not exist for vetting!");
			}
			r.setStatusUpdateDate(new Date());
			r.setStatus(ApprovalStatus.PENDING);
			requirementService.saveOrUpdate(r);
		}
		application.setApplicationStatus(ApplicationStatus.PENDING);
		applicationService.saveOrUpdate(application);
	}


}
