package com.beta.orm.service;

import java.util.List;

import com.beta.entity.Category;

public interface CategoryService extends BaseService<Long, Category>{

	Category findByNameAndCompanyRef(String categoryName, String companyReferenceNumber);

	List<Category> findByCompanyRef(String companyReferenceNumber);

	public void removeCat(Long id);
}
