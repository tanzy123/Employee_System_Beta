package com.beta;

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
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CompanyService;
import com.beta.orm.service.RequirementService;
import com.beta.service.VendorVettingProcess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestDefaultVendorVettingProcess {

	private static final String SAMPLE_USERNAME1 = "John";

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private VendorVettingProcess vettingProcess;
	
	@Autowired
	private RequirementService requirementService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Before
	public void initialize() {
		Company company = TestConstant.SAMPLE_COMPANY;
		companyService.saveOrUpdate(company);
	}
	
	@Test
	public void testApproveVetVendor() throws Exception {
		
		Application application = TestConstant.SAMPLE_APPLICATION1;
		application.setCompanyReferenceNumber(TestConstant.SAMPLE_COMPANY.getCompanyReferenceNumber());
		applicationService.saveOrUpdate(application);
		Company vendor = new Company();
		vendor.setCompanyEmail("ZhiYi.Tan@cognizant.com");
		vendor.setCompanyReferenceNumber(application.getVendorReferenceNumber());
		vendor.setCompanyName("sampleVendor");
		vendor.setCompanyAddress("Singapore");
		vendor.setContactNumber("12312345435");
		vendor.setTurnover(2L);
		companyService.saveOrUpdate(vendor);
		Requirement r1 = new Requirement(SAMPLE_USERNAME1, "", ApprovalStatus.PENDING);
		r1.setSequence(1);
		r1.setApplicationRef(TestConstant.SAMPLE_APPLICATION1.getApplicationRef());
		requirementService.saveOrUpdate(r1);
		vettingProcess.vetVendor(SAMPLE_USERNAME1, application, ApprovalStatus.APPROVE, "qweqweqwe");
	}
	
	@Test
	public void testRejectVetVendor() throws Exception {
		
		Application application = TestConstant.SAMPLE_APPLICATION1;
		application.setCompanyReferenceNumber(TestConstant.SAMPLE_COMPANY.getCompanyReferenceNumber());
		applicationService.saveOrUpdate(application);
		Company vendor = new Company();
		vendor.setCompanyEmail("ZhiYi.Tan@cognizant.com");
		vendor.setCompanyReferenceNumber(application.getVendorReferenceNumber());
		vendor.setCompanyName("sampleVendor");
		vendor.setCompanyAddress("Singapore");
		vendor.setContactNumber("12312345435");
		vendor.setTurnover(2L);
		companyService.saveOrUpdate(vendor);
		Requirement r1 = new Requirement(SAMPLE_USERNAME1, "", ApprovalStatus.PENDING);
		r1.setSequence(1);
		r1.setApplicationRef(TestConstant.SAMPLE_APPLICATION1.getApplicationRef());
		requirementService.saveOrUpdate(r1);
		vettingProcess.vetVendor(SAMPLE_USERNAME1, application, ApprovalStatus.REJECT, "qweqweqwe");
	}
}
