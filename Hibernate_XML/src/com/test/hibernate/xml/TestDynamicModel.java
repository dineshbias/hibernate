/**
 * 
 */
package com.test.hibernate.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.test.hibernate.xml.pojo.batch.entity.TestBatchProcessing;

/**
 * @author edinjos
 *
 */
public class TestDynamicModel {

	private static SessionFactory factory;

	static {
		Configuration config = new Configuration().configure("hibernateConfBatch.cfg.xml");
		factory = config.buildSessionFactory();
		System.out.println(
				Thread.currentThread().getName() + " " + TestBatchProcessing.class + " SessionFactory initialized...");
	}

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " main started ");

		//testDynamicMappingRetrieve(factory);
		//testDynamicMappingCreate(factory);
		testDynamicMappingRetrieve(factory);
		testDynamicMappingUpdate(factory, 3);
		testDynamicMappingRetrieve(factory);

		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " closing factory ");
		factory.close();
		System.out.println(Thread.currentThread().getName() + " " + TestBatchProcessing.class + " main exit ");
	}

	public static void testDynamicMappingCreate(SessionFactory factory) {
		System.out.println("-------------- testDynamicMappingCreate Start--------------");

		Map animal = new HashMap();
		animal.put("id", 3);
		animal.put("name", "Fish");
		animal.put("numAcres", 23);

		Session session = factory.openSession();

		Transaction trs = session.beginTransaction();
		System.out.println("-------------- testDynamicMappingCreate Transaction started--------------");

		session.save("com.test.hibernate.xml.Species", animal);

		System.out.println("-------------- testDynamicMappingCreate Transaction commited--------------");
		trs.commit();
		session.close();

		System.out.println("-------------- testDynamicMappingCreate End--------------");
	}

	public static void testDynamicMappingRetrieve(SessionFactory factory) {
		System.out.println("-------------- testDynamicMappingRetrieve Start--------------");
		Session session = factory.openSession();

		String hql = "FROM com.test.hibernate.xml.Species";
		Query query = session.createQuery(hql);
		List speciesData = query.list();
		System.out.println("-------------- testDynamicMappingRetrieve -------------- " + speciesData.size());

		speciesData.forEach((x) -> {
			System.out.println("");
			((Map) x).forEach((k, y) -> System.out.print(k + ":" + y + " "));
		});

		session.close();
		System.out.println("-------------- testDynamicMappingRetrieve End--------------");
	}

	public static void testDynamicMappingUpdate(SessionFactory factory, int id) {
		System.out.println("-------------- testDynamicMappingUpdate Start--------------");
		Session session = factory.openSession();

		Transaction trs = session.beginTransaction();

		Map loadedMap = (Map) session.load("com.test.hibernate.xml.Species", id);
		loadedMap.forEach((k, v) -> System.out.print(k + ":" + v + " "));

		loadedMap.put("name", "Dinasour");
		
		System.out.println("-------------- testDynamicMappingUpdate Commit Transaction--------------");
		trs.commit();
		session.close();
		System.out.println("-------------- testDynamicMappingUpdate End--------------");
	}

	public static void testDynamicMappingDelete(SessionFactory factory) {
		System.out.println("-------------- testDynamicMappingDelete Start--------------");
		Session session = factory.openSession();

		Transaction trs = session.beginTransaction();

		trs.commit();
		session.close();
		System.out.println("-------------- testDynamicMappingDelete End--------------");
	}
}
