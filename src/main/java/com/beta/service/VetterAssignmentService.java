package com.beta.service;

import java.util.List;

import com.beta.entity.Requirement;

public interface VetterAssignmentService {

	void initialVettersAssignmentByApplicationRef(String companyReferenceNumber, List<Requirement> listOfVetters);
}
