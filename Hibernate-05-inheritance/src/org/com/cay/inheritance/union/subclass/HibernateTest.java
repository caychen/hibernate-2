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
	 * union-subclass
	 * �ŵ㣺
	 * 		1�������ݿ���У�����Ҫ���ӱ�����С�
	 * 		2��������е��ֶο�����ӷǿ�Լ����
	 * 
	 * ȱ�㣺
	 * 		1�����������ֶ�
	 * 		2���ڸ��²�����ʱ��
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
	 * ��ѯ��
	 * 	1����ѯ�����¼����Ѹ�����ӱ���ܵ�һ���ٽ��в�ѯ�������Բ�
	 * 	2�����������¼�����ܱȽϺ�
	 */
	@Test
	public void testUnionSubclassGet(){
		List<Person> persons = session.createQuery("from Person").getResultList();
		System.out.println(persons.size());
		
		List<Student> students = session.createQuery("from Student").getResultList();
		System.out.println(students.size());
	}
	
	/**
	 * ���¸�����ֶΣ����²������ӣ����ܵ͡�
	 */
	@Test
	public void testUnionSubclassUpdate(){
		String hql = "update Person p set p.age = 20 where p.id = 2";
		session.createQuery(hql).executeUpdate();
	}

}
