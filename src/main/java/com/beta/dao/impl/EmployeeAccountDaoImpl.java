package com.beta.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.apache.cxf.service.invoker.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beta.dao.EmployeeAccountDao;
import com.beta.entity.EmployeeAccount;


@Repository("EmployeeAccountJPADAO")
public class EmployeeAccountDaoImpl extends JpaDAOImpl<Long, EmployeeAccount> implements EmployeeAccountDao {

	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@PersistenceContext(unitName="VendorManagementPersistentUnit")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	@PostConstruct
	public void init()
	{
		super.setEntityManager(entityManager);
		super.setEntityManagerFactory(entityManagerFactory);
	}

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
		super.setEntityManager(entityManager);
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}


}
