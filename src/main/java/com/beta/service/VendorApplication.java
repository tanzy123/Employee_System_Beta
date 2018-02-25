package com.beta.service;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.web.multipart.MultipartFile;

import com.beta.entity.Application;
import com.beta.exception.VendorMgmtException;

public interface VendorApplication {

	public Application generateVendorApplication(Application application);
	public void validateVendorApplication(Application application) throws VendorMgmtException;
	public void uploadApplicationAndDocuments(Application generatedApplication, MultipartFile[] files) throws IOException;
	 
}
