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

	//测试hibernate二级缓存
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
	
	//测试集合的二级缓存
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
		
		//设置查询缓存，并在hibernate配置文件中启用查询缓存
		//<property name="cache.use_query_cache">true</property>
		query.setCacheable(true);
		
		List<Employee> emps = query.getResultList();
		System.out.println(emps.size());
		
		emps = query.getResultList();
		System.out.println(emps.size());
	}
	
}
