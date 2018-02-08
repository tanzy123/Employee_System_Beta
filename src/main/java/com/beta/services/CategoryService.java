package com.beta.services;

import com.beta.entity.Category;

public interface CategoryService extends BaseService<Long, Category>{

	Category findByNameAndCompanyRef(String categoryName, String companyReferenceNumber);
}
