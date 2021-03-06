package com.beta.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.beta.dao.CategoryDao;
import com.beta.entity.Category;


@Repository("CategoryJPADAO")
public class CategoryDaoImpl extends JpaDAOImpl<Long,Category>implements CategoryDao {

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

	public void setEntityManager(EntityManager em) {
		this.entityManager = em;
		super.setEntityManager(entityManager);
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}
	
	
	
}
