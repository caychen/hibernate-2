package org.com.cay.test;

import java.util.Date;

import org.com.cay.entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class HibernateTest2 {

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
	
	@Test
	public void testSessionCache(){
		News news = session.get(News.class, 1);
		System.out.println(news);

		News news2 = session.get(News.class, 1);
		System.out.println(news2);
	}
	
	/*
	 * flush: 使数据表中的记录和Session缓存中的对象的状态保持一致，为了保持一致，则可能会发送对应的SQL语句
	 * 	1、调用Transaction的commit()方法中，先调用session的flush()方法，在提交事务。
	 * 	2、flush()可能会发送sql语句，但不会提交事务。
	 * 	3、注意：在未提交事务或显式地调用session的flush()方法之前，也有可能会进行flush操作。
	 * 		(1)、执行HQL或者QBC查询，会先进行flush操作，以得到数据表最新的记录
	 * 		(2)、若记录的ID是由底层数据库使用自增的方式生成的，则在调用save()方法时，就会立即发送insert语句，
	 * 			因为save方法后，必须保证对象的ID是存在的！
	 */
	@Test
	public void testFlush(){
		News news = session.get(News.class, 1);
		news.setAuthor("Amy");
		
	}
	
	/*
	 * refresh:会强制发送select语句，以使session缓存中对象的状态和数据库表中的对象记录保持一致。
	 * 由于mysql的事务隔离级别，默认为REPEATABLE READ(3),即可重复读
	 * 需要在hibernate的映射文件中指定：
	 * 	 	<!-- 设置hibernate的事务隔离级别 ,用整数来表示隔离级别-->
        	<property name="hibernate.connection.isolation">2</property>
        	属性来设置事务的隔离级别，改成READ COMMITED(2)
	 */
	@Test
	public void testRefresh(){
		News news = session.get(News.class, 1);
		System.out.println(news);
		
		session.refresh(news);
		System.out.println(news);
	}
	
	@Test
	public void testClear(){
		News news = session.get(News.class, 1);
		//清理session缓存
		session.clear();
		
		News news2 = session.get(News.class, 1);
	}

	/**
	 * save()方法：
	 * 	1、使一个临时对象变为持久化对象
	 * 	2、为对象分配主键id
	 * 	3、在flush缓存时，会发送一条insert语句
	 * 	4、在save之前设置的主键id是无效的
	 * 	5、持久化对象的主键id是无法被修改的
	 */
	@Test
	public void testSave() {
		// 执行数据库操作
		News news = new News();
		news.setAuthor("AA");
		news.setPublishDate(new Date());
		news.setTitle("aa");
		news.setId(100);

		System.out.println(news);
		session.save(news);
		System.out.println(news);

	}
	
	/**
	 * presist(): 也会执行insert操作
	 * 与save()区别：
	 * 	在presist()方法调用之前，若对象已经有主键id了，则不会执行insert，而是抛出异常。		
	 * 	
	 */
	@Test
	public void testPresist(){
		// 执行数据库操作
		News news = new News();
		news.setAuthor("BB");
		news.setPublishDate(new Date());
		news.setTitle("bb");
		//news.setId(1111);

		session.persist(news);
	}
	
	/**
	 * get VS load:
	 * 	1、执行get方法，会立即加载对象，而执行load方法，若不使用该对象，则不会立即执行查询操作，而返回一个代理对象。
	 * 		get是立即检索，load是延迟检索。
	 * 
	 * 	2、若数据库中没有对应的记录，且session也没有被关闭的时候，则
	 * 		get： 返回null，
	 * 		load： 若不使用该对象时，正常，但若需要使用对象时就会抛出异常。
	 * 
	 * 	3、load可能会抛出懒加载异常LazyInitializationException,即在需要初始化代理对象之前，session已经关闭。
	 */
	@Test
	public void testGet(){
		// 执行数据库操作
		News news = session.get(News.class, 1);
		System.out.println(news);
	}
	
	@Test
	public void testLoad(){
		// 执行数据库操作
		News news = session.load(News.class, 1);
		//session.close();
		System.out.println(news);
	}
	
	@Test
	public void testGetNull(){
		// 执行数据库操作
		News news = session.get(News.class, 10);
		System.out.println(news);
	}
	
	@Test
	public void testLoadNull(){
		// 执行数据库操作
		News news = session.load(News.class, 10);
		System.out.println(news);
	}
	
	/**
	 * update():
	 * 	1、若更新一个持久化对象，不需要显式调用session的update方法，
	 * 		因为在调用Transaction的commit方法时，会先执行session的flush方法。
	 * 
	 * 	2、更新一个游离对象，需要显式调用session的update方法，把游离对象变为持久化对象。
	 * 
	 * 需要注意：
	 * 	1、无论需要更新的的游离对象和数据库表的记录是否一致，都会执行update语句。
	 * 		为了解决update方法不再盲目地触发update语句，
	 * 		即需要更新的游离对象和数据库表的记录一致的话，就不执行update语句，
	 * 		需要在xxx.hbm.xml的class节点上添加select-before-update="true"(默认为false)的属性，通常不设置该值
	 * 		即：在执行update更新之前，会先执行select语句，如果一致，则不再执行update语句，反之则会执行update语句
	 * 
	 * 	2、若数据库表中没有游离对象主键相对应的记录，但还是调用了update方法，会抛出异常。
	 * 
	 * 	3、当update方法关联一个游离对象时，如果在session的缓存中已经存在相同的持久化对象，则会抛出异常，
	 * 		因为在session缓存中不能存在两个相同的持久化对象。
	 */
	@Test
	public void testUpdate(){
		// 执行数据库操作
		//持久化对象
		News news = session.get(News.class, 1);
		
		//news.setAuthor("XXX");
		
		tx.commit();
		session.close();
		
		//此时的news为游离对象
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		//关于第2点
		//news.setTitle("yyy");//正常
		//news.setId(1000);//抛出异常
		
		//关于第3点
		News news2 = session.get(News.class, 1);
		session.update(news);
	}
	
	/**
	 * saveOrUpdate()：
	 * 	若主键id不为null，但数据库表中还没有和其对应的记录，如果执行saveOrUpdate方法的话，会抛出异常
	 */
	@Test
	public void testSaveOrUpdate(){
		// save
		News news = new News();
		news.setAuthor("CC");
		news.setPublishDate(new Date());
		news.setTitle("cc");
		session.saveOrUpdate(news);
		
		System.out.println("----------------");
		//update
		News news2 = new News();
		news2.setAuthor("DD");
		news2.setPublishDate(new Date());
		news2.setTitle("dd");
		news2.setId(3);
		session.saveOrUpdate(news2);
	}
	
	/**
	 * delete():执行删除操作
	 * 	只要主键id和数据库表中一条记录对应，就会执行delete操作
	 * 	如果主键id和数据库表中没有对应的记录，则会抛出异常
	 * 
	 * 可以设置hibernate配置文件中的hibernate.use_identifier_rollback为true
	 * 	意思为：使删除对象后，将其主键id设为null
	 */
	@Test
	public void testDelete(){
		//游离对象
		News news = new News();
		news.setId(1);
		session.delete(news);//正常删除
		
		//持久化对象
		News news2 = session.get(News.class, 3);
		session.delete(news2);//正常删除
		
		//数据库中没有对应的记录
		News news3 = new News();
		news3.setId(11);
		session.delete(news3);//抛出异常
	}
	
	/**
	 * evict():从session缓存中把指定的持久化对象移除
	 * 	
	 */
	@Test
	public void testEvict(){
		News news1 = session.get(News.class, 1);
		News news2 = session.get(News.class, 2);
		
		news1.setAuthor("ZZZ");
		news2.setAuthor("yyy");
		
		//将news1持久化对象从session中移除，则最后只会执行news2的update操作，news1不会执行update操作
		session.evict(news1);
	}

}
