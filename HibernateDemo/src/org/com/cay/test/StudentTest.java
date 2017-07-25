package org.com.cay.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.Date;

import org.com.cay.entity.Address;
import org.com.cay.entity.Student;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;

	@Before
	// 初始化方法
	public void init() {
		//Hibernate-4.x版本
		/*
		// 创建配置对象
		Configuration config = new Configuration().configure();
		// 创建服务注册对象
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		// 创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		// 创建会话
		session = sessionFactory.openSession();
		// 开启事务
		transaction = session.beginTransaction();*/
		
		//Hibernate-5.x版本
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		
		sessionFactory = config.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	// 释放方法
	public void destroy() {
		transaction.commit();// 提交事务
		session.close();
		sessionFactory.close();
	}

	@Test
	// 测试方法
	public void testSaveStudents() {
		/*开启自动提交
		session.doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				connection.setAutoCommit(true);
			}
		});*/
		
		//Student s = new Student(1, "张三丰", "男", new Date(), "武当山");
		Student s = new Student();
		s.setSid(1);
		s.setSname("张三丰");
		s.setGender("男");
		s.setBirthday(new Date());
		//s.setAddress("武当山");
		
		Address address = new Address("210000","0217654321","上海");
		s.setAddress(address);
		session.save(s);//保存对象到数据库
	}

	@Test
	public void testWriteBlob() throws Exception{
		//Student s = new Student(1, "小花", "女", new Date(), "上海");
		Student s = new Student();
		s.setSid(1);
		s.setSname("小花");
		s.setGender("女");
		s.setBirthday(new Date());
		//s.setAddress("上海");
		File f = new File("F:/Code/Java/HibernateDemo/image/student.jpg");
		InputStream is = new FileInputStream(f);
		Blob image = Hibernate.getLobCreator(session).createBlob(is, is.available());
		s.setPicture(image);
		session.save(s);
	}
	
	@Test
	public void testReadBlob() throws Exception{
		Student s = session.get(Student.class, 1);
		Blob image = s.getPicture();
		
		//获得输入流
		InputStream is = image.getBinaryStream();
		//创建输出流
		OutputStream os = new FileOutputStream(new File("F:/Code/Java/HibernateDemo/image/student_bak.jpg"));
		
		byte[] buf = new byte[is.available()];
		int len = 0;
		while((len = is.read(buf)) > 0){
			os.write(buf);
		}
		
		os.close();
		is.close();
	}
	
	@Test
	public void testGetStudent(){
		Student s = session.get(Student.class,1);
		System.out.println(s.getClass().getName());
		System.out.println(s);
	}
	
	@Test
	public void testLoadStudent(){
		Student s = session.load(Student.class,1);
		System.out.println(s.getClass().getName());
		System.out.println(s);
	}
	
	@Test
	public void testUpdateStudent(){
		Student s = session.get(Student.class,1);
		s.setSname("张翠山");
		session.update(s);
	}
	
	@Test
	public void testDeleteStudent(){
		Student s = new Student();
		s.setSid(1);
		session.delete(s);
	}
}
