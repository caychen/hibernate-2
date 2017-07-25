package org.com.cay.both.OneToMany;

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
		Customer customer = new Customer();
		customer.setCustomerName("BBBB");
		
		Orders orders1 = new Orders();
		orders1.setOrderName("Order-3");
		
		Orders orders2 = new Orders();
		orders2.setOrderName("Order-4");
		
		//设定关联关系
		orders1.setCustomer(customer); 
		orders2.setCustomer(customer); 
		
		//添加集合
		customer.getOrderss().add(orders1);
		customer.getOrderss().add(orders2);
		
		//先插入customer，再插入orders，会产生3条insert语句和2条update语句
		//因为1的一端和多的一端同时都维护关联关系，所以会多出update语句
		//可以在1的一端的集合节点指定inverse=true来使1的一端放弃维护关联关系！
		//建议设定inverse=true，并建议先插入1的一端，再插入多的一端
		session.save(customer);
		session.save(orders1);
		session.save(orders2);
		
		//先插入orders，再插入customer，会产生3条insert语句和4条update语句
		
//		session.save(orders1);
//		session.save(orders2);
//		session.save(customer);
	}
	
	@Test
	public void testOne2ManyGet(){
		//1、对多的一端的集合使用延迟加载
		Customer customer = session.get(Customer.class, 1);
		
		//2、返回的多的一端的集合是Hibernate内置的集合类型
		//该类型具有延迟加载和存放代理对象的功能
		System.out.println(customer.getOrderss().getClass().getName());
		
		//3、也可能会产生懒加载异常
	}
	
}
