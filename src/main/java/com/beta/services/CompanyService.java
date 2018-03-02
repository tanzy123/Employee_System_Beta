package com.beta.services;

import java.util.List;

import com.beta.entity.Company;

public interface CompanyService extends BaseService<Long, Company> {
	Company findbyCompanyNameAndRefNo(String companyReferenceNumber, String companyName);
	Company findbyRefNo(String companyReferenceNumber);
	List <Company> findByComName(String companyName);
}
