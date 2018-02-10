package com.beta.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beta.dao.CompanyAdministratorAccountDao;
import com.beta.entity.CompanyAdministratorAccount;


@Repository("CompanyAdminstratorAccountJPADAO")
public class CompanyAdminstratorAccountDaoImpl extends JpaDAOImpl<Long,CompanyAdministratorAccount> implements CompanyAdministratorAccountDao{

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext(unitName="VendorManagementPersistentUnit")
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@PostConstruct
	public void init()
	{
		super.setEntityManager(entityManager);
		super.setEntityManagerFactory(entityManagerFactory);
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	
	
	
	
	
}
