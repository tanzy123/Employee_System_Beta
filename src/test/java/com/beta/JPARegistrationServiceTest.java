package com.beta;

import java.text.ParseException;
import java.util.ArrayList;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static com.beta.TestConstant.*;
import static org.junit.Assert.*;

import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.Department;
import com.beta.entity.Role;
import com.beta.exception.VendorMgmtException;
import com.beta.service.CompanyValidation;
import com.beta.service.VendorApplication;
import com.beta.services.ApplicationService;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;
import com.beta.unused.RegistrationAndApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPARegistrationServiceTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	RegistrationAndApplication RegApp;
	
	@Autowired
	ApplicationService appServ;
	
	@Autowired
	VendorApplication venApp;
	
	@Autowired
	CompanyValidation comValid;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	CategoryService catServ;
	
	@Before
	public void initialize() {
		companyService.saveOrUpdate(SAMPLE_COMPANY);
	/*	Category cat = new Category();
		cat.setCategoryName(SAMPLE_CATEGORY1.getCategoryName());
		cat.setCompanyReferenceNumber("CTS123");
		catServ.saveOrUpdate(cat); */
	}
	
	
	@Test
	public void fullVendorApplication() throws ParseException {
		
		Category cat1 = new Category();
		cat1.setCategoryName("ADMIN");
		cat1.setCompanyReferenceNumber("CTS123");
		catServ.saveOrUpdate(cat1);
		
		Application app = new Application();
	
		app.setCategory(cat1);
		app.setCompanyReferenceNumber("CTS123");
		app.setVendorReferenceNumber("AAA-111");
		app.setPOC("Mr Lee");
		app.setVendorPeriod(1L);
		
		String appRef=RegApp.applyVendorship(app);
		
		Application app3=appServ.findByApplicationRefNo(appRef);
		
		assertEquals(app3.getPOC(),"Mr Lee");
		assertEquals(app3.getVendorReferenceNumber(),"AAA-111");
		assertEquals(app3.getCompanyReferenceNumber(),"CTS123");
		assertEquals(app3.getApplicationStatus(), ApplicationStatus.PENDING);
		
	}
	
	
	@Test
	public void sameVendorCompanyApplication() throws ParseException {
		
		
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("VENDOR REFERENCE NUMBER CANNOT BE THE SAME AS COMPANY REFERENCE NUMBER");
		
		Category cat1 = new Category();
		cat1.setCategoryName("ADMIN");
		cat1.setCompanyReferenceNumber("CTS123");
		catServ.saveOrUpdate(cat1);
		
		Application app = new Application();
	
		app.setCategory(cat1);
		app.setCompanyReferenceNumber("CTS123");
		app.setVendorReferenceNumber("CTS123");
		app.setPOC("Mr Lee");
		app.setVendorPeriod(1L);
		
		String appRef=RegApp.applyVendorship(app);
		
		Application app3=appServ.findByApplicationRefNo(appRef);
		
		assertEquals(app3.getPOC(),"Mr Lee");
		assertEquals(app3.getVendorReferenceNumber(),"AAA-111");
		assertEquals(app3.getCompanyReferenceNumber(),"CTS123");
		assertEquals(app3.getApplicationStatus(), ApplicationStatus.PENDING);
		
	}
	
	@Test
	public void incompleteVendorApplication() throws ParseException {
		
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("MANDATORY FIELDS ARE NOT ALL FILLED UP");
		
		Category cat1 = new Category();
		cat1.setCategoryName("ADMIN");
		cat1.setCompanyReferenceNumber("CTS123");
		catServ.saveOrUpdate(cat1);
		
		Application app = new Application();
		app.setCategory(cat1);
	
		app.setVendorReferenceNumber("AAA-111");
		app.setPOC("Mr Lee");
		app.setVendorPeriod(1L);
		
		String appRef=RegApp.applyVendorship(app);
	
		
	}
	
}
