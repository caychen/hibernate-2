package org.com.cay.both.OneToOne.fk;

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

	
	@Test
	public void testOne2OneSave() {
		Department dept = new Department();
		dept.setDeptName("Dept-AA");
		
		Manager mgr = new Manager();
		mgr.setMgrName("Mgr-AA");
		
		dept.setMgr(mgr);
		mgr.setDept(dept);
		
		session.save(mgr);
		session.save(dept);
	}
	
	@Test
	public void testOne2OneGet() {
		//在查询有外键的对象时，默认关联的对象还是使用懒加载，所以可能会出现懒加载异常
		Department dept = session.get(Department.class, 1);
		System.out.println(dept.getDeptName());
		
		//查询Manager对象的连接条件应该是dept.mgr_id=mgr.mgr_id，而不是dept.dept_id=mgr.mgr_id
		Manager mgr = dept.getMgr();
		System.out.println(mgr.getMgrName());
	}
	
	@Test
	public void testOne2OneGet2() {
		//在查询没有外键的实体对象时，使用左外连接查询，一起查询出其关联的对象，并进行初始化赋值
		Manager mgr = session.get(Manager.class, 1);
		System.out.println(mgr.getMgrName());
		System.out.println(mgr.getDept().getDeptName());
	}

}
