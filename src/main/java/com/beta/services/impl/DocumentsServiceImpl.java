package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.DocDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Documents;

import com.beta.exception.VendorMgmtException;
import com.beta.services.DocumentsService;

@Service("DocumentJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class DocumentsServiceImpl extends BaseServiceImpl<Long, Documents> implements DocumentsService{
	
//	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//	Validator validator = factory.getValidator();
	
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
		// TODO Auto-generated method stub
		
	}




	
}
