package org.com.cay.single.ManyToOne;

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
	public void testMany2OneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("BBBB");
		
		Orders orders1 = new Orders();
		orders1.setOrderName("Order-3");
		
		Orders orders2 = new Orders();
		orders2.setOrderName("Order-4");
		
		//设定关联关系
		orders1.setCustomer(customer); 
		orders2.setCustomer(customer); 
		
		//先插入1的一端，再插入多的一端，会产生3条insert语句
		session.save(customer);
		session.save(orders1);
		session.save(orders2);
		
		//先插入多的一端，再插入1的一端，会产生3条insert语句和2条update语句
		//因为在插入多的一端的时候，无法确定1的一端的外键值，所以只能等1的一端插入之后，再额外发送update更新语句
		//不推荐此保存方法，而是推荐先插入1的一端，再插入多的一端
//		session.save(orders1);
//		session.save(orders2);
//		session.save(customer);
	}
	
	@Test
	public void testMany2OneGet(){
		//1、若查询多的一端的对象时，则默认情况下，只查询多的一端的对象，而没有关联的对象
		Orders orders = session.get(Orders.class, 1);
		System.out.println(orders.getClass().getName());
		System.out.println("111111111111111111");
		//2、而只有在需要使用关联对象的时候，才发送对应的查询语句
		System.out.println(orders);
		
		//3、获取Orders对象时，默认情况下其关联的对象Customer对象是一个代理对象。
		System.out.println(orders.getCustomer().getClass().getName());
		
		//4、在查询关联对象的时候，如果session已经关闭，则默认情况下会发生LazyInitializationException异常
	}
	
	@Test
	public void testMany2OneUpdate(){
		session.get(Orders.class, 1).getCustomer().setCustomerName("XXX");
	}
	
	@Test
	public void testMany2OneDelete(){
		//不设定级联关系的情况下，且在多的一端有引用，则不能直接删除1的一端的对象
		session.delete(session.get(Customer.class, 1));
	}
	
}
