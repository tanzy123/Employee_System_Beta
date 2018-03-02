package com.beta.orm.service.jpaImpl;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.beta.dao.ApplicationDao;
import com.beta.dao.JPADAO;
import com.beta.dao.VendorReferenceDao;
import com.beta.entity.Application;
import com.beta.entity.Category;
import com.beta.entity.Company;
import com.beta.entity.VendorReference;
import com.beta.exception.VendorMgmtException;
import com.beta.orm.service.VendorReferenceService;



@Service("VendorJPAService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=VendorMgmtException.class)
public class VendorReferenceServiceImpl extends BaseServiceImpl<Long, VendorReference> implements VendorReferenceService{
	
	EntityManagerFactory entityManagerFactory;
    
    EntityManager em;
	
	@Autowired
    protected VendorReferenceDao vendorReferenceDao;

	@Autowired
	protected ApplicationDao applicationDao;
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO( (JPADAO)vendorReferenceDao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	vendorReferenceDao.setEntityManager(entityManager);
    }

	@Override
	public void saveOrUpdate(VendorReference entity) throws VendorMgmtException {

		validateVendorReference(entity);
		if (entity.getReferenceId() == null) {
			VendorReference venRef = findByAppRef(entity.getApplicationRef());
			if (venRef == null) {
				dao.persist(entity);
			}
			else {
				venRef.setCompanyAddress(entity.getCompanyAddress());
				venRef.setCompanyName(entity.getCompanyName());
				venRef.setContactPerson(entity.getContactPerson());
				venRef.setEmailAddress(entity.getEmailAddress());
				venRef.setEndDate(entity.getEndDate());
				venRef.setPhoneNumber(entity.getPhoneNumber());
				venRef.setStartDate(entity.getStartDate());
				dao.merge(venRef);
			}
		}	
		else {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", entity.getApplicationRef());
		VendorReference venRef = vendorReferenceDao.findById(entity.getReferenceId());
			if (venRef != null) {
				venRef.setCompanyAddress(entity.getCompanyAddress());
				venRef.setCompanyName(entity.getCompanyName());
				venRef.setContactPerson(entity.getContactPerson());
				venRef.setEmailAddress(entity.getEmailAddress());
				venRef.setEndDate(entity.getEndDate());
				venRef.setPhoneNumber(entity.getPhoneNumber());
				venRef.setStartDate(entity.getStartDate());
				dao.merge(venRef);
			} else
				dao.merge(entity);
		}
	}
	
	public VendorReference findByAppRef( String appReferenceNumber) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", appReferenceNumber);
		List<VendorReference> list = findByNamedQueryAndNamedParams("VendorReference.findByAppRef", params);
		if (list.size() > 1)
			throw new VendorMgmtException("Multiple vendor reference is found with given application reference number");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}
	
	public void validateVendorReference(VendorReference entity) 
{
		if (entity.getCompanyName() == null || entity.getCompanyAddress() == null)
			throw new VendorMgmtException("Company name or company address not found");
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", entity.getApplicationRef());
		List<Application> list = applicationDao.findByNamedQueryAndNamedParams("Application.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one application found with the same application reference");
		else if (list.isEmpty())
			throw new VendorMgmtException("No application found with the same application reference");
}	

/*	
	@Override
	public void delete(VendorReference vendorReference) {
		
		
	} */

}