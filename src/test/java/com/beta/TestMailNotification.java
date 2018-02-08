package com.beta;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.beta.service.NotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestMailNotification {
	@Autowired
	NotificationService notificationService;
	
	@Test
	public void testSendEmail() throws Exception{
		String cc[] = {"Koh.KahAikSebastian@cognizant.com", "Tatsuro.Ninomiya@cognizant.com","JianMingTimothy.Tan@cognizant.com","Jermayne.Chong@cognizant.com","HongYun.Pang@cognizant.com","TsuKian.Ng@cognizant.com","Ng.HsinInn@cognizant.com"
				,"YongMeng.Sim@cognizant.com","Yin.Yifan@cognizant.com","ZheQin.Chong@cognizant.com","ZhiYi.Tan@cognizant.com","SongNian.Tay@cognizant.com"};
		notificationService.sendMail("ZhiYi.Tan@cognizant.com", cc, "Test Mail", "Lets get started guys!");
	}

}
