package org.com.cay.test;

import org.com.cay.entity.Employee;
import org.com.cay.entity.Project;
import org.com.cay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Test {

	@org.junit.Test
	public void test() {
		Project project1 = new Project(1001, "项目一");
		Project project2 = new Project(1002, "项目二");
		
		Employee employee1 = new Employee(1, "黄蓉");
		Employee employee2 = new Employee(2, "郭靖");
		
		//参加项目1的雇员
		project1.getEmployees().add(employee1);
		project1.getEmployees().add(employee2);
		
		project2.getEmployees().add(employee1);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.save(project1);
		session.save(project2);
		
		tx.commit();
		HibernateUtil.closeSession(session);
	}

}
