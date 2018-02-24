package com.beta.services;

import java.util.List;

import com.beta.entity.Documents;

public interface DocumentsService extends BaseService<Long, Documents> {
	
	List<Documents> findByApplicationRef(String applicationRef);
	
	Documents findByApplicationRefAndFilePath(String applicationRef, String filePath);
}