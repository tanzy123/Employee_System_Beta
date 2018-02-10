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

import com.beta.dao.CompanyAdministratorAccountDao;
import com.beta.dao.CompanyDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Company;
import com.beta.entity.CompanyAdministratorAccount;
import com.beta.entity.UserAccount;
import com.beta.exception.VendorMgmtException;
import com.beta.services.CompanyAdminstratorAccountService;
import com.beta.services.UserAccountService;


@Service("CompanyAdminstratorAccountJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class CompanyAdminstratorAccountServiceImpl extends BaseServiceImpl<Long, CompanyAdministratorAccount> implements CompanyAdminstratorAccountService, UserAccountService {

	@Autowired
	protected CompanyAdministratorAccountDao dao;
	
	@Autowired
	protected CompanyDao companyDao;
	
	@PostConstruct
	public void init() throws Exception
	{
		super.setDAO((JPADAO)dao);
	}
	
	@PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }

	@Override
	public void saveOrUpdate(CompanyAdministratorAccount entity) throws VendorMgmtException {
		throw new UnsupportedOperationException();
	}
	
	public void validateAccount(UserAccount entity) {
		Map<String, Object> params = new HashMap<>();
		params.put("password", entity.getPassword());
		params.put("userName", entity.getUserName());
		List<CompanyAdministratorAccount> list = dao.findByNamedQueryAndNamedParams("CompanyAdministratorAccount.validateAccount", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found while validating account");
		else if (list.isEmpty())
			throw new VendorMgmtException("Invalid details entered while validating account");
	}

	public void validateNewAccount(UserAccount entity) {
		if (entity.getUserName() == null || entity.getPassword() == null || entity.getCompanyReferenceNumber() == null)
			throw new VendorMgmtException("Username/Password or company reference number not found");
		
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", entity.getCompanyReferenceNumber());
		List<Company> list = companyDao.findByNamedQueryAndNamedParams("Company.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found while validating account");
		else if (list.isEmpty())
			throw new VendorMgmtException("Invalid company entered while validating account");
	}

	@Override
	public void updatePassword(UserAccount userAccount, String updatedPassword) {
		validateAccount(userAccount);
		
	}

	@Override
	public void createNewAccount(UserAccount userAccount) {
		validateNewAccount(userAccount);
		
	}
	
	
}
