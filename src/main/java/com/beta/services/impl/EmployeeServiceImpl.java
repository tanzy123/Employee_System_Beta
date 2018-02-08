package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.beta.dao.EmployeeDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Application;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Employee;
import com.beta.services.EmployeeService;

public class EmployeeServiceImpl extends BaseServiceImpl<Long, Employee> implements EmployeeService {

	
	@Autowired
	protected EmployeeDao dao;
	
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
