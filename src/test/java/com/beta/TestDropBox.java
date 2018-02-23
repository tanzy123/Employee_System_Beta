package com.beta;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.service.SaveDocumentService;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

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
	public void testUploadAndDeleteFile() throws IOException, DbxException{
		service.uploadFile("C:\\Users\\645686\\Desktop\\Hydrangeas.jpg", "/applicationRef/Hydrangeas.jpg");
	}
	
	@Test
	public void testReadFile() throws IOException, DbxException{
		service.readFile("/applicationRef/Hydrangeas.jpg","dafdsfsd.txt");
	}

}
