/**
 * 
 */
package com.test.hibernate.xml.pojo.interceptor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.test.hibernate.xml.pojo.batch.entity.EmployeeB;

public class TestSessionFactoryScopedInterceptor {

	private static SessionFactory factory;

	static {
		System.out.println(Thread.currentThread()
				+ " loading hibernate configs...");

		Configuration config = new Configuration().configure(
				"hibernateConfBatch.cfg.xml").setInterceptor(
				new LoggerInterceptor());
		factory = config.buildSessionFactory();

		System.out.println(Thread.currentThread()
				+ " loaded hibernate configs...");
	}

	public static void main(String... args) {
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		System.out.println(Thread.currentThread() + " Main started...");

		testInterceptors();

		System.out.println(Thread.currentThread() + " Closing factory....");
		factory.close();

		System.out.println(Thread.currentThread() + " Main exit...");
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
	}

	public static void testInterceptors() {
		System.out.println(Thread.currentThread()
				+ " testInterceptors started...");

		System.out.println(Thread.currentThread() + " Open session ...");
		Session session = factory.openSession();
		
		
		
		System.out.println(Thread.currentThread() + " Begin  Transaction ...");
		Transaction tx = session.beginTransaction();

		try {
			
			System.out.println(Thread.currentThread() + " Get Employee ... " + 105);
			EmployeeB empB = session.get(EmployeeB.class, 105);
			
			System.out.println(Thread.currentThread() + " Loaded Employee ... " + empB);
			
			empB.setCityName("PUNE");
			empB.setStateName("MAHARASTRA");
			
			System.out.println(Thread.currentThread() + " Update Employee ... " );
			session.save(empB);
			
		} catch (Exception e) {
			System.out.println(Thread.currentThread()
					+ " " +  e);
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			System.out.println(Thread.currentThread()
					+ " Commit Transaction ...");
			tx.commit();
		}
		
		System.out.println(Thread.currentThread()
				+ " closing session ...");
		session.close();
		
		System.out.println(Thread.currentThread()
				+ " testInterceptors exit...");
	}

}
