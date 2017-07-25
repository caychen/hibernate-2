package org.com.cay.inheritance.subclass;

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
	
	/**
	 * subclassȱ�㣺
	 * 	1�������ݿ���У���Ҫ���ӱ�����С�
	 * 	2��������е��ֶβ�����ӷǿ�Լ����
	 * 	3�����̳в�ν�������ݿ����ֶ�Ҳ��϶ࡣ
	 */
	
	/**
	 * ���������
	 * 	1�������������ֻ��Ѽ�¼���뵽һ�����ݿ���С�
	 * 	2�����������Hibernate�Զ�ά����
	 */
	@Test
	public void testSubclassSave(){
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
	 * ��ѯ��
	 * 	1����ѯ�����¼��ֻ��Ҫ��ѯһ�����ݿ��
	 * 	2�����������¼��Ҳֻ��Ҫ��ѯһ�����ݿ��
	 */
	@Test
	public void testSubclassGet(){
		List<Person> persons = session.createQuery("from Person").getResultList();
		System.out.println(persons.size());
		
		List<Student> students = session.createQuery("from Student").getResultList();
		System.out.println(students.size());
	}

}
