package com.beta.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;

@Service("vetterSequenceServiceImpl")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class DefaultVetterSequenceService implements VettingSequenceService{

	@Override
	public List<Requirement> VetterSequenceGenerator(Requirement requirement) {
		
		List<Requirement> vetterList = null;
		vetterList.add(requirement);
		return vetterList;
	}
	
}

