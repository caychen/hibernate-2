package org.com.cay.test;


import org.com.cay.entity.Grade;
import org.com.cay.entity.Student2;
import org.com.cay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

/**
 * 单向多对一关系(多个学生对应一个班级)
 * @author Cam
 *
 */
public class testMany2One {
	
	@Test
	public void add(){
		Grade g = new Grade("Java培训一班","Java培训一班");
		Student2 s1 = new Student2("岳飞","男");
		Student2 s2 = new Student2("花木兰","女");
		
		//设置关联关系
		s1.setGrade(g);
		s2.setGrade(g);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.save(s1);
		session.save(s2);
		session.save(g);
		
		tx.commit();
		HibernateUtil.closeSession(session);
	}
}