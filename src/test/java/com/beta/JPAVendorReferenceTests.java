package com.beta;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.beta.TestConstant.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.Application;
import com.beta.entity.Category;
import com.beta.entity.VendorReference;
import com.beta.exception.VendorMgmtException;
import com.beta.services.ApplicationService;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;
import com.beta.services.VendorReferenceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class JPAVendorReferenceTests {

	@Autowired
	VendorReferenceService vendorService;
	
	@Autowired
	ApplicationService appServ;
	
	@Autowired
	CategoryService catServ;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Autowired
	CompanyService comServ;
	
	@Before
	public void initialize() {
		Category category = SAMPLE_CATEGORY1;
		category.setCompanyReferenceNumber(SAMPLE_COMPANY.getCompanyReferenceNumber());
		comServ.saveOrUpdate(SAMPLE_COMPANY);
		catServ.saveOrUpdate(category);
		appServ.saveOrUpdate(SAMPLE_APPLICATION1);
		
	}
	
	@Test
	public void testVendorReferenceCreate() {
	//create
	int startsize = vendorService.findAll().size();	
	
	VendorReference vendorReference = new VendorReference();
	vendorReference.setApplicationRef("ASD1111");
	vendorReference.setCompanyAddress("Company Address 3");
	vendorReference.setCompanyName("CompanyName3");
	vendorService.saveOrUpdate(vendorReference);
	
	int finalsize = vendorService.findAll().size();
	
	assertEquals((startsize+1), finalsize);
	
	}
	
	
	
	@Test
	public void testVendorReferenceDelete() throws Exception {
	
		
	
	VendorReference vendorReference = new VendorReference();
	vendorReference.setReferenceId(33l);
	vendorReference.setApplicationRef("ASD1111");
	vendorReference.setCompanyAddress("Company Address 3");
	vendorReference.setCompanyName("CompanyName3");
	vendorService.saveOrUpdate(vendorReference);
	
	Map<String, Object> params = new HashMap<>();
	params.put("applicationRef", vendorReference.getApplicationRef());
	List<VendorReference> list = vendorService.findByNamedQueryAndNamedParams("VendorReference.findByAppRef", params);
	Long venId = null;
	if (list.size()==1) {
		for (VendorReference v:list) {
			venId=v.getReferenceId();
		}
	}
	
	int size1 = vendorService.findAll().size();
	vendorService.deleteIfExisting(venId);
	int size2 = vendorService.findAll().size();
	System.out.println(size1);
	System.out.println(size2);
	assertThat(size1,is(size2+1));
	}
	
	
	
	@Test
	public void testVendorReferenceWithoutCompanyName() {
	
	expectedEx.expect(VendorMgmtException.class);
    expectedEx.expectMessage("Company name or company address not found");
	
	VendorReference vendorReference = new VendorReference();
	vendorReference.setReferenceId(33l);
	vendorReference.setApplicationRef("ASD1111");
	vendorReference.setCompanyAddress("Company Address 3");
	vendorService.saveOrUpdate(vendorReference);
	
	}
	
	@Test
	public void testVendorReferenceWithoutCompanyAddress() {
	
	expectedEx.expect(VendorMgmtException.class);
    expectedEx.expectMessage("Company name or company address not found");
	
	VendorReference vendorReference = new VendorReference();
	vendorReference.setReferenceId(33l);
	vendorReference.setApplicationRef("ASD1111");
	vendorReference.setCompanyName("CompanyName3");
	vendorService.saveOrUpdate(vendorReference);
	
	}

	@Test
	public void testVendorReferencewithNoAppRef() {
		
	expectedEx.expect(VendorMgmtException.class);
	expectedEx.expectMessage("No application found with the same application reference");

	VendorReference vendorReference = new VendorReference();
	vendorReference.setReferenceId(33l);
	vendorReference.setApplicationRef("ASD1110");
	vendorReference.setCompanyName("CompanyName3");
	vendorReference.setCompanyAddress("Company Address 3");
	vendorService.saveOrUpdate(vendorReference);
	
	}
	
	
}
