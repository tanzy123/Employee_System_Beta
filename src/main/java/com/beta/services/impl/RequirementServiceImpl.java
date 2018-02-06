package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.beta.dao.JPADAO;
import com.beta.dao.RequirementDao;
import com.beta.entity.Requirement;
import com.beta.services.RequirementService;

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

}
