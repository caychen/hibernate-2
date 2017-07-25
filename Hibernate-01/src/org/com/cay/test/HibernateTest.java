package org.com.cay.test;

import java.util.Date;

import org.com.cay.entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

	@Test
	public void test() {
		//创建一个SessionFactory对象
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		//创建一个Session对象
		Session session = sessionFactory.openSession();
		
		//开启事务
		Transaction tx = session.beginTransaction();
		
		//执行数据库操作
		News news = new News();
		news.setAuthor("Cay");
		news.setPublishDate(new Date());
		news.setTitle("Hello World");
		
		session.save(news);
		
		//提交事务
		tx.commit();
		
		//关闭Session
		session.close();
		
		//关闭SessionFactory
		sessionFactory.close();
	}

}
