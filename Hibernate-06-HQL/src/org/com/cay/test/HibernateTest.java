package org.com.cay.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.com.cay.entity.Department;
import org.com.cay.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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

	// 基于位置参数
	@Test
	public void testHQL() {
		String hql = "from Employee e where e.salary > ? and e.salary < ?";
		Query<Employee> query = session.createQuery(hql);
		query.setParameter(0, 6000.0f).setParameter(1, 8500.0f);

		List<Employee> list = query.getResultList();
		System.out.println(list.size());
	}

	// 命名参数
	@Test
	public void testHQLNamedParameter() {
		String hql = "from Employee e where e.salary > :min and e.salary < :max";
		Query<Employee> query = session.createQuery(hql);

		query.setParameter("min", 6000.0f).setParameter("max", 8500.0f);

		List<Employee> list = query.getResultList();
		System.out.println(list.size());
	}

	// 分页查询
	@Test
	public void testHQLPageQuery() {
		String hql = "from Employee";
		Query<Employee> query = session.createQuery(hql);

		int pageNo = 2;
		int pageSize = 5;

		List<Employee> list = query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).getResultList();

		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	// 命名查询，sql放在对应的实体类的hbm.xml中
	@Test
	public void testHQLNamedQuery() {
		Query<Employee> query = session.getNamedQuery("getEmployeeBySalary");
		List<Employee> list = query.setParameter("minSal", 5000.0f).setParameter("maxSal", 7600.0f).getResultList();
		System.out.println(list.size());
	}

	// 部分属性查询
	@Test
	public void testHQLPropertyQuery() {
		String hql = "select e.email, e.salary,e.dept from Employee e where e.dept = :dept";
		Query<Object[]> query = session.createQuery(hql);

		Department dept = new Department();
		dept.setId(4);
		List<Object[]> result = query.setParameter("dept", dept).getResultList();
		for (Object[] objs : result) {
			System.out.println(Arrays.asList(objs));
		}
	}

	// 投影查询
	@Test
	public void testHQLPropertyQuery2() {
		String hql = "select new Employee(e.name, e.salary,e.email,e.dept)" + " from Employee e where e.dept = :dept";
		Query<Employee> query = session.createQuery(hql);

		Department dept = new Department();
		dept.setId(4);
		List<Employee> result = query.setParameter("dept", dept).getResultList();
		for (Employee employee : result) {
			System.out.println(employee);
		}
	}

	// 报表查询
	@Test
	public void testHQLGroupBy() {
		String hql = "select min(e.salary),max(e.salary) from Employee e " + "group by e.dept "
				+ "having min(e.salary) > :minSal";

		Query<Object[]> query = session.createQuery(hql);
		List<Object[]> result = query.setParameter("minSal", 2000.0f).getResultList();
		for (Object[] objects : result) {
			System.out.println(Arrays.asList(objects));
		}
	}
	
	//迫切左外连接
	@Test
	public void testLeftJoinFetch(){
		//第一种、使用distinct去重
//		String hql = "select distinct d from Department d left join fetch d.employees";
		
		String hql = "from Department d left join fetch d.employees";
		Query<Department> query = session.createQuery(hql);
		List<Department> depts = query.getResultList();
		
		//第二种方法，使用set去重
		depts = new ArrayList<>(new LinkedHashSet<>(depts));
		
		System.out.println(depts.size());
		
		for (Department department : depts) {
			System.out.println(department);
		}
	}
	
	//左外连接
	@Test
	public void testLeftJoin(){
		String hql = "from Department d left join d.employees";
		Query<Object[]> query = session.createQuery(hql);
		List<Object[]> result = query.getResultList();
//		System.out.println(result);
		for (Object[] obj : result) {
			System.out.println(Arrays.asList(obj));
		}
	}
	
	@Test
	public void testQBC(){
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> list = criteria.add(Restrictions.gt("salary", 9000F)).list();
		System.out.println(list);
	}
	
	@Test
	//测试HQL中的and和or
	public void testQBC2(){
		//AND
		Conjunction conjunction = Restrictions.conjunction();
		conjunction.add(Restrictions.like("name", "f", MatchMode.ANYWHERE));
		conjunction.add(Restrictions.between("salary", 5000F, 9000F));
		System.out.println(conjunction);//name like %f% and salary between 5000.0 and 9000.0
		
		System.out.println("--------------------");
		//OR
		Disjunction disjunction = Restrictions.disjunction();
		List<String> nameRange = new ArrayList<>();
		nameRange.add("AA");
		nameRange.add("GG");
		nameRange.add("CC");
		disjunction.add(Restrictions.in("name", nameRange));
		disjunction.add(Restrictions.le("salary", 5000f));
		disjunction.add(Restrictions.isNotEmpty("email"));
		System.out.println(disjunction);//name in (AA, GG, CC) or salary<=5000.0 or email is not empty
	}
	
	@Test
	public void testQBC3(){
		//统计查询
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.max("salary"));
		System.out.println(criteria.uniqueResult());
	}
	
	@Test
	public void testQBC4(){
		//统计查询
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Employee.class);
		
		//添加排序
		criteria.addOrder(Order.asc("salary"));
		criteria.addOrder(Order.desc("email"));
		
		//添加分页
		int pageSize = 5;
		int pageNum = 2;
		List<Employee> list = criteria.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		System.out.println(list);
	}
}
