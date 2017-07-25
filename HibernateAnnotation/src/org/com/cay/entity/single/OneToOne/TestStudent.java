package org.com.cay.entity.single.OneToOne;


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
		IDCard idCard = new IDCard("1234567890", "张三");
		Students s = new Students(idCard, "男", new Date(),"太极拳");
		
		//先保存身份证类对象
		session.save(idCard);
		//再保存学生类对象
		session.save(s);
	}

}
