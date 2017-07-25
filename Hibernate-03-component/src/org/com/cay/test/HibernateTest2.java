package org.com.cay.test;

import java.io.IOException;

import org.com.cay.entity.Pay;
import org.com.cay.entity.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest2 {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction tx;

	@Before
	public void init() {
		// 创建一个SessionFactory对象
		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();

		// 创建一个Session对象
		session = sessionFactory.openSession();

		// 开启事务
		tx = session.beginTransaction();
	}

	@After
	public void destroy() {
		// 提交事务
		tx.commit();

		// 关闭Session
		session.close();

		// 关闭SessionFactory
		sessionFactory.close();
	}

	@Test
	public void testComponent() {
		Worker worker = new Worker();
		Pay pay = new Pay();
		
		pay.setMonthPay(1000);
		pay.setYearPay(80000);
		pay.setVocationWithPay(5);
		
		worker.setName("ABCD");
		worker.setPay(pay);
		session.save(worker);
	}
	
	
}
