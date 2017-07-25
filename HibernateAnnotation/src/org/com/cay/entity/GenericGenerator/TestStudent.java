package org.com.cay.entity.GenericGenerator;

import java.util.Date;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestStudent {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;
	
	@Before
	public void init(){
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}
	
	@After
	public void destory(){
		tx.commit();
		session.close();
		sessionFactory.close();
		
	}
	
	@Test
	public void testSaveStudents() {
		Address addr = new Address("215500","12345678901","常熟市xxx");
		
		Students1 s1 = new Students1("张三丰","男",new Date(),"太极拳",addr);
		Students2 s2 = new Students2("000001","张三丰","男",new Date(),"太极拳",addr);
		
		session.save(s1);
		session.save(s2);
	}

}
