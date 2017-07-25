package org.com.cay.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class SessionTest {

	@Test
	public void testOpenSession(){
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();
		if(session != null){
			System.out.println("session创建成功!");
		}else{
			System.out.println("session创建失败!");
		}
		
		Session session2 = sessionFactory.openSession();
		
		//openSession每次创建新的session对象,多次使用openSession，每次session对象都不同
		System.out.println(session == session2);//false
	}
	
	@Test
	public void testGetCurrentSession(){
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		SessionFactory sessionFactory = config.buildSessionFactory();
		//如果使用getCurrentSession需要在hibernate.cfg.xml文件中配置
		Session session = sessionFactory.getCurrentSession();
		if(session != null){
			System.out.println("session创建成功!");
		}else{
			System.out.println("session创建失败!");
		}
		
		Session session2 = sessionFactory.getCurrentSession();
		//getCurrentSession使用现有的session对象,多次调用getCurrentSession创建的session对象，都一致
		System.out.println(session == session2);//true
	}
	
}
