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
		//�ڲ�ѯ������Ķ���ʱ��Ĭ�Ϲ����Ķ�����ʹ�������أ����Կ��ܻ�����������쳣
		Department dept = session.get(Department.class, 1);
		System.out.println(dept.getDeptName());
		
		//��ѯManager�������������Ӧ����dept.mgr_id=mgr.mgr_id��������dept.dept_id=mgr.mgr_id
		Manager mgr = dept.getMgr();
		System.out.println(mgr.getMgrName());
	}
	
	@Test
	public void testOne2OneGet2() {
		//�ڲ�ѯû�������ʵ�����ʱ��ʹ���������Ӳ�ѯ��һ���ѯ��������Ķ��󣬲����г�ʼ����ֵ
		Manager mgr = session.get(Manager.class, 1);
		System.out.println(mgr.getMgrName());
		System.out.println(mgr.getDept().getDeptName());
	}

}
