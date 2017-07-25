package org.com.cay.both.OneToMany;

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
	public void testOne2ManySave() {
		Customer customer = new Customer();
		customer.setCustomerName("BBBB");
		
		Orders orders1 = new Orders();
		orders1.setOrderName("Order-3");
		
		Orders orders2 = new Orders();
		orders2.setOrderName("Order-4");
		
		//�趨������ϵ
		orders1.setCustomer(customer); 
		orders2.setCustomer(customer); 
		
		//��Ӽ���
		customer.getOrderss().add(orders1);
		customer.getOrderss().add(orders2);
		
		//�Ȳ���customer���ٲ���orders�������3��insert����2��update���
		//��Ϊ1��һ�˺Ͷ��һ��ͬʱ��ά��������ϵ�����Ի���update���
		//������1��һ�˵ļ��Ͻڵ�ָ��inverse=true��ʹ1��һ�˷���ά��������ϵ��
		//�����趨inverse=true���������Ȳ���1��һ�ˣ��ٲ�����һ��
		session.save(customer);
		session.save(orders1);
		session.save(orders2);
		
		//�Ȳ���orders���ٲ���customer�������3��insert����4��update���
		
//		session.save(orders1);
//		session.save(orders2);
//		session.save(customer);
	}
	
	@Test
	public void testOne2ManyGet(){
		//1���Զ��һ�˵ļ���ʹ���ӳټ���
		Customer customer = session.get(Customer.class, 1);
		
		//2�����صĶ��һ�˵ļ�����Hibernate���õļ�������
		//�����;����ӳټ��غʹ�Ŵ������Ĺ���
		System.out.println(customer.getOrderss().getClass().getName());
		
		//3��Ҳ���ܻ�����������쳣
	}
	
}
