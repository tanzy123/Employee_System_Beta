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

import com.beta.dao.JPADAO;
import com.beta.dao.RequirementDao;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.services.RequirementService;

@Service
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class RequirementServiceImpl extends BaseServiceImpl<Long, Requirement> implements RequirementService {

	
	@Autowired
	protected RequirementDao dao;
	
	@PostConstruct
	public void init() throws Exception
	{
		super.setDAO((JPADAO)dao);
	}
	
	@PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }

	@Override
	public List<Requirement> getRequirementByUsernameAndStatus(String userName, ApprovalStatus status) {
		
		Map<String,Object> params = new HashMap<>();
		params.put("userName", userName);
		params.put("status", status);
		return dao.findByNamedQueryAndNamedParams("Requirement.findByStatusAndUsername", params);
	}

}
