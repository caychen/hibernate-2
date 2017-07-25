package org.com.cay.entity.Transient;

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
	public void init() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}

	@After
	public void destory() {
		tx.commit();
		session.close();
		sessionFactory.close();

	}

	@Test
	public void testSaveStudents() {
		//会忽略salary属性的映射
		Students student = new Students("张三丰","男",new Date(),"太极拳","常熟市", 5000.0);
		
		session.save(student);
		
	}

}
