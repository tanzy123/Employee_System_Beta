package com.beta.services;

import com.beta.entity.VendorReference;
import com.beta.exception.VendorMgmtException;


public interface VendorReferenceService extends BaseService<Long, VendorReference>{
	
	public void saveOrUpdate(VendorReference vendorReference) throws VendorMgmtException  ;

	
	public void delete(VendorReference vendor);

}
