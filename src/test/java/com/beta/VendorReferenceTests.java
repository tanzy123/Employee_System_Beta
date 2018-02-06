package com.beta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.entity.VendorReference;
import com.beta.services.VendorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class VendorReferenceTests {

	@Autowired
	VendorService vendorService;
	
	@Test
	@Rollback(value=false)
	public void testVendorReferenceCreate() {
	VendorReference vendorReference = new VendorReference();

	vendorReference.setReferenceId(33l);
	vendorReference.setCompanyAddress("Company Address 3");
	vendorReference.setCompanyName("CompanyName3");
	vendorService.saveOrUpdate(vendorReference);
	System.out.println(vendorReference);
	}

}
