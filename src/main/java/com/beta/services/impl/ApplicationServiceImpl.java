package com.beta.services.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.ApplicationDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Application;
import com.beta.exception.VendorMgmtException;
import com.beta.services.ApplicationService;

@Service("applicationService")

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class ApplicationServiceImpl extends BaseServiceImpl<Long, Application> implements ApplicationService {

	@Autowired
	protected ApplicationDao dao;

	@PostConstruct
	public void init() throws Exception {
		super.setDAO((JPADAO) dao);

	}

	@PreDestroy
	public void destroy() {
	}

	@Override
	public void setEntityManagerOnDao(EntityManager entityManager) {
		dao.setEntityManager(entityManager);
	}

	@Override
	public void save(Application entity) throws VendorMgmtException {
		
		
	}

	@Override
	public Application update(Application entity) throws VendorMgmtException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Application saveOrUpdate(Application entity) throws VendorMgmtException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) throws VendorMgmtException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteIfExisting(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
