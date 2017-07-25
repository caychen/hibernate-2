package org.com.cay.single.ManyToOne;

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
	public void testMany2OneSave() {
		Customer customer = new Customer();
		customer.setCustomerName("BBBB");
		
		Orders orders1 = new Orders();
		orders1.setOrderName("Order-3");
		
		Orders orders2 = new Orders();
		orders2.setOrderName("Order-4");
		
		//�趨������ϵ
		orders1.setCustomer(customer); 
		orders2.setCustomer(customer); 
		
		//�Ȳ���1��һ�ˣ��ٲ�����һ�ˣ������3��insert���
		session.save(customer);
		session.save(orders1);
		session.save(orders2);
		
		//�Ȳ�����һ�ˣ��ٲ���1��һ�ˣ������3��insert����2��update���
		//��Ϊ�ڲ�����һ�˵�ʱ���޷�ȷ��1��һ�˵����ֵ������ֻ�ܵ�1��һ�˲���֮���ٶ��ⷢ��update�������
		//���Ƽ��˱��淽���������Ƽ��Ȳ���1��һ�ˣ��ٲ�����һ��
//		session.save(orders1);
//		session.save(orders2);
//		session.save(customer);
	}
	
	@Test
	public void testMany2OneGet(){
		//1������ѯ���һ�˵Ķ���ʱ����Ĭ������£�ֻ��ѯ���һ�˵Ķ��󣬶�û�й����Ķ���
		Orders orders = session.get(Orders.class, 1);
		System.out.println(orders.getClass().getName());
		System.out.println("111111111111111111");
		//2����ֻ������Ҫʹ�ù��������ʱ�򣬲ŷ��Ͷ�Ӧ�Ĳ�ѯ���
		System.out.println(orders);
		
		//3����ȡOrders����ʱ��Ĭ�������������Ķ���Customer������һ���������
		System.out.println(orders.getCustomer().getClass().getName());
		
		//4���ڲ�ѯ���������ʱ�����session�Ѿ��رգ���Ĭ������»ᷢ��LazyInitializationException�쳣
	}
	
	@Test
	public void testMany2OneUpdate(){
		session.get(Orders.class, 1).getCustomer().setCustomerName("XXX");
	}
	
	@Test
	public void testMany2OneDelete(){
		//���趨������ϵ������£����ڶ��һ�������ã�����ֱ��ɾ��1��һ�˵Ķ���
		session.delete(session.get(Customer.class, 1));
	}
	
}
