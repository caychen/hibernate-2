package org.com.cay.inheritance.union.subclass;

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
	 * union-subclass
	 * 优点：
	 * 		1、在数据库表中，不需要增加辨别者列。
	 * 		2、子类独有的字段可以添加非空约束。
	 * 
	 * 缺点：
	 * 		1、存在冗余字段
	 * 		2、在更新操作的时候
	 */
	
	
	@Test
	public void testUnionSubclassSave(){
		Person person = new Person();
		person.setId(1);
		person.setAge(11);
		person.setName("AA");
		
		session.save(person);
		
		Student stu = new Student();
		stu.setId(2);
		stu.setAge(22);
		stu.setName("BB");
		stu.setSchool("S-BB");
		
		session.save(stu);
	}
	
	/**
	 * 查询：
	 * 	1、查询父类记录，需把父表和子表汇总到一起再进行查询，性能稍差
	 * 	2、对于子类记录，性能比较好
	 */
	@Test
	public void testUnionSubclassGet(){
		List<Person> persons = session.createQuery("from Person").getResultList();
		System.out.println(persons.size());
		
		List<Student> students = session.createQuery("from Student").getResultList();
		System.out.println(students.size());
	}
	
	/**
	 * 更新父表的字段，更新操作复杂，性能低。
	 */
	@Test
	public void testUnionSubclassUpdate(){
		String hql = "update Person p set p.age = 20 where p.id = 2";
		session.createQuery(hql).executeUpdate();
	}

}
