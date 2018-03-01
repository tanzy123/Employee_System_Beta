package com.beta.service;

import java.util.List;

import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;

public interface VendorVettingProcess {

	void vetVendor(String userName, Application application, ApprovalStatus updatedStatus, String requirements) throws Exception;
	
	void initialVettersAssignmentByApplicationRef(String companyReferenceNumber, List<Requirement> listOfVetters) throws Exception;
}
