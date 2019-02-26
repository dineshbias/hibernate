package com.test.hibernate.xml.pojo.batch.entity;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class TestBatchProcessing {

	private static SessionFactory factory;

	static {
		Configuration config = new Configuration().configure("hibernateConfBatch.cfg.xml");
		factory = config.buildSessionFactory();
		System.out.println(
				Thread.currentThread().getName() + " " + TestBatchProcessing.class + " SessionFactory initialized...");
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " main started ");
		printNumberOfRows(null);
		testBatchInserts(50);
		testBatchUpdates();
		deleteRecords();
		printNumberOfRows(null);
		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " closing factory ");
		factory.close();
		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " main exit ");
	}

	public static void printNumberOfRows(Session session) {
		
		if(null==session)
			session = factory.openSession();
		
		try {
			Query query = session
					.createQuery("SELECT count(*) FROM com.test.hibernate.xml.pojo.batch.entity.EmployeeB");
			List<Object> count = query.list();
			System.out
			.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " printNumberOfRows .. " + count);

		} catch(Exception e){
			e.printStackTrace();
		}
			finally {
		
			System.out
					.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " Closing session ");
			session.close();
		}

	}

	public static void testBatchInserts(int noOfRecords) {
		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " insertRecords .. "
				+ noOfRecords);

		System.out.println(Thread.currentThread().getName() + " " + "Opening Session");
		Session session = factory.openSession();
		System.out.println(Thread.currentThread().getName() + " " + "Begin Transaction");
		Transaction trs = session.beginTransaction();
		for (int i = 0; i < noOfRecords; i++) {
			EmployeeB employeeB = new EmployeeB("Ramesh" + i, "Kumar" + i, 1000 + i, "SUBHASH" + i, "ROORKEE",
					"UTTARAKHAND", "247667");
			session.save(employeeB);
			
			
			if (i % 20 == 0) {
				session.flush();
				session.clear();
				
			}
		}

		System.out.println(Thread.currentThread().getName() + " " + "Commiting Transaction");
		trs.commit();
		System.out.println(Thread.currentThread().getName() + " " + "Closing Session");
		session.close();
	}

	public static void testBatchUpdates() {
		System.out
				.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " testBatchUpdates .. ");

		System.out.println(Thread.currentThread().getName() + " " + "Opening Session");
		Session session = factory.openSession();
		System.out.println(Thread.currentThread().getName() + " " + "Begin Transaction");
		Transaction trs = session.beginTransaction();
		
		ScrollableResults results = session.getNamedQuery("getAllEmployeeBs").setCacheMode(CacheMode.IGNORE)
				.scroll(ScrollMode.FORWARD_ONLY);

		System.out.println(Thread.currentThread().getName() + " " + "ScrollableResults : " + results);
		int count = 0;
		while (results.next()) {
			EmployeeB employeeB = (EmployeeB) results.get(0);
			employeeB.setStateName("UTTAR PRADESH");

			if (++count % 20 == 0) {
				// flush a batch of updates and release memory:
				session.flush();
				session.clear();
			}
		}
		System.out.println(Thread.currentThread().getName() + " " + "Records updated in primary cache");
		System.out.println(Thread.currentThread().getName() + " " + "Commiting Transaction");
		trs.commit();
		System.out.println(Thread.currentThread().getName() + " " + "Closing Session");
		session.close();
	}

	public static void deleteRecords() {
		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " deleteRecords .. ");

		System.out.println(Thread.currentThread().getName() + " " + "Opening Session");
		Session session = factory.openSession();
		System.out.println(Thread.currentThread().getName() + " " + "Begin Transaction");
		Transaction trs = session.beginTransaction();

		Query query = session.createQuery("DELETE FROM com.test.hibernate.xml.pojo.batch.entity.EmployeeB");
		int rowsCount = query.executeUpdate();

		System.out.println(Thread.currentThread().getName() + " " + "rowsCount : " + rowsCount);
		System.out.println(Thread.currentThread().getName() + " " + "Commiting Transaction");
		trs.commit();
		System.out.println(Thread.currentThread().getName() + " " + "Closing Session");
		session.close();
	}
}
