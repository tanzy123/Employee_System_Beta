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
import com.beta.dao.DepartmentDao;
import com.beta.dao.JPADAO;
import com.beta.entity.Company;
import com.beta.entity.Department;
import com.beta.exception.VendorMgmtException;
import com.beta.services.DepartmentService;

@Service("DepartmentJPAService")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class DepartmentServiceImpl extends BaseServiceImpl<Long, Department> implements DepartmentService{

	@Autowired
	protected DepartmentDao dao;
	
	@Autowired
	private CompanyDao companyDao;
	
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
	public void saveOrUpdate(Department entity) throws VendorMgmtException {
		validateDepartment(entity);
		if (entity.getDepartmentId() == null) {
			Department department = findByNameAndCompanyRef(entity.getDepartmentName(), entity.getCompanyReferenceNumber());
			if (department == null)
				dao.persist(entity);
			else {
				department.setDepartmentName(entity.getDepartmentName());
				dao.merge(department);
			}
		}	
		else {
			Department department = dao.findById(entity.getDepartmentId());
			if (department != null) {
				department.setDepartmentName(entity.getDepartmentName());
				dao.merge(department);
			} else
				dao.merge(entity);
		}
		
	}

	public void validateDepartment(Department entity) {
		if (entity.getDepartmentName() == null || entity.getCompanyReferenceNumber() == null)
			throw new VendorMgmtException("department name or company reference number not found");

		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", entity.getCompanyReferenceNumber());
		List<Company> list = companyDao.findByNamedQueryAndNamedParams("Company.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one company found while validating department");
		else if (list.isEmpty())
			throw new VendorMgmtException("Invalid company entered while validating department");
		
	}

	public Department findByNameAndCompanyRef(String departmentName, String companyReferenceNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferenceNumber);
		params.put("departmentName", departmentName);
		List<Department> list = dao.findByNamedQueryAndNamedParams("Department.findByNameAndRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one department per company found for updating");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public List<Department> findByCompanyRef(String companyReferenceNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("companyReferenceNumber", companyReferenceNumber);
		return dao.findByNamedQueryAndNamedParams("Department.findByCompRefNo", params);
		
	}
	
}
