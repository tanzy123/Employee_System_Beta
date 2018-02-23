package com.beta.services;

import java.util.List;

import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;

public interface ApplicationService extends BaseService<Long, Application> {
	Application findByApplicationRefNo(String applicationRef);

	List<Application> findByStatusAndCompRef(ApplicationStatus status, String companyReferenceNumber);

	List<Application> findByStatusAndVendorRef(ApplicationStatus applicationStatus, String vendorReferenceNumber);

	List<Application> findByCompRef(String companyReferenceNumber);

	List<Application> findByVendorRef(String vendorReferenceNumber);
}
