package com.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.RequirementService;

@Service("defaultVetterSequenceServiceImpl")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class DefaultVetterSequenceService implements VettingSequenceService{

	@Autowired
	RequirementService requirementService;
	
	@Autowired
	NotificationService notificationService;
	
	@Override
	public void validateVetterSequence(List<Requirement> requirementList) {
		validateList(requirementList);
	}

	public void validateList(List<Requirement> list) {
		list.sort((r1, r2)-> r1.getSequence() - r2.getSequence());
		for (int i=1;i<=list.size();i++) {
			if (list.get(i-1).getSequence()!=i)
				throw new VendorMgmtException("Sequence is not valid");
		}
	}
}

