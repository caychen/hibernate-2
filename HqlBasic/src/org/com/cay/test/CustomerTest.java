package org.com.cay.test;

import java.util.List;

import org.com.cay.entity.Customer;
import org.com.cay.utils.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	
	private Session session = null;

	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}
	
	private void queryList(String hql){
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Customer> customers = query.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		} 
	}

	@Test
	public void test() {
		String hql = "from Customer";
		queryList(hql);
		
	}

	@Test
	public void testDistinct(){
		String hql = "select sex from Customer";
		
		//去除重复结果
		//String hql = "select distinct sex from Customer";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object> objs = query.list();
		for (Object object : objs) {
			System.out.println(object);
		}
		
	}
	
	@Test
	public void testWhere1(){
		String hql = "from Customer where sex <> '男'";
		
		queryList(hql);
		
	}
	
	@Test
	public void testWhere2(){
		//年龄为20或者40岁的顾客
		String hql = "from Customer where age in (20,40)";
		//String hql = "from Customer where age not in (20,40)";
		
		queryList(hql);
		
	}
	
	@Test
	public void testWhere3(){
		//年龄在30~40之间的顾客
		String hql = "from Customer where age between 30 and 40";
		
		queryList(hql);
		
	}
	
	@Test
	public void testWhere4(){
		//姓张，且姓名为两个字
		String hql = "from Customer where name like '张_'";
		
		queryList(hql);
		
	}
	
	

	@Test
	public void testWhere5(){
		//地址中有北京两字
		String hql = "from Customer where address like '%北京%'";
		
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Customer> customers = query.list();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		
	}
	
	@Test
	public void testWhere6(){
		//查询返回结果为一条记录
		String hql = "from Customer where name = '张三'";
		
		Query query = session.createQuery(hql);
		Customer customer = (Customer) query.uniqueResult();
		System.out.println(customer);
		
	}
}
