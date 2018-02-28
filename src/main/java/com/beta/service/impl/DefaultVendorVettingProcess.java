package com.beta.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Application;                                              
import com.beta.entity.ApplicationStatus;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Company;
import com.beta.entity.EmailPurposeType;
import com.beta.entity.EmployeeAccount;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.service.NotificationService;
import com.beta.service.VendorVettingProcess;
import com.beta.services.ApplicationService;
import com.beta.services.CompanyService;
import com.beta.services.EmployeeAccountService;
import com.beta.services.RequirementService;

@Service
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DefaultVendorVettingProcess implements VendorVettingProcess {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	CompanyService companyService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	RequirementService requirementService;
	
	@Autowired
	EmployeeAccountService employeeAccountService;

	@Override
	public void vetVendor(String userName, Application application, ApprovalStatus updatedStatus, String requirements)
			throws Exception {
		List<Requirement> requirementList = requirementService.findByApplicationRef(application.getApplicationRef());
		Requirement requirementToUpdate = validateUserAndApprovalStatusInRequirementList(userName, requirementList);

		requirementToUpdate.setRequirement(requirements);
		requirementToUpdate.setStatus(updatedStatus);
		requirementToUpdate.setStatusUpdateDate(Calendar.getInstance().getTime());

		if (updatedStatus == ApprovalStatus.APPROVE) {
			if (!checkIfMoreValidationRequired(requirementList)) {
				application.setApplicationStatus(ApplicationStatus.APPROVE);
				sendApplicationToVendor(application);
				applicationService.saveOrUpdate(application);
			} else {
				String nextVetterUsername = updateNextVettorToPending(requirementList, userName);
				sendApplicationToNextVetter(nextVetterUsername);
			}

		} else if (updatedStatus == ApprovalStatus.REJECT) {
			application.setApplicationStatus(ApplicationStatus.REJECT);
			sendApplicationToVendor(application);
			applicationService.saveOrUpdate(application);
		} else
			throw new VendorMgmtException("Vetters can only approve or reject application!");
	}

	private void sendApplicationToNextVetter(String nextVetterUsername) throws Exception{
		EmployeeAccount employeeAccount = employeeAccountService.findByUserName(nextVetterUsername);
		if (employeeAccount == null)
			throw new VendorMgmtException("Requirement contains non-existing username!");
		String email = employeeAccount.getEmployeeEmail();
		String cc[] = {};
		String msg = "Please log in to vet a new Vendor Application Request";
		String msgFromPreviousVetter = "";
		notificationService.sendEmailWithPurposeCC(email, cc, "Vendor Vetting Notification", msg, msgFromPreviousVetter
				, EmailPurposeType.SendToNextEmployeeVettor);
	}

	private String updateNextVettorToPending(List<Requirement> requirementList, String userName) {
		Integer currentSequence = requirementList.stream().filter(req -> req.getUserName().equals(userName)).findFirst()
				.map(r -> r.getSequence()).get();
		Integer nextSequence = currentSequence + 1;
		Requirement nextVetter = requirementList.stream().filter(req -> req.getSequence().equals(nextSequence))
				.findFirst().get();
		nextVetter.setStatus(ApprovalStatus.PENDING);
		return nextVetter.getUserName();
	}

	private void sendApplicationToVendor(Application application) throws Exception {
		Company vendor = companyService.findbyRefNo(application.getVendorReferenceNumber());
		String email = vendor.getCompanyEmail();
		List<Requirement> vetterRequirement = requirementService.findByApplicationRef(application.getApplicationRef());
		String msg = getRequirements(vetterRequirement, application.getApplicationStatus());
		String[] cc = {};
		EmailPurposeType purposeType = application.getApplicationStatus().equals(ApplicationStatus.APPROVE)
				? EmailPurposeType.VendorApplicationAccepted
				: EmailPurposeType.VendorApplicationRejected;
		notificationService.sendEmailWithPurposeCC(email, cc, "Vendor Application Status", msg,
				application.getApplicationStatus().toString(), purposeType);
	}

	private String getRequirements(List<Requirement> vetterRequirement, ApplicationStatus status) {
		String msg = "";
		switch (status) {
		case APPROVE:
			for (Requirement r : vetterRequirement) {
				if (r.getRequirement() != null)
					msg += r.getRequirement() + System.lineSeparator();
			}
			return msg;
		case REJECT:
			Requirement r = vetterRequirement.stream().filter(req -> (req.getStatus() == ApprovalStatus.REJECT))
					.findFirst().get();
			msg += r.getRequirement() + System.lineSeparator();
			return msg;
		default:
			throw new VendorMgmtException("Wrong application status type input");
		}
	}

	private boolean checkIfMoreValidationRequired(List<Requirement> requirementList) {
		return requirementList.stream().anyMatch(
				r -> r.getStatus().equals(ApprovalStatus.WAITING) || r.getStatus().equals(ApprovalStatus.PENDING));
	}

	private Requirement validateUserAndApprovalStatusInRequirementList(final String userName,
			List<Requirement> requirementList) {
		List<Requirement> filteredList = requirementList.stream()
				.filter(r -> r.getUserName().equals(userName) && r.getStatus().equals(ApprovalStatus.PENDING))
				.collect(Collectors.toList());

		if (filteredList.size() != 1)
			throw new VendorMgmtException("Username not found/Duplicate usernames in Requirement List");

		return filteredList.get(0);
	}

	public void validateList(List<Requirement> list) {
		list.sort((r1, r2) -> r1.getSequence() - r2.getSequence());
		for (int i = 1; i <= list.size(); i++) {
			if (list.get(i - 1).getSequence() != i)
				throw new VendorMgmtException("Sequence is not valid");
		}
	}

	@Override
	public void initialVettersAssignmentByApplicationRef(String companyReferenceNumber,
			List<Requirement> listOfVetters) throws Exception {
		validateList(listOfVetters);
		String applicationRef = listOfVetters.get(0).getApplicationRef();
		Application application = applicationService.findByApplicationRefNo(applicationRef);
		if (application.getApplicationStatus() != ApplicationStatus.VETTING) {
			throw new VendorMgmtException("Attempting to set vetters when application status is no longer VETTING!");
		}
		if (application.getCompanyReferenceNumber().equals(companyReferenceNumber))
			throw new VendorMgmtException("Company Reference Number doesn't match Application's Company Reference Number");
		generateRequirements(listOfVetters, applicationRef);
		application.setApplicationStatus(ApplicationStatus.PENDING);
		applicationService.saveOrUpdate(application);
		
	}

	private void generateRequirements(List<Requirement> listOfVetters, String applicationRef) throws Exception {
		for (Requirement r : listOfVetters) {
			if (!applicationRef.equals(r.getApplicationRef())) {
				throw new VendorMgmtException("List of Vetters do not contain the same Application Reference Number!");
			}
			if (employeeAccountService.findByUserName(r.getUserName()) == null) {
				throw new VendorMgmtException("Username does not exist for vetting!");
			}
			r.setStatusUpdateDate(new Date());
			if (r.getSequence().equals(1)) {
				r.setStatus(ApprovalStatus.PENDING);
				sendApplicationToNextVetter(r.getUserName());
			} else
				r.setStatus(ApprovalStatus.WAITING);
			requirementService.saveOrUpdate(r);
		}
	}
}
