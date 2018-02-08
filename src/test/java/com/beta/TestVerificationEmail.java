package com.beta;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD:src/test/java/com/beta/VendorApplicationTests.java
import com.beta.entity.Application;
import com.beta.entity.Category;
import com.beta.service.VendorApplication;
import com.beta.service.impl.VendorApplicationImpl;
import com.beta.services.CategoryService;
import com.beta.services.impl.CategoryServiceImpl;

=======
import com.beta.entity.Company;
import com.beta.service.DefaultRegistrationVerificationByEmail;
>>>>>>> c522fbff2913c959a8540d001f292fe57156e79e:src/test/java/com/beta/TestVerificationEmail.java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestVerificationEmail {

	@Autowired
	VendorApplication ven = new VendorApplicationImpl();
	
	@Test
	public void testVerificationEmail()
	{
		DefaultRegistrationVerificationByEmail verificationEmail= new DefaultRegistrationVerificationByEmail();
		
//		verificationEmail.SendVarificationEmail(Company company);
	}
	
<<<<<<< HEAD:src/test/java/com/beta/VendorApplicationTests.java
	@Test
	public void testGenerator() throws ParseException {
		// This test is incomplete because of the changes to be made by zhiyi
		Application app = new Application();
		Category cat = new Category();
		cat.setCategoryId(1L);
		cat.setCategoryName("audit");
		
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyId(12L);
		app.setPOC("Mr Lee");
		app.setVendorId(200L);
		app.setVendorPeriod(1L);
		
		Application app1 = new Application();
		app1 = ven.generateVendorApplication(app);
		
		
	}
	@Test
	public void testValidator() throws ParseException {
		// This test is meant to pass
		Application app = new Application();
		app.setApplicationRef("DDD");
		Category cat = new Category();
		cat.setCategoryId(2L);
		cat.setCategoryName("Audit");
		
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyId(12L);
		app.setPOC("Mr Lee");
		app.setVendorId(200L);
		app.setVendorPeriod(1L);
		
		Application app1 = new Application();
		app1 = ven.generateVendorApplication(app);
		
		String results = ven.validateVendorApplication(app1);
		
		assertEquals("APPLICATION UPLOADED SUCCESSFULLY",results);
		
	}
	
	@Test
	public void testValidator2() throws ParseException {
		// This test is meant to fail because category is not inserted
		Application app = new Application();
		
		app.setApplicationId(1L);
		app.setCompanyId(12L);
		app.setPOC("Mr Lee");
		app.setVendorId(200L);
		app.setVendorPeriod(1L);
		
		Application app1 = new Application();
		app1 = ven.generateVendorApplication(app);
		
		String results = ven.validateVendorApplication(app1);
		
		assertEquals("MANDATORY FIELDS ARE NOT ALL FILLED UP",results);
	}
	
	@Test
	public void testValidator3() throws ParseException {
		// This test is meant to fail because of invalid category
		Application app = new Application();
		Category cat = new Category();
		cat.setCategoryId(2L);
		cat.setCategoryName("Food");
		
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyId(12L);
		app.setPOC("Mr Lee");
		app.setVendorId(200L);
		app.setVendorPeriod(1L);
		
		Application app1 = new Application();
		app1 = ven.generateVendorApplication(app);
		
		String results = ven.validateVendorApplication(app1);
		
		assertEquals("APPLICATION SUBMISSION FAILED",results);
		
	}
	
	@Test
	public void testValidator4() throws ParseException {
		// This test is meant to fail because no POC name is given
		Application app = new Application();
		Category cat = new Category();
		cat.setCategoryId(2L);
		cat.setCategoryName("Audit");
		
		app.setApplicationId(1L);
		app.setCategory(cat);
		app.setCompanyId(12L);
		app.setVendorId(200L);
		app.setVendorPeriod(1L);
		
		Application app1 = new Application();
		app1 = ven.generateVendorApplication(app);
		
		String results = ven.validateVendorApplication(app1);
		
		assertEquals("MANDATORY FIELDS ARE NOT ALL FILLED UP",results);
		
	}

=======
>>>>>>> c522fbff2913c959a8540d001f292fe57156e79e:src/test/java/com/beta/TestVerificationEmail.java
}
