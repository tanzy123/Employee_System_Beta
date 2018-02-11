package com.beta.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.ApplicationDao;
import com.beta.dao.DocDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Application;
import com.beta.entity.Documents;
import com.beta.exception.VendorMgmtException;
import com.beta.services.DocumentsService;

@Service("DocumentJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class DocumentsServiceImpl extends BaseServiceImpl<Long, Documents> implements DocumentsService{
	
//	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//	Validator validator = factory.getValidator();
	
	@Autowired
	protected ApplicationDao applicationDao;
	
	@Autowired
    protected DocDao dao;

	@PostConstruct
    public void init() throws Exception {
	 super.setDAO( (JPADAO)dao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }

	@Override
	public void saveOrUpdate(Documents entity) throws VendorMgmtException {
		validateDocuments(entity);
		if (entity.getDocumentId() == null) {
			Documents Documents = findByApplicationRef(entity.getApplicationRef());
			if (Documents == null)
				dao.persist(entity);
		}	
		else {
			Documents Documents = dao.findById(entity.getDocumentId());
			if (Documents != null) {
				Documents.setDocuments(entity.getDocuments());
				dao.merge(Documents);
			} else
				dao.merge(entity);
		}
		
	}

	public Documents findByApplicationRef(String applicationRef) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", applicationRef);
		List<Documents> list = dao.findByNamedQueryAndNamedParams("Documents.findByApplicationRef", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one applicationRef found for updating");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public void validateDocuments(Documents entity) {
		if (entity.getApplicationRef() == null)
			throw new VendorMgmtException("Application reference number not found");
		
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", entity.getApplicationRef());
		List<Application> list = applicationDao.findByNamedQueryAndNamedParams("Application.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one application found while validating documents");
		else if (list.isEmpty())
			throw new VendorMgmtException("Invalid application entered while validating documents");
	}




	
}
