package com.beta.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beta.dao.RequirementDao;
import com.beta.entity.Requirement;


@Repository("RequirementJPADAO")
public class RequirementDaoImpl extends JpaDAOImpl<Long, Requirement> implements RequirementDao {

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
