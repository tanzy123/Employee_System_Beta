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
import com.beta.dao.RoleDao;
import com.beta.entity.Application;
import com.beta.entity.Company;
import com.beta.entity.Department;
import com.beta.entity.Role;
import com.beta.entity.VendorReference;
import com.beta.exception.VendorMgmtException;
import com.beta.services.RoleService;



@Service("RoleJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class RoleServiceImpl extends BaseServiceImpl<Long, Role> implements RoleService{
	
//	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//	Validator validator = factory.getValidator();
	
	@Autowired
    protected RoleDao dao;
	
	@Autowired
	protected CompanyDao comDao;

	@PostConstruct
    public void init() throws Exception {
	 super.setDAO( (JPADAO)dao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }


	@Override
	public void saveOrUpdate(Role entity) throws VendorMgmtException {

		validateRole(entity);
		if (entity.getRoleId() == null) {
			Role role = findByCompanyReferenceNumberAndRole(entity.getCompanyReferenceNumber(), entity.getRole());
			if (role == null) {
				dao.persist(entity);
			}
			else
			{
				role.setRole(entity.getRole());
				dao.merge(role);
			}
		}	
		else {
			Role role = dao.findById(entity.getRoleId());
			if (role!=null) {
				
				role.setRole(entity.getRole());
				dao.merge(role);
			} else
				dao.merge(entity);
		}
	}
	
	public Role findByCompanyReferenceNumberAndRole( String companyReferenceNumber, String role) {
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferenceNumber);
		params.put("role", role);
		List<Role> list = dao.findByNamedQueryAndNamedParams("Role.findByCompanyRefNumberAndRole", params);
		if (list.size() > 1) {
			throw new VendorMgmtException("More than one Role found for given company reference number");}
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	public void validateRole(Role entity) {
		if (entity.getRole() == null || entity.getCompanyReferenceNumber() == null)
			throw new VendorMgmtException("Role name or Reference Number not found");

		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", entity.getCompanyReferenceNumber());
		List<Company> list = comDao.findByNamedQueryAndNamedParams("Company.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found with the same application reference");
		else if (list.isEmpty())
			throw new VendorMgmtException("No company found with the same given reference");
	}
	@Override
	public List<Role> findByCompanyRef(String companyReferenceNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferenceNumber);
		return dao.findByNamedQueryAndNamedParams("Role.findByCompRefNo", params);
		
	}
	

	
}

