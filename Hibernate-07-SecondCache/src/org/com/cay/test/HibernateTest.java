package org.com.cay.test;

import java.util.List;

import org.com.cay.entity.Department;
import org.com.cay.entity.Employee;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
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

	//����hibernate��������
	@Test
	public void testHibernateSecondCache(){
		Employee emp = session.get(Employee.class, 1);
		System.out.println(emp.getName());
		
		tx.commit();
		session.close();
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Employee emp2 = session.get(Employee.class, 1);
		System.out.println(emp2.getName());
	}
	
	//���Լ��ϵĶ�������
	@Test
	public void testCollectionSecondLevelCache(){
		Department dept = session.get(Department.class, 3);
		System.out.println(dept);
		System.out.println(dept.getEmployees().size());
		
		tx.commit();
		session.close();
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		Department dept2 = session.get(Department.class, 3);
		System.out.println(dept2);
		System.out.println(dept2.getEmployees().size());
	}
	
	@Test
	public void testQueryCache(){
		Query<Employee> query = session.createQuery("from Employee");
		
		//���ò�ѯ���棬����hibernate�����ļ������ò�ѯ����
		//<property name="cache.use_query_cache">true</property>
		query.setCacheable(true);
		
		List<Employee> emps = query.getResultList();
		System.out.println(emps.size());
		
		emps = query.getResultList();
		System.out.println(emps.size());
	}
	
}
