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
		//����һ��SessionFactory����
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		//����һ��Session����
		Session session = sessionFactory.openSession();
		
		//��������
		Transaction tx = session.beginTransaction();
		
		//ִ�����ݿ����
		News news = new News();
		news.setAuthor("Cay");
		news.setPublishDate(new Date());
		news.setTitle("Hello World");
		
		session.save(news);
		
		//�ύ����
		tx.commit();
		
		//�ر�Session
		session.close();
		
		//�ر�SessionFactory
		sessionFactory.close();
	}

}
