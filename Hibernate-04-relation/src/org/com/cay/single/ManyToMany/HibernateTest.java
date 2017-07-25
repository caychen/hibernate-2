package org.com.cay.single.ManyToMany;

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
	public void testMany2ManySave(){
		Category category1 = new Category();
		category1.setName("C-AA");
		
		Category category2 = new Category();
		category2.setName("C-BB");
		
		Item item1 = new Item();
		item1.setName("I-AA");
		
		Item item2 = new Item();
		item2.setName("I-BB");
		
		//设定关联关系
		category1.getItems().add(item1);
		category1.getItems().add(item2);
		
		category2.getItems().add(item1);
		category2.getItems().add(item2);
		
		session.save(category1);
		session.save(category2);
		
		session.save(item1);
		session.save(item2);
	}
	
	@Test
	public void testMany2ManyGet(){
		Category category1 = session.get(Category.class, 1);
		System.out.println(category1.getName());
		
		//需要连接中间表
		Set<Item> items = category1.getItems();
		System.out.println(items.size());
	}

}
