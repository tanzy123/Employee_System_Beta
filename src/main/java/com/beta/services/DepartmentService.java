package com.beta.services;

import java.util.List;

import com.beta.entity.Department;

public interface DepartmentService extends BaseService<Long, Department> {
	public Department findByNameAndCompanyRef(String departmentName, String companyReferenceNumber);

	public List<Department> findByCompanyRef(String companyReferenceNumber);
	
	public void removeDepartment(Long id);
}
