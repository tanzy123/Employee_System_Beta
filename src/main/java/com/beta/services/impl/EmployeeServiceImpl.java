package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.EmployeeDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Employee;
import com.beta.exception.VendorMgmtException;
import com.beta.services.EmployeeService;

@Service("EmployeeJPAService")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
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

	@Override
	public void save(Employee entity) throws VendorMgmtException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Employee entity) throws VendorMgmtException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveOrUpdate(Employee entity) throws VendorMgmtException {
		// TODO Auto-generated method stub
		
	}
}
