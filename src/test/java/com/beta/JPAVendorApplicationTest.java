package com.beta;

import static com.beta.TestConstant.SAMPLE_DOCUMENT_LIST;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.ApplicationService;
import com.beta.orm.service.CategoryService;
import com.beta.orm.service.CompanyService;
import com.beta.service.VendorApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPAVendorApplicationTest {

	@Autowired
	ApplicationService appServ;
	
	@Autowired
	VendorApplication ven;
	
	@Autowired
	CompanyService comService;
	
	@Autowired
	CategoryService catServ;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
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
	@Rollback(value=false)
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
		assertEquals(sdf.format(applicationDate),sdf.format(app1.getApplicationDate()));
		assertEquals(sdf.format(modifiedDate),sdf.format(app1.getModifiedDate()));
	}
	
	@Test
	public void TestPassApplicationValidator() throws ParseException {
	
		Category cat = new Category();
		
	//	cat.setCategoryId(3L);
		cat.setCategoryName("Tech");
		cat.setCompanyReferenceNumber("AA-11");
		catServ.saveOrUpdate(cat);
		
		Application app = new Application();
		Application app1 = new Application();
			//app.setApplicationId(1L);
		app.setApplicationRef("BB-11");
		app.setCategory(cat);
		app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
	//	app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("BB-12");
		
		ven.validateVendorApplication(app);
		Long id = app.getApplicationId();
		Application app2=appServ.find(id);
		
		assertEquals(app2.getPOC(),"Yi Fan");
		assertEquals(app2.getVendorReferenceNumber(),"BB-12");
		assertEquals(app2.getCompanyReferenceNumber(),"AA-11");
		assertEquals(app2.getApplicationRef(),"BB-11");
		
	}
	
	@Test
	public void TestIncompleteApplication() throws Exception {
		
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("MANDATORY FIELDS ARE NOT ALL FILLED UP");
	    
		//This test is supposed to fail because CompanyRefNo. is not inside
		Category cat = new Category();
		
		cat.setCategoryId(3L);
		cat.setCategoryName("Tech");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
	//	app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("BB-12");
		
		ven.validateVendorApplication(app);
		
	}
	
	@Test
	public void TestInvalidCategory() throws Exception {
		
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("VENDOR CATEGORY DO NOT FALL INTO COMPANY'S REQUESTED CATEGORY LIST");
		
		//This test is supposed to fail because Category don't match SQL records
		Category cat = new Category();
		
		cat.setCategoryId(3L);
		cat.setCategoryName("Cleaning");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("BB-12");
		
		ven.validateVendorApplication(app);
		
	}
	
	@Test
	public void TestInvalidVendorReference() throws Exception {
		
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("VENDOR REFERENCE NUMBER CANNOT BE THE SAME AS COMPANY REFERENCE NUMBER");
	    
		//This test is supposed to fail because VENDORREF== COMPANYREF
		Category cat = new Category();
		
		cat.setCategoryId(3L);
		cat.setCategoryName("Cleaning");
		cat.setCompanyReferenceNumber("AA-11");
		
		Application app = new Application();
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyReferenceNumber("AA-11");
		app.setPOC("Yi Fan");
		app.setSupportingDocument(SAMPLE_DOCUMENT_LIST);
		app.setVendorPeriod(1L);
		app.setVendorReferenceNumber("AA-11");
		
		ven.validateVendorApplication(app);
		
	}

}
