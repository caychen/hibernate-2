package org.com.cay.test;

import java.util.Iterator;
import java.util.List;

import org.com.cay.entity.Items;
import org.com.cay.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

public class TestCache {

	@Test
	public void test1() {
		Session session = HibernateUtil.getSession();
		Items item = session.get(Items.class, 1);
		System.out.println(item);
		
		//清除session一级缓存中指定的缓存对象
		session.evict(item);
		
		//清除session中一级缓存中所有的的缓存对象
		session.clear();
		
		//Hibernate默认强制开启一级缓存，会从缓存中查询，如果找到则返回，如果未找到，则去数据库中进行查询
		item = session.get(Items.class, 1);
		System.out.println(item);
		
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void test2(){
		Session session = HibernateUtil.getSession();
		Items item = session.get(Items.class, 1);
		System.out.println(item);
		
		session = HibernateUtil.getSession();
		item = session.get(Items.class, 1);
		System.out.println(item);
		
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void test3(){
		Session session = HibernateUtil.getSession();
		
		Query query = session.createQuery("from Items");
		
		List<Items> items = query.list();//list返回的数据会放入一级缓存
		for (Items item : items) {
			System.out.println(item);
		}
		
		System.out.println("====================");
		//list不会使用一级缓存机制，而是直接去数据库中查询数据，即使一级缓存中已有数据。
		items = query.list();
		for (Items item : items) {
			System.out.println(item);
		}
		
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void test4(){
		Session session = HibernateUtil.getSession();
		
		Query query = session.createQuery("from Items");
		List<Items> items = query.list();//发送一次sql语句，从数据库中获取数据放入一级缓存
		
		//Iterator先从一级缓存中查询，如果找到，直接返回，如果未找到，去数据库中查找。
		Iterator<Items> it = items.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		HibernateUtil.closeSession(session);
	}

}
