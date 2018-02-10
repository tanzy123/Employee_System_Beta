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
import com.beta.entity.Application;
import com.beta.exception.VendorMgmtException;
import com.beta.service.FieldCopyUtil;
import com.beta.services.ApplicationService;

@Service("ApplicationJPAService")
@org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRED, rollbackFor = VendorMgmtException.class)
public class ApplicationServiceImpl extends BaseServiceImpl<Long, Application> implements ApplicationService {

	@Autowired
	protected ApplicationDao dao;

	@PostConstruct
	public void init() throws Exception {
		super.setDAO((JPADAO) dao);
	}

	@PreDestroy
	public void destroy() {
	}

	@Override
	public void setEntityManagerOnDao(EntityManager entityManager) {
		dao.setEntityManager(entityManager);
	}

	public void save(Application entity) throws VendorMgmtException {
		if (entity.getApplicationId() == null)
			dao.persist(entity);
		else
			dao.merge(entity);
	}

	public Application findByApplicationRefNo(String applicationRef) {
		Map<String, Object> params = new HashMap<>();
		params.put("applicationRef", applicationRef);
		List<Application> list = findByNamedQueryAndNamedParams("Application.findByRefNo", params);
		if (list.size() > 1)
			throw new VendorMgmtException("More than one application found for updating");
		else if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	private void updateApplicationDetails(Application entity, Application application) {
		FieldCopyUtil.setFields(entity, application);
	}

	@Override
	public void saveOrUpdate(Application entity) throws VendorMgmtException {
		if (entity.getApplicationRef() == null)
			throw new VendorMgmtException("Application Reference Number not found!");
		Application application = findByApplicationRefNo(entity.getApplicationRef());

		if (application == null)
			save(entity);
		else
			updateApplicationDetails(entity, application);
	}
}