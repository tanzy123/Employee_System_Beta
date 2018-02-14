package com.beta;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ JPAApplicationServiceTest.class, JPACategoryServiceTest.class} )
public class AllJPATestSuite {}
