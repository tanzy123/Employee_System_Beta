package com.beta;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.beta.entity.Requirement;
import com.beta.exception.VendorMgmtException;
import com.beta.service.DefaultVetterSequenceService;

public class TestEmailVetterSequenceService {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	DefaultVetterSequenceService service;
	
	@Before
	public void initialize() {
		service = new DefaultVetterSequenceService();
	}

	@Test
	public void testValidateList() {
		List<Requirement> list = new ArrayList<>();
		Requirement requirement[] = new Requirement[5];
		for (int i=1;i<=requirement.length;i++) {
			requirement[i-1] = new Requirement();
			requirement[i-1].setSequence(i);
			list.add(requirement[i-1]);
		}
		service.validateList(list);
	}
	
	@Test
	public void testEmptyList() {
		List<Requirement> list = new ArrayList<>();
		service.validateList(list);
	}
	
	@Test
	public void testInvalidList() {
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Sequence is not valid");
		List<Requirement> list = new ArrayList<>();
		Requirement requirement[] = new Requirement[5];
		for (int i=1;i<=requirement.length;i++) {
			requirement[i-1] = new Requirement();
			requirement[i-1].setSequence(i-1);
			list.add(requirement[i-1]);
		}
		service.validateList(list);
	}
	
	@Test
	public void testInvalidListWithSameNumber() {
		expectedEx.expect(VendorMgmtException.class);
	    expectedEx.expectMessage("Sequence is not valid");
		List<Requirement> list = new ArrayList<>();
		Requirement requirement[] = new Requirement[5];
		for (int i=1;i<=requirement.length;i++) {
			requirement[i-1] = new Requirement();
			requirement[i-1].setSequence(1);
			list.add(requirement[i-1]);
		}
		service.validateList(list);
	}
}
