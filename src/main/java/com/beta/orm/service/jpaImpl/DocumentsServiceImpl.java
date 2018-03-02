package com.beta.orm.service.jpaImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.ApplicationDao;
import com.beta.dao.DocDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Application;
import com.beta.entity.Documents;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.DocumentsService;
import com.beta.service.FieldCopyUtil;

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
		
		if (entity.getFilePath() == null) 
			dao.persist(entity);
		else if (entity.getDocumentId() == null) {
			Documents documents = findByApplicationRefAndFilePath(entity.getApplicationRef(), entity.getFilePath());
			if (documents == null)
				dao.persist(entity);
			else {
				updateDocuments(entity, documents);
			}
		}	
		else {
			Documents documents = dao.findById(entity.getDocumentId());
			if (documents != null) {
				updateDocuments(entity, documents);
				dao.merge(documents);
			} else
				dao.merge(entity);
		}
		
	}

	private void updateDocuments(Documents entity, Documents documents) {
		FieldCopyUtil.setFields(entity, documents);
		
	}

	public Documents findByApplicationRefAndFilePath(String applicationRef, String filePath) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", applicationRef);
		params.put("filePath", filePath);
		
		List<Documents> documents = dao.findByNamedQueryAndNamedParams("Documents.findByApplicationRefAndFilePath", params);
		if (documents.size()>1)
			throw new VendorMgmtException("More than one document of the same file path found!");
		else if (documents.isEmpty())
			return null;
		else
			return documents.get(0);
	}

	public List<Documents> findByApplicationRef(String applicationRef) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", applicationRef);
		return dao.findByNamedQueryAndNamedParams("Documents.findByApplicationRef", params);
		
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
			throw new UserException("Invalid application entered while validating documents");
	}




	
}
