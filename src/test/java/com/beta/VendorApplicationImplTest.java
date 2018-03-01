package com.beta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.service.SaveDocumentService;
import com.beta.service.VendorApplication;

//Specific on Zy's comp only

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class VendorApplicationImplTest {

	@Autowired
	VendorApplication vendorApplication;
	
	@Autowired
	SaveDocumentService saveDocumentService;
	
	@Test
	public void uploadFilesOfSameName() {
		String foldername = vendorApplication.obtainUniqueFoldername("123", "Desert.jpg");
		saveDocumentService.uploadFile("C:\\Users\\645686\\Desktop\\Desert.jpg", foldername);
	}
}
