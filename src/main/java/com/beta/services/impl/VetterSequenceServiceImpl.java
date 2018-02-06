package com.beta.services.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import com.beta.dao.JPADAO;
import com.beta.dao.RequirementDao;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.services.RequirementService;

@Service("vetterSequenceServiceImpl")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class VetterSequenceServiceImpl extends BaseServiceImpl<Long, Requirement> implements RequirementService{
	
	
	@Autowired
    protected RequirementDao dao;

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

	
	public List<Requirement> VetterSequenceGenerator(Requirement requirement) {
		
		List<Requirement> vetterList = null;
		vetterList.add(requirement);
		return vetterList;
	}
    
    

	

	
}

