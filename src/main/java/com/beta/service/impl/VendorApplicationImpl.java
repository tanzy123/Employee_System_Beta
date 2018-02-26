package com.beta.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.web.multipart.MultipartFile;

import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.Category;
import com.beta.entity.Documents;
import com.beta.exception.VendorMgmtException;
import com.beta.service.SaveDocumentService;
import com.beta.service.VendorApplication;
import com.beta.services.ApplicationService;
import com.beta.services.CategoryService;
import com.beta.services.CompanyService;
import com.beta.services.DocumentsService;


@Service("VendorApplication")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class VendorApplicationImpl implements VendorApplication {

	@Autowired
	CategoryService categoryService;

	@Autowired
	ApplicationService appservice;

	@Autowired
	CompanyService companyService;

	@Autowired
	DocumentsService documentsService;
	
	@Autowired
	SaveDocumentService saveDocumentService;
	

	@Override
	public Application generateVendorApplication(Application application) {
		// There are some fields not inserted by vendor or staff, hence this method will
		// generate the rest
		String uniqueKey = "";
		do {
			uniqueKey = UUID.randomUUID().toString();
		} while (appservice.findByApplicationRefNo(uniqueKey) != null);

		Date applicationDate = new Date();
		Date modifiedDate = new Date();
		application.setApplicationRef(uniqueKey);
		application.setApplicationStatus(ApplicationStatus.VETTING);
		application.setApplicationDate(applicationDate);
		application.setModifiedDate(modifiedDate);

		return application;
	}

	@Override
	public void validateVendorApplication(Application application) throws VendorMgmtException {
		int counter = 0;

		String vendorRef = application.getVendorReferenceNumber();
		String companyRef = application.getCompanyReferenceNumber();
		List<Category> category = categoryService.findByCompanyRef(companyRef);

		Category vendorCategory = application.getCategory();
		String poc = application.getPOC();
		Long vendorPeriod = application.getVendorPeriod();

		try {
			if (companyRef.equals(null) || vendorCategory.equals(null) || poc.equals(null) || vendorPeriod.equals(null)
					|| vendorRef.equals(null)) {
				throw new NullPointerException();
			}
			if (vendorRef.equals(companyRef)) {
				throw new VendorMgmtException("VENDOR REFERENCE NUMBER CANNOT BE THE SAME AS COMPANY REFERENCE NUMBER");
			}
			{
				for (Category c : category) {
					if (vendorCategory.getCategoryName().equals(c.getCategoryName())
							&& c.getCompanyReferenceNumber().equals(application.getCompanyReferenceNumber())) {
						appservice.saveOrUpdate(application);
						counter = 1;
						// APPLICATION UPLOADED SUCCESSFULLY
					}
				}
				if (counter != 1) {
					throw new VendorMgmtException("VENDOR CATEGORY DO NOT FALL INTO COMPANY'S REQUESTED CATEGORY LIST");
				}
			}
		} catch (

		NullPointerException e) {
			throw new VendorMgmtException("MANDATORY FIELDS ARE NOT ALL FILLED UP");
		}
	}

	@Override
	public void uploadApplicationAndDocuments(Application generatedApplication, MultipartFile[] files)
			throws IOException {

		appservice.saveOrUpdate(generatedApplication);
		
		saveDocumentsToDatabase(generatedApplication.getApplicationRef(), files);
		
	}

	private void saveDocumentsToDatabase(String applicationRef, MultipartFile[] files) throws IOException {
		final String workingDirectory = System.getProperty("user.dir") + "/";
		for (MultipartFile file : files) {
			if (!file.getOriginalFilename().isEmpty()) {
				File tempFile = getTempFile(file, workingDirectory);
				String folderName = "/" + applicationRef + "/" + file.getOriginalFilename();
				saveDocumentService.uploadFile(tempFile.getAbsolutePath(), folderName);
				createDocumentToStoreInDB(folderName, applicationRef, file.getOriginalFilename());
				
			}
		}
	}

	private void createDocumentToStoreInDB(String filePath, String applicationRef, String originalFileName) {
		Documents document = new Documents();
		document.setApplicationRef(applicationRef);
		document.setFilePath(filePath);
		document.setOriginalFileName(originalFileName);
		documentsService.saveOrUpdate(document);		
	}

	private File getTempFile(MultipartFile file, String workingDirectory) throws IOException {
		String originalFileName = file.getOriginalFilename();
		File tempFile = new File(workingDirectory + originalFileName);
		file.transferTo(tempFile);
		return tempFile;
	}


}
