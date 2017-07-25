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
		// ����һ��SessionFactory����
		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();

		// ����һ��Session����
		session = sessionFactory.openSession();

		// ��������
		tx = session.beginTransaction();

	}

	@After
	public void destroy() {
		// �ύ����
		tx.commit();

		// �ر�Session
		session.close();

		// �ر�SessionFactory
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
	 * flush: ʹ���ݱ��еļ�¼��Session�����еĶ����״̬����һ�£�Ϊ�˱���һ�£�����ܻᷢ�Ͷ�Ӧ��SQL���
	 * 	1������Transaction��commit()�����У��ȵ���session��flush()���������ύ����
	 * 	2��flush()���ܻᷢ��sql��䣬�������ύ����
	 * 	3��ע�⣺��δ�ύ�������ʽ�ص���session��flush()����֮ǰ��Ҳ�п��ܻ����flush������
	 * 		(1)��ִ��HQL����QBC��ѯ�����Ƚ���flush�������Եõ����ݱ����µļ�¼
	 * 		(2)������¼��ID���ɵײ����ݿ�ʹ�������ķ�ʽ���ɵģ����ڵ���save()����ʱ���ͻ���������insert��䣬
	 * 			��Ϊsave�����󣬱��뱣֤�����ID�Ǵ��ڵģ�
	 */
	@Test
	public void testFlush(){
		News news = session.get(News.class, 1);
		news.setAuthor("Amy");
		
	}
	
	/*
	 * refresh:��ǿ�Ʒ���select��䣬��ʹsession�����ж����״̬�����ݿ���еĶ����¼����һ�¡�
	 * ����mysql��������뼶��Ĭ��ΪREPEATABLE READ(3),�����ظ���
	 * ��Ҫ��hibernate��ӳ���ļ���ָ����
	 * 	 	<!-- ����hibernate��������뼶�� ,����������ʾ���뼶��-->
        	<property name="hibernate.connection.isolation">2</property>
        	��������������ĸ��뼶�𣬸ĳ�READ COMMITED(2)
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
		//����session����
		session.clear();
		
		News news2 = session.get(News.class, 1);
	}

	/**
	 * save()������
	 * 	1��ʹһ����ʱ�����Ϊ�־û�����
	 * 	2��Ϊ�����������id
	 * 	3����flush����ʱ���ᷢ��һ��insert���
	 * 	4����save֮ǰ���õ�����id����Ч��
	 * 	5���־û����������id���޷����޸ĵ�
	 */
	@Test
	public void testSave() {
		// ִ�����ݿ����
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
	 * presist(): Ҳ��ִ��insert����
	 * ��save()����
	 * 	��presist()��������֮ǰ���������Ѿ�������id�ˣ��򲻻�ִ��insert�������׳��쳣��		
	 * 	
	 */
	@Test
	public void testPresist(){
		// ִ�����ݿ����
		News news = new News();
		news.setAuthor("BB");
		news.setPublishDate(new Date());
		news.setTitle("bb");
		//news.setId(1111);

		session.persist(news);
	}
	
	/**
	 * get VS load:
	 * 	1��ִ��get���������������ض��󣬶�ִ��load����������ʹ�øö����򲻻�����ִ�в�ѯ������������һ���������
	 * 		get������������load���ӳټ�����
	 * 
	 * 	2�������ݿ���û�ж�Ӧ�ļ�¼����sessionҲû�б��رյ�ʱ����
	 * 		get�� ����null��
	 * 		load�� ����ʹ�øö���ʱ��������������Ҫʹ�ö���ʱ�ͻ��׳��쳣��
	 * 
	 * 	3��load���ܻ��׳��������쳣LazyInitializationException,������Ҫ��ʼ���������֮ǰ��session�Ѿ��رա�
	 */
	@Test
	public void testGet(){
		// ִ�����ݿ����
		News news = session.get(News.class, 1);
		System.out.println(news);
	}
	
	@Test
	public void testLoad(){
		// ִ�����ݿ����
		News news = session.load(News.class, 1);
		//session.close();
		System.out.println(news);
	}
	
	@Test
	public void testGetNull(){
		// ִ�����ݿ����
		News news = session.get(News.class, 10);
		System.out.println(news);
	}
	
	@Test
	public void testLoadNull(){
		// ִ�����ݿ����
		News news = session.load(News.class, 10);
		System.out.println(news);
	}
	
	/**
	 * update():
	 * 	1��������һ���־û����󣬲���Ҫ��ʽ����session��update������
	 * 		��Ϊ�ڵ���Transaction��commit����ʱ������ִ��session��flush������
	 * 
	 * 	2������һ�����������Ҫ��ʽ����session��update����������������Ϊ�־û�����
	 * 
	 * ��Ҫע�⣺
	 * 	1��������Ҫ���µĵ������������ݿ��ļ�¼�Ƿ�һ�£�����ִ��update��䡣
	 * 		Ϊ�˽��update��������äĿ�ش���update��䣬
	 * 		����Ҫ���µ������������ݿ��ļ�¼һ�µĻ����Ͳ�ִ��update��䣬
	 * 		��Ҫ��xxx.hbm.xml��class�ڵ������select-before-update="true"(Ĭ��Ϊfalse)�����ԣ�ͨ�������ø�ֵ
	 * 		������ִ��update����֮ǰ������ִ��select��䣬���һ�£�����ִ��update��䣬��֮���ִ��update���
	 * 
	 * 	2�������ݿ����û����������������Ӧ�ļ�¼�������ǵ�����update���������׳��쳣��
	 * 
	 * 	3����update��������һ���������ʱ�������session�Ļ������Ѿ�������ͬ�ĳ־û���������׳��쳣��
	 * 		��Ϊ��session�����в��ܴ���������ͬ�ĳ־û�����
	 */
	@Test
	public void testUpdate(){
		// ִ�����ݿ����
		//�־û�����
		News news = session.get(News.class, 1);
		
		//news.setAuthor("XXX");
		
		tx.commit();
		session.close();
		
		//��ʱ��newsΪ�������
		
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		
		//���ڵ�2��
		//news.setTitle("yyy");//����
		//news.setId(1000);//�׳��쳣
		
		//���ڵ�3��
		News news2 = session.get(News.class, 1);
		session.update(news);
	}
	
	/**
	 * saveOrUpdate()��
	 * 	������id��Ϊnull�������ݿ���л�û�к����Ӧ�ļ�¼�����ִ��saveOrUpdate�����Ļ������׳��쳣
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
	 * delete():ִ��ɾ������
	 * 	ֻҪ����id�����ݿ����һ����¼��Ӧ���ͻ�ִ��delete����
	 * 	�������id�����ݿ����û�ж�Ӧ�ļ�¼������׳��쳣
	 * 
	 * ��������hibernate�����ļ��е�hibernate.use_identifier_rollbackΪtrue
	 * 	��˼Ϊ��ʹɾ������󣬽�������id��Ϊnull
	 */
	@Test
	public void testDelete(){
		//�������
		News news = new News();
		news.setId(1);
		session.delete(news);//����ɾ��
		
		//�־û�����
		News news2 = session.get(News.class, 3);
		session.delete(news2);//����ɾ��
		
		//���ݿ���û�ж�Ӧ�ļ�¼
		News news3 = new News();
		news3.setId(11);
		session.delete(news3);//�׳��쳣
	}
	
	/**
	 * evict():��session�����а�ָ���ĳ־û������Ƴ�
	 * 	
	 */
	@Test
	public void testEvict(){
		News news1 = session.get(News.class, 1);
		News news2 = session.get(News.class, 2);
		
		news1.setAuthor("ZZZ");
		news2.setAuthor("yyy");
		
		//��news1�־û������session���Ƴ��������ֻ��ִ��news2��update������news1����ִ��update����
		session.evict(news1);
	}

}
