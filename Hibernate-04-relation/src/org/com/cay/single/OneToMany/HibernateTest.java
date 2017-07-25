package org.com.cay.single.OneToMany;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HibernateTest {

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
	public void testOne2ManySave() {
		Orders order1 = new Orders();
		order1.setOrderName("Order-1");
		
		Orders order2 = new Orders();
		order2.setOrderName("Order-2");
		
		Customer customer = new Customer();
		customer.setCustomerName("AAAA");
		
		customer.getOrderss().add(order1);
		customer.getOrderss().add(order2);
		
		session.save(order1);
		session.save(order2);
		session.save(customer);
	}
	
	@Test
	public void testOne2ManyGet(){
		Customer customer = session.get(Customer.class, 1);
		Set<Orders> orderss = customer.getOrderss();
		orderss.forEach(orders -> System.out.println(orders));
	}
	
	@Test
	public void testOne2ManyUpdate(){
		session.get(Customer.class, 1).getOrderss().iterator().next().setOrderName("OrderA");
	}
	
	@Test
	public void testOne2ManyDelete(){
		Customer customer = session.get(Customer.class, 1);
		session.delete(customer);
	}
	
}
