package org.com.cay.inheritance.joined.subclass;

import java.util.List;

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
	
	/**
	 * joined-subclass
	 * 优点：
	 * 		1、在数据库表中，不需要增加辨别者列。
	 * 		2、子类独有的字段可以添加非空约束。
	 * 		3、父类字段存放在父表中，子类独有的字段放入子表中，不会造成冗余。
	 * 
	 * 缺点：
	 * 		1、查询速度慢。
	 */
	
	/**
	 * 插入操作：
	 * 	1、对于子类对象至少需要把数据插入到两张数据库表
	 */
	@Test
	public void testJoinedSubclassSave(){
		Person person = new Person();
		person.setAge(11);
		person.setName("AA");
		
		session.save(person);
		
		Student stu = new Student();
		stu.setAge(22);
		stu.setName("BB");
		stu.setSchool("S-BB");
		
		session.save(stu);
	}
	
	/**
	 * 查询：
	 * 	1、查询父类记录，做一个左外连接查询
	 * 	2、对于子类记录，做一个内连接查询
	 */
	@Test
	public void testJoinedSubclassGet(){
		List<Person> persons = session.createQuery("from Person").getResultList();
		System.out.println(persons.size());
		
		List<Student> students = session.createQuery("from Student").getResultList();
		System.out.println(students.size());
	}

}
