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
	 * joined-subclass
	 * �ŵ㣺
	 * 		1�������ݿ���У�����Ҫ���ӱ�����С�
	 * 		2��������е��ֶο�����ӷǿ�Լ����
	 * 		3�������ֶδ���ڸ����У�������е��ֶη����ӱ��У�����������ࡣ
	 * 
	 * ȱ�㣺
	 * 		1����ѯ�ٶ�����
	 */
	
	/**
	 * ���������
	 * 	1�������������������Ҫ�����ݲ��뵽�������ݿ��
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
	 * ��ѯ��
	 * 	1����ѯ�����¼����һ���������Ӳ�ѯ
	 * 	2�����������¼����һ�������Ӳ�ѯ
	 */
	@Test
	public void testJoinedSubclassGet(){
		List<Person> persons = session.createQuery("from Person").getResultList();
		System.out.println(persons.size());
		
		List<Student> students = session.createQuery("from Student").getResultList();
		System.out.println(students.size());
	}

}
