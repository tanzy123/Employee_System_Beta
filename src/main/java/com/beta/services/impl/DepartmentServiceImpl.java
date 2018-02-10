package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.DepartmentDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Department;
import com.beta.exception.VendorMgmtException;
import com.beta.services.DepartmentService;

@Service("DepartmentJPAService")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
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

	@Override
	public void saveOrUpdate(Department entity) throws VendorMgmtException {
		// TODO Auto-generated method stub
		
	}
}
