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

import com.beta.dao.ApplicationDao;
import com.beta.dao.JPADAO;
import com.beta.dao.RequirementDao;
import com.beta.entity.Application;
import com.beta.entity.ApplicationStatus;
import com.beta.entity.ApprovalStatus;
import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.services.RequirementService;

@Service("RequirementJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class RequirementServiceImpl extends BaseServiceImpl<Long, Requirement> implements RequirementService {

	@Autowired
	protected ApplicationDao applicationDao;
	
	@Autowired
	protected RequirementDao dao;
	
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
	public List<Requirement> findByUsernameAndStatus(String userName, ApprovalStatus status) {
		
		Map<String,Object> params = new HashMap<>();
		params.put("userName", userName);
		params.put("status", status);
		return dao.findByNamedQueryAndNamedParams("Requirement.findByStatusAndUsername", params);
	}

	@Override
	public void saveOrUpdate(Requirement entity) throws VendorMgmtException {

		validateRequirement(entity);
		if (entity.getRequirementId() == null) {
			Requirement requirement = findByAppRefAndUserName(entity.getApplicationRef(), entity.getUserName());
			if (requirement == null) {
				dao.persist(entity);}
			else {
				requirement.setRequirement(entity.getRequirement());
				requirement.setSequence(entity.getSequence());
				requirement.setStatus(entity.getStatus());
				requirement.setStatusUpdateDate(entity.getStatusUpdateDate());
				dao.merge(requirement); 
			}
		}	
		else {
			Requirement requirement = dao.findById(entity.getRequirementId());
			if (requirement != null) {
				requirement.setRequirement(entity.getRequirement());
				requirement.setSequence(entity.getSequence());
				requirement.setStatus(entity.getStatus());
				requirement.setStatusUpdateDate(entity.getStatusUpdateDate());
				dao.merge(requirement);
			} else
				dao.merge(entity);
		}
	}
	
	@Override
	public Requirement findByApplicationRefAndUser( String appReferenceNumber, String UserName) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", appReferenceNumber);
		params.put("userName", UserName);
		
		List<Requirement> list = dao.findByNamedQueryAndNamedParams("Requirement.findByApplicationRefAndUsername", params);
		
		if (list.size() > 1)
			throw new VendorMgmtException("Multiple requirement is found with given application reference number and username");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	} 
	
	
	public Requirement findByAppRefAndUserName( String appReferenceNumber, String UserName) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", appReferenceNumber);
		params.put("userName", UserName);
		
		List<Requirement> list = dao.findByNamedQueryAndNamedParams("Requirement.findByApplicationRefAndUsername", params);
		
		if (list.size() > 1)
			throw new VendorMgmtException("Multiple requirement is found with given application reference number and username");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	} 
	
	public void validateRequirement(Requirement entity) {
		if (entity.getUserName() == null)
			throw new VendorMgmtException("User name not found");
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", entity.getApplicationRef());
		List<Application> list = applicationDao.findByNamedQueryAndNamedParams("Application.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one application found with the same application reference");
		else if (list.isEmpty())
			throw new VendorMgmtException("No application found with the same application reference");
	}

	@Override
	public List<Requirement> findByApplicationRef(String applicationRef) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", applicationRef);
		List<Requirement> list = dao.findByNamedQueryAndNamedParams("Requirement.findByApplicationRef", params);
		return list;
	}
	
	@Override
	public List<Requirement> findByApplicationRefAndStatus(String applicationRef, ApprovalStatus status) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", applicationRef);
		params.put("status", status);
		List<Requirement> list = dao.findByNamedQueryAndNamedParams("Requirement.findByApplicationRefAndStatus", params);
		return list;
	}
	
	@Override
	public void removeVet(Long id) {
		// TODO Auto-generated method stub
		super.delete(id);
	}
	
}
