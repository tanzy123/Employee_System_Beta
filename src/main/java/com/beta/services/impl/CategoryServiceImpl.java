package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.CategoryDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Category;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CategoryService;

@Service("CategoryService")

@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)

public class CategoryServiceImpl extends BaseServiceImpl<Long, Category> implements CategoryService{

	
	@Autowired
	protected CategoryDao dao;
	
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
