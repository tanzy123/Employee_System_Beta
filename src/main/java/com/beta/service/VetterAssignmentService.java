package com.beta.service;

import java.util.List;

import com.beta.entity.Requirement;

public interface VetterAssignmentService {

	void assignVettersByApplicationRef(String companyReferenceNumber, List<Requirement> listOfVetters);
}
