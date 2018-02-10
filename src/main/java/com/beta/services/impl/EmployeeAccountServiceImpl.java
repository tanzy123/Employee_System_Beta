package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.EmployeeAccountDao;
import com.beta.dao.JPADAO;
import com.beta.entity.EmployeeAccount;
import com.beta.exception.VendorMgmtException;
import com.beta.services.EmployeeAccountService;

@Service("EmployeeAccountJPAService")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class EmployeeAccountServiceImpl extends BaseServiceImpl<Long, EmployeeAccount> implements EmployeeAccountService {

	
	@Autowired
	protected EmployeeAccountDao dao;
	
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
	public void saveOrUpdate(EmployeeAccount entity) throws VendorMgmtException {
		// TODO Auto-generated method stub
		
	}
}
