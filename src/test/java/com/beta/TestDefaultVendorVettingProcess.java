package com.beta;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;
import com.beta.service.DefaultVendorVettingProcess;
import com.beta.services.ApplicationService;

@RunWith(MockitoJUnitRunner.class)
public class TestDefaultVendorVettingProcess {

	private static final String SAMPLE_USERNAME1 = "John";
	private static final String SAMPLE_USERNAME2 = "Mary";
	private Application application;
	private List<Requirement> list = new ArrayList<>();
	

	@Mock
	private ApplicationService applicationServiceMock;
	
	@InjectMocks
	private DefaultVendorVettingProcess vettingProcess;
	
	
	@Before
	public void initialize() {
		MockitoAnnotations.initMocks(this);
		Requirement requirement = new Requirement();
		requirement.setRequirementId(1L);
		requirement.setStatus(ApprovalStatus.PENDING);
		requirement.setUserName(SAMPLE_USERNAME1);
		list.add(requirement);
		Requirement requirement2 = new Requirement();
		requirement2.setRequirementId(2L);
		requirement2.setStatus(ApprovalStatus.PENDING);
		requirement2.setUserName(SAMPLE_USERNAME2);
		list.add(requirement2);
		application = new Application();
		application.setVettorRequirement(list);
		
	}
	
	@Test
	public void testSucessfulVetVendor() {
		vettingProcess.vetVendor(SAMPLE_USERNAME1, application, ApprovalStatus.APPROVE, "asd");
		
	}
}
