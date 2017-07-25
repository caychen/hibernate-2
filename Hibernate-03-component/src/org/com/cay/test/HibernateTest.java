package org.com.cay.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.com.cay.entity.News;
import org.hibernate.Hibernate;
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
		// ����һ��SessionFactory����
		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();

		// ����һ��Session����
		session = sessionFactory.openSession();

		// ��������
		tx = session.beginTransaction();
	}

	@After
	public void destroy() {
		// �ύ����
		tx.commit();

		// �ر�Session
		session.close();

		// �ر�SessionFactory
		sessionFactory.close();
	}

	@Test
	public void testWriteBlob() throws IOException {
		News news = new News("Hello World", "Cay", new Date());
		news.setContent("Cay" + ":" + "Hello World" + ":" + news.getPublishDate());

		InputStream is = new FileInputStream("1.png");
		Blob image = Hibernate.getLobCreator(session).createBlob(is, is.available());
		news.setImage(image);

		session.save(news);
	}
	
	@Test
	public void testReadBlob() throws IOException, SQLException {
		News news = session.get(News.class, 1);
		Blob image = news.getImage();
		
		InputStream is = image.getBinaryStream();
		System.out.println(is.available());
		
		OutputStream os = new FileOutputStream("2.png"); 
		int len = 0;
		byte[] buffer = new byte[is.available()];
		while((len = is.read(buffer)) != -1){
			os.write(buffer, 0, len);
		}
		
		is.close();
		os.close();
	}

}
