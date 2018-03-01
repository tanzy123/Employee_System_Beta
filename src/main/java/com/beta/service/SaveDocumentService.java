package com.beta.service;

import com.dropbox.core.DbxException;

public interface SaveDocumentService {
	
	void createFolder(String folderName) throws DbxException;
	
	String uploadFile(String path, String foldername);
	
	void readAndDownloadFile(String foldername, String filename);
	
	void deleteFile(String path);
	
	boolean checkFileExists(String foldername);
}
