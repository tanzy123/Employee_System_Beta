package com.beta.orm.service;

import com.beta.entity.Company;

public interface CompanyService extends BaseService<Long, Company> {
	Company findbyCompanyNameAndRefNo(String companyReferenceNumber, String companyName);
	Company findbyRefNo(String companyReferenceNumber);
}
