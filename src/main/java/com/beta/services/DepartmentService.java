package com.beta.services;

import com.beta.entity.Department;

public interface DepartmentService extends BaseService<Long, Department> {
	public Department findByNameAndCompanyRef(String departmentName, String companyReferenceNumber);
}
