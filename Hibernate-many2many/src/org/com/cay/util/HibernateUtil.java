package org.com.cay.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;
	
	static{
		//创建Configuration对象，加载hibernate.cfg.xml文件，完成配置初始化
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		
		sessionFactory = config.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static Session getSession(){
		session = sessionFactory.openSession();
		return session;
	}
	
	public static void closeSession(Session session){
		if(session != null)
			session.close();
	}
}
