package org.com.cay.test;

import java.util.Set;

import org.com.cay.entity.Grade;
import org.com.cay.entity.Student2;
import org.com.cay.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


public class testOne2Many {

	@Test
	//将学生添加到班级
	public void add(){
		Grade g = new Grade("Java培训班一班","Java培训一班");
		Student2 s1 = new Student2("郭靖","男");
		Student2 s2 = new Student2("黄蓉","女");
		
		g.getStudents().add(s1);
		g.getStudents().add(s2);
		
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(s1);
		session.save(s2);
		session.save(g);
		
		transaction.commit();
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void findStudentByGrade(){
		Session session = HibernateUtil.getSession();
		
		Grade grade = session.get(Grade.class, 1);
		
		Set<Student2> students = grade.getStudents();
		for (Student2 student2 : students) {
			System.out.println(student2);
		}
		
		HibernateUtil.closeSession(session);
	}
	
	@Test
	//修改学生信息
	public void update(){
		Grade g = new Grade("Java培训班二班","Java培训二班");
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Student2 s1 = session.get(Student2.class, 1);
		g.getStudents().add(s1);
		session.save(g);
		
		tx.commit();
		HibernateUtil.closeSession(session);
	}
	
	@Test
	public void delete(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Student2 s = session.get(Student2.class, 2);
		session.delete(s);
		
		tx.commit();
		HibernateUtil.closeSession(session);
	}

}

