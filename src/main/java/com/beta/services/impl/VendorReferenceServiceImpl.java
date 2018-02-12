package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.JPADAO;
import com.beta.dao.VendorReferenceDao;
import com.beta.entity.VendorReference;
import com.beta.exception.VendorMgmtException;
import com.beta.services.VendorReferenceService;



@Service("VendorJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class VendorReferenceServiceImpl extends BaseServiceImpl<Long, VendorReference> implements VendorReferenceService{
	
//	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//	Validator validator = factory.getValidator();
	
	@Autowired
    protected VendorReferenceDao vendorReferenceDao;

	@PostConstruct
    public void init() throws Exception {
	 super.setDAO( (JPADAO)vendorReferenceDao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	vendorReferenceDao.setEntityManager(entityManager);
    }

	@Override
	public void saveOrUpdate(VendorReference vendorReference) throws VendorMgmtException {
		// TODO Auto-generated method stub
		vendorReferenceDao.persist(vendorReference);
	}

}
