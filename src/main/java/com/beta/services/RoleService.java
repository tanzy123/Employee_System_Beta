package com.beta.services;

import com.beta.entity.Role;

public interface RoleService extends BaseService<Long, Role> {
//	public void saveOrUpdate(Role role) throws VendorMgmtException;
	public Role findByCompanyReferenceNumberAndRole(String companyReferenceNumber, String role);
}
