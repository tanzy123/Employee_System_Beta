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
import com.beta.service.FieldCopyUtil;
import com.beta.services.CompanyAdminstratorAccountService;


@Service("CompanyAdminstratorAccountJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class CompanyAdminstratorAccountServiceImpl extends BaseServiceImpl<Long, CompanyAdministratorAccount> implements CompanyAdminstratorAccountService {

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
		CompanyAdministratorAccount validatedAccount = validateAccount(entity);
		updateAccountDetails(entity, validatedAccount);
		
		
	}
	
	private void updateAccountDetails(CompanyAdministratorAccount entity, CompanyAdministratorAccount validatedAccount) {
		FieldCopyUtil.setFields(entity, validatedAccount);
		
	}

	public CompanyAdministratorAccount validateAccount(UserAccount entity) {
		Map<String, Object> params = new HashMap<>();
		params.put("userName", entity.getUserName());
		List<CompanyAdministratorAccount> list = dao.findByNamedQueryAndNamedParams("CompanyAdministrator.findByUsername", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found while validating account");
		else if (list.isEmpty())
			throw new VendorMgmtException("Invalid username entered while validating account");
		
		
		String password = entity.getPassword();
		String databasePassword = list.get(0).getPassword();
		//validate if password is correct
		return list.get(0);
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
	public void updatePassword(CompanyAdministratorAccount userAccount, String updatedPassword) {
		CompanyAdministratorAccount validatedAccount = validateAccount(userAccount);
		//may need to hash password first
		validatedAccount.setPassword(updatedPassword);
		dao.merge(validatedAccount);
	}

	@Override
	public void createNewAccount(CompanyAdministratorAccount userAccount) {
		validateNewAccount(userAccount);
		dao.persist(userAccount);
	}
}
