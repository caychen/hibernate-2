package org.com.cay.test;

import java.util.List;

import org.com.cay.entity.Commodity;
import org.com.cay.utils.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommodityTest {
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
		List<Commodity> commoditys = query.list();
		for (Commodity commodity : commoditys) {
			System.out.println(commodity);
		}
	}

	@Test
	public void test() {
		String hql = "from Commodity";
		queryList(hql);
	}

	@Test
	public void testWhere1(){
		String hql = "from Commodity where price >= 400";
		queryList(hql);
	}
	
	@Test
	public void testWhere2(){
		String hql = "from Commodity where description = null";
		queryList(hql);
	}
	
	@Test
	public void testWhere3(){
		String hql = "from Commodity where price between 100 and 5000 and category like '%电脑%'";
		queryList(hql);
	}
	
	@Test
	public void testWhere4(){
		String hql = "from Commodity where price * 5 > 3000";
		queryList(hql);
	}
	
	@Test
	public void testOrderBy1(){
		String hql = "from Commodity order by price asc";//asc升序，desc降序
		queryList(hql);
	}
	
	@Test
	public void testOrderBy2(){
		String hql = "from Commodity order by id asc, price desc, name asc";//asc升序，desc降序
		queryList(hql);
	}
}
