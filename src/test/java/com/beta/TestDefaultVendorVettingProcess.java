package com.beta;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Company;
import com.beta.entity.Requirement;
import com.beta.service.VendorVettingProcess;
import com.beta.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestDefaultVendorVettingProcess {

	private static final String SAMPLE_USERNAME1 = "John";
	private static final String SAMPLE_USERNAME2 = "Mary";
	private List<Requirement> list = new ArrayList<>();
	

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private VendorVettingProcess vettingProcess;
	
	
	@Before
	public void initialize() {
		Company company = TestConstant.SAMPLE_COMPANY;
		company.setApplications(new ArrayList<>());
		companyService.saveOrUpdate(company);
	}
	
	@Test
	public void testSucessfulVetVendor() throws Exception {
		
		Application application = TestConstant.SAMPLE_APPLICATION1;
		application.setCompanyReferenceNumber(TestConstant.SAMPLE_COMPANY.getCompanyReferenceNumber());
		List<Requirement> list = new ArrayList<>();
		Requirement r = new Requirement(SAMPLE_USERNAME1, "asd", ApprovalStatus.PENDING);
		list.add(r);
		application.setVettorRequirement(list);
		vettingProcess.vetVendor(SAMPLE_USERNAME1, application, ApprovalStatus.APPROVE, "asd");
	}
}
