package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.beta.dao.DepartmentDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Department;
import com.beta.services.DepartmentService;

public class DepartmentServiceImpl extends BaseServiceImpl<Long, Department> implements DepartmentService{

	

	@Autowired
	protected DepartmentDao dao;
	
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
