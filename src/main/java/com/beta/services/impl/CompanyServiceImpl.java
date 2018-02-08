package com.beta.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.CompanyDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Company;
import com.beta.exception.VendorMgmtException;
import com.beta.service.FieldCopyUtil;
import com.beta.services.CompanyService;

@Service("CompanyService")

@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class CompanyServiceImpl extends BaseServiceImpl<Long, Company> implements CompanyService {

	@Autowired
	protected CompanyDao dao;

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
	public void save(Company entity) throws VendorMgmtException {
		if (entity.getCompanyPrimaryId() == null)
			dao.persist(entity);
		else
			dao.merge(entity);

	}

	@Override
	public void update(Company entity) throws VendorMgmtException {
		Company company = findbyCompanyNameAndRefNo(entity.getCompanyReferenceNumber(), entity.getCompanyName());
		if (company == null)
			throw new VendorMgmtException("Company reference number or company name not found!");
		else
			updateCompanyDetails(entity, company);
	}

	@Override
	public void saveOrUpdate(Company entity) throws VendorMgmtException {
		if (entity.getCompanyReferenceNumber() == null || entity.getCompanyName() == null)
			throw new VendorMgmtException("Company reference number or company name not found!");
		Company company = findbyCompanyNameAndRefNo(entity.getCompanyReferenceNumber(), entity.getCompanyName());

		if (company == null)
			save(entity);
		else
			updateCompanyDetails(entity, company);

	}

	private void updateCompanyDetails(Company entity, Company company) {
		FieldCopyUtil.setFields(entity, company);
	}

	public Company findbyCompanyNameAndRefNo(String companyReferenceNumber, String companyName) {
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferenceNumber);
		params.put("companyName", companyName);
		List<Company> list = findByNamedQueryAndNamedParams("Company.findByNameAndRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found to update");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}
}
