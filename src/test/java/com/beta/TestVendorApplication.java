package com.beta;

import static com.beta.TestConstant.*;
import static com.beta.TestConstant.SAMPLE_DOCUMENT_LIST;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.service.VendorApplication;
import com.beta.services.CompanyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestVendorApplication {

	@Autowired
	VendorApplication ven;
	
	@Autowired
	CompanyService comService;
	
	@Before
	public void TestCOYPersist() {
		
		Company com = new Company();
		
		com.setCompanyReferenceNumber("AA-11");
		com.setCompanyName("ABC");
		com.setCompanyAddress("S'pore");
		com.setCompanyEmail("www.abc.com");
		com.setContactNumber("9999 9999");
		com.setTurnover(2000L);
		comService.saveOrUpdate(com);
	
	}
	
	@Test
	@Rollback
	public void TestApplicationGenerator() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // This would only work if test and object creation happen on the same time
		Date applicationDate = sdf.parse(sdf.format(new Date()));
		Date modifiedDate = sdf.parse(sdf.format(new Date()));
		
		Category cat = new Category();
		cat.setCategoryId(1L);
		cat.setCategoryName("TECH");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		Application app1 = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("BB-12");
		
		app1=ven.generateVendorApplication(app);
		
		assertEquals(ApplicationStatus.PENDING, app1.getApplicationStatus());
		assertEquals(applicationDate,app1.getApplicationDate());
		assertEquals(modifiedDate,app1.getModifiedDate());
	}
	
	@Test
	public void TestApplicationValidator() throws ParseException {
	
		Category cat = new Category();
		
		cat.setCategoryId(3L);
		cat.setCategoryName("Tech");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		Application app1 = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("BB-12");
		
		app1=ven.generateVendorApplication(app);
		String results = ven.validateVendorApplication(app1);
		
		System.out.println(results);
		
		assertEquals("APPLICATION UPLOADED SUCCESSFULLY",results);
		
	}
	
	@Test
	public void TestApplicationValidator2() throws ParseException {
		//This test is supposed to fail because CompanyRefNo. is not inside
		Category cat = new Category();
		
		cat.setCategoryId(3L);
		cat.setCategoryName("Tech");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		Application app1 = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
	//	app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("BB-12");
		
		app1=ven.generateVendorApplication(app);
		String results = ven.validateVendorApplication(app1);
		
		
		
		assertEquals("MANDATORY FIELDS ARE NOT ALL FILLED UP",results);
		
	}
	
	@Test
	public void TestApplicationValidator3() throws ParseException {
		//This test is supposed to fail because Category don't match SQL records
		Category cat = new Category();
		
		cat.setCategoryId(3L);
		cat.setCategoryName("Cleaning");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		Application app1 = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("BB-12");
		
		app1=ven.generateVendorApplication(app);
		String results = ven.validateVendorApplication(app1);
		
		
		assertEquals("VENDOR CATEGORY DO NOT FALL INTO COMPANY'S REQUESTED CATEGORY LIST",results);
		
	}
	
	@Test
	public void TestApplicationValidator4() throws ParseException {
		//This test is supposed to fail because VENDORREF== COMPANYREF
		Category cat = new Category();
		
		cat.setCategoryId(3L);
		cat.setCategoryName("Cleaning");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		Application app1 = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("AA-11");
		
		app1=ven.generateVendorApplication(app);
		String results = ven.validateVendorApplication(app1);
		
		
		assertEquals("VENDOR REFERENCE NUMBER CANNOT BE THE SAME AS COMPANY REFERENCE NUMBER",results);
		
	}

}
