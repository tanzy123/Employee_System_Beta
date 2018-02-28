package com.beta;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.service.SaveDocumentService;
import com.dropbox.core.DbxException;
/*
Do not run this test all at once

*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestDropBox {
	
	@Autowired
	SaveDocumentService service;
	
	@Test
	public void testCreateFolder() throws IOException, DbxException{
		service.createFolder("/applicationRef");
	}
	
	@Test
	public void testUploadFile() throws IOException, DbxException{
		service.uploadFile("C:\\Users\\645686\\Desktop\\Desert.jpg", "/applicationRef/Hydrangeas.jpg");
		service.uploadFile("C:\\Users\\645686\\Desktop\\Koala.jpg", "/applicationRef/Hydrangeas.jpg");
	}
	
	@Test
	public void testReadAndDownloadFile() throws IOException, DbxException{
		service.readAndDownloadFile("/applicationRef/Hydrangeas.jpg","dafdsfsd.txt");
	}
	
	@Test
	public void testCheckIfFileExists() {
		assertTrue(service.checkFileExists("/applicationRef/Hydrangeas.jpg"));
	}
	
	@Test
	public void testCheckIfFileDoesNotExist() {
		assertFalse(service.checkFileExists("/applicationRef/Hydrangeaas.jpg"));
	}

}
