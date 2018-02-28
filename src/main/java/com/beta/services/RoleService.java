package com.beta.services;

import java.util.List;

import com.beta.entity.Role;

public interface RoleService extends BaseService<Long, Role> {
//	public void saveOrUpdate(Role role) throws VendorMgmtException;
	public Role findByCompanyReferenceNumberAndRole(String companyReferenceNumber, String role);

	List<Role> findByCompanyRef(String companyReferenceNumber);
}
