package org.com.cay.test;


import java.util.List;
import java.util.Map;

import org.com.cay.entity.Seller;
import org.com.cay.utils.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SellerTest {
	
	private Session session = null;

	@Before
	public void setUp() throws Exception {
		session = HibernateSessionFactory.getCurrentSession();
	}

	@After
	public void tearDown() throws Exception {
		session.close();
	}

	//一旦自定义了构造器，如果使用from Seller查询语句，就会报错，所以建议将默认构造器编写出来
	@Test
	public void testFrom() {
		String hql = "from Seller";
		
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Seller> sellers = query.list();
		for (Seller seller : sellers) {
			System.out.println(seller);
		}
	}
	
	@Test
	public void testSelectObjectArray(){
		//如果查询多个属性，返回值为Object[]
		//如果查询单个属性，返回值为Object
		String hql = "select name, tel, address from Seller";
		
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object[]> sellers = query.list();
		for (Object[] obj : sellers) {
			System.out.println(obj[0]);//name
			System.out.println(obj[1]);//tel
			System.out.println(obj[2]);//address
		}
	}
	
	@Test
	public void testSelectObject(){
		//如果查询多个属性，返回值为Object[]
		//如果查询单个属性，返回值为Object
		String hql = "select name from Seller";
		
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object> sellers = query.list();
		for (Object obj : sellers) {
			System.out.println(obj);//name
		}
	}
	
	@Test
	public void testSelectByList(){
		//如果查询多个属性，返回值为Object[]
		//如果查询单个属性，返回值为Object
		String hql = "select new List(name, tel, address) from Seller";
		
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<List<Object>> sellers = query.list();
		for (List<Object> list : sellers) {
			System.out.println(list.get(0));//name
			System.out.println(list.get(1));//tel
			System.out.println(list.get(2));//address
		}
	}
	
	@Test
	public void testSelectByMap(){
		//如果查询多个属性，返回值为Object[]
		//如果查询单个属性，返回值为Object
		String hql = "select new map(s.name as name, s.tel as tel, s.address as address) from Seller s";
		
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> maps = query.list();
		for (Map<String, Object> map : maps) {
			//使用别名
			//String hql = "select new map(s.name as name, s.tel as tel, s.address as address) from Seller s";
			System.out.println(map.get("name"));//name
			System.out.println(map.get("tel"));//tel
			System.out.println(map.get("address"));//address
			
			//不使用别名
			//String hql = "select new map(s.name, s.tel, s.address) from Seller s";
			System.out.println(map.get("0"));//name
			System.out.println(map.get("1"));//tel
			System.out.println(map.get("2"));//address
		}
	}

	@Test
	public void testSelectByConstrutctor(){
		//如果查询多个属性，返回值为Object[]
		//如果查询单个属性，返回值为Object
		String hql = "select new Seller(s.name, s.tel, s.address) from Seller s";
		
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Seller> sellers = query.list();
		for (Seller seller : sellers) {
			System.out.println(seller.getName());//name
			System.out.println(seller.getTel());//tel
			System.out.println(seller.getAddress());//address
			
		}
	}
}
