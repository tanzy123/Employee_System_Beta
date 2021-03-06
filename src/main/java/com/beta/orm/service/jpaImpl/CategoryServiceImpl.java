package com.beta.orm.service.jpaImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.CategoryDao;
import com.beta.dao.CompanyDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.CategoryService;

@Service("CategoryJPAService")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class CategoryServiceImpl extends BaseServiceImpl<Long, Category> implements CategoryService {

	@Autowired
	protected CategoryDao dao;

	@Autowired
	private CompanyDao companyDao;

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
	public void saveOrUpdate(Category entity) throws VendorMgmtException {

		validateCategory(entity);
		if (entity.getCategoryId() == null) {
			Category category = findByNameAndCompanyRef(entity.getCategoryName(), entity.getCompanyReferenceNumber());
			if (category == null)
				dao.persist(entity);
			else {
				category.setCategoryName(entity.getCategoryName());
				dao.merge(category);
			} 
		}	
		else {
			Category category = dao.findById(entity.getCategoryId());
			if (category != null) {
				category.setCategoryName(entity.getCategoryName());
				dao.merge(category);
			} else
				dao.merge(entity);
		}
	}

	public Category findByNameAndCompanyRef(String categoryName, String companyReferenceNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferenceNumber);
		params.put("categoryName", categoryName);
		List<Category> list = dao.findByNamedQueryAndNamedParams("Category.findByNameAndRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one category per company found for updating");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public void validateCategory(Category entity) {
		if (entity.getCategoryName() == null || entity.getCompanyReferenceNumber() == null)
			throw new UserException("Category name or company reference number not found");

		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", entity.getCompanyReferenceNumber());
		List<Company> list = companyDao.findByNamedQueryAndNamedParams("Company.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found while validating category");
		else if (list.isEmpty())
			throw new UserException("Invalid company entered while validating category");
	}

	@Override
	public List<Category> findByCompanyRef(String companyReferenceNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferenceNumber);
		return dao.findByNamedQueryAndNamedParams("Category.findByCompRefNo", params);
	}

	@Override
	public void removeCat(Long id) {
		// TODO Auto-generated method stub
		super.delete(id);
		
	}
}
		