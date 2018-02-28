package com.beta.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.CompanyDao;
import com.beta.dao.EmployeeAccountDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Company;
import com.beta.entity.EmployeeAccount;
import com.beta.exception.UserException;
import com.beta.exception.VendorMgmtException;
import com.beta.service.FieldCopyUtil;
import com.beta.services.EmployeeAccountService;

@Service("EmployeeAccountJPAService")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class EmployeeAccountServiceImpl extends BaseServiceImpl<Long, EmployeeAccount>
		implements EmployeeAccountService {

	@Autowired
	protected EmployeeAccountDao dao;

	@Autowired
	protected CompanyDao companyDao;

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
	public void saveOrUpdate(EmployeeAccount entity) throws VendorMgmtException {
		EmployeeAccount validatedAccount = validateAccount(entity);
		updateAccountDetails(entity, validatedAccount);

	}

	private void updateAccountDetails(EmployeeAccount entity, EmployeeAccount validatedAccount) {
		entity.setPassword(null);
		FieldCopyUtil.setFields(entity, validatedAccount);
	}

	@Override
	public void createNewAccount(EmployeeAccount userAccount) {
		validateNewAccount(userAccount);

		userAccount.setPassword(BCrypt.hashpw(userAccount.getPassword(), BCrypt.gensalt()));
		dao.merge(userAccount);

	}

	public void validateNewAccount(EmployeeAccount entity) {
		if (entity.getUserName() == null || entity.getPassword() == null || entity.getCompanyReferenceNumber() == null)
			throw new VendorMgmtException("Username/Password or company reference number not found");

		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", entity.getCompanyReferenceNumber());
		List<Company> list = companyDao.findByNamedQueryAndNamedParams("Company.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found while validating account");
		else if (list.isEmpty())
			throw new UserException("Invalid company entered while validating account");

	}

	@Override
	public void updatePassword(EmployeeAccount userAccount, String updatedPassword) {
		EmployeeAccount validatedAccount = validateAccount(userAccount);
		String updatedPW = BCrypt.hashpw(userAccount.getPassword(), BCrypt.gensalt());
		// may need to hash password first
		validatedAccount.setPassword(updatedPW);
		dao.merge(validatedAccount);

	}

	public EmployeeAccount validateAccount(EmployeeAccount entity) {
		EmployeeAccount validatedAccount = findByUserName(entity.getUserName());

		String password = entity.getPassword();
		String databasePassword = validatedAccount.getPassword();
		// validate if password is correct
		if(BCrypt.checkpw(password, databasePassword))
			return validatedAccount;
		else
			throw new UserException("Invalid Username or Password"); 
	}

	public EmployeeAccount findByUserName(String userName) {
		Map<String, Object> params = new HashMap<>();
		params.put("userName", userName);
		List<EmployeeAccount> list = dao.findByNamedQueryAndNamedParams("EmployeeAccount.findByUsername", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found while validating account");
		else if (list.isEmpty())
			throw new UserException("Invalid username entered while validating account");
		return list.get(0);
	}
	public List<EmployeeAccount> checkDuplicateEmployeeIdInSameCompany(String companyReferencenumber, String employeeId)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferencenumber);
		params.put("employeeId", employeeId);
		return dao.findByNamedQueryAndNamedParams("EmployeeAccount.checkEmpIdDuplicate", params);
		
	}
}
