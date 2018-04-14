/**
 * 
 */
package com.hibernate.test;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.entity.Employee;
import com.hibernate.entity.EmployeeC;
import com.hibernate.entity.EmployeeQC;

/**
 * @author edinjos
 *
 */
public class TestMain {

	public static SessionFactory factory;
	public static HashMap<Integer, Integer> empIDMap;
	public static HashMap<Integer, Integer> empCIDMap;

	static {
		System.out.println("Loading hibernate Configs started.....");

		empIDMap = new HashMap<Integer, Integer>();
		empCIDMap = new HashMap<Integer, Integer>();

		empIDMap.put(1, 1);
		empIDMap.put(2, 101);
		empIDMap.put(3, 201);
		empIDMap.put(4, 901);
		empIDMap.put(5, 401);

		empCIDMap.put(1, 201);
		empCIDMap.put(2, 901);
		empCIDMap.put(3, 401);
		empCIDMap.put(4, 501);
		empCIDMap.put(5, 601);

		Configuration config = new Configuration().configure("hibernate.cfg.xml");
		factory = config.buildSessionFactory();

		System.out.println("Statistics enabled : " + factory.getStatistics().isStatisticsEnabled());
		String[] regions = TestMain.factory.getStatistics().getSecondLevelCacheRegionNames();
		System.out.print("Regions :");
		for (String region : regions)
			System.out.print(region + " ");

		System.out.println("\nLoading hibernate Configs finished.....");
	}

	public static void main(String... args) throws InterruptedException {
		System.out.println("***Starting Main*****");
		TestMain test = new TestMain();

		Session session = factory.openSession();

		try {

			// test.addRecordsToDatabase(session);
			test.fetchAllRecordsFromDB();
			test.fetchAllRecordsFromDB();
			test.fetchAllRecordsFromDB();
			
			// testCachingUsingSingleThread();
			// testCachingMultipleThreads();

			// TestCaching.testFirstLevelCacheUsingCriteria(901);
			// TestCaching.testFirstLevelCacheNativeSQL(901);
			// TestCaching.testFirstLevelCacheUsingHQL(901);
			// TestCaching.testSecondLevelCacheUsingSession(901);

			//TestCaching.testQueryCaching("Chris");
			//TestCaching.testQueryCaching("Chris");
			//TestCaching.testQueryCaching("Chris");
			//TestCaching.testQueryCaching("Chris");

		} finally {
			System.out.println("Closing session...");
			session.close();
		}

		System.out.println("Closing session factory...");
		factory.close();
		System.out.println("***Exiting Main*****");
	}

	public void addRecordsToDatabase(Session session) {

		System.out.println(TestMain.class + " addRecordsToDatabase enter...");

		System.out.println(TestMain.class + " Begin transaction...");
		Transaction tx = session.beginTransaction();

		// Use HQL to add employee to database
		// EmployeeC employeeC = new EmployeeC("Brijesh", "Joshi", 77777);
		// Employee employee = new Employee("Kamlesh", "Giri", 88888);

		EmployeeQC employeeQC = new EmployeeQC("Chris", "Jonasson", 9999999);

		// session.save(employeeC);
		// session.save(employee);
		session.save(employeeQC);

		System.out.println(TestMain.class + " Commiting transaction...");
		tx.commit();

		System.out.println(TestMain.class + " addRecordsToDatabase exit...");
	}

	public void fetchAllRecordsFromDB() {
		System.out.println(TestMain.class + " fetchAllRecordsFromDB enter...");
		
		Session session = TestMain.factory.openSession();
		
		System.out.println(TestMain.class + " Begin transaction...");
		Transaction tx = session.beginTransaction();

		// Use Native Entity SQL
		String hql1 = "from com.hibernate.entity.EmployeeC";
		String hql2 = "from com.hibernate.entity.Employee";
		String hql3 = "from com.hibernate.entity.EmployeeQC";

		Query query1 = session.createQuery(hql1);
		query1.setCacheable(true);
		Query query2 = session.createQuery(hql2);
		query2.setCacheable(true);
		Query query3 = session.createQuery(hql3);
		query3.setCacheable(true);
		List<EmployeeC> list1 = query1.list();
		List<Employee> list2 = query2.list();
		List<EmployeeQC> list3 = query3.list();

		System.out.println("------------------------------------------");

		for (EmployeeC employeeC : list1) {
			//System.out.println(employeeC);
		}

		for (Employee employee : list2) {
			//System.out.println(employee);
		}

		for (EmployeeQC employee : list3) {
			//System.out.println(employee);
		}

		TestCaching.printStatisticsDetail();

		System.out.println("------------------------------------------");

		List<EmployeeC> list4 = query1.list();
		List<Employee> list5 = query2.list();
		List<EmployeeQC> list6 = query3.list();

		TestCaching.printStatisticsDetail();

		System.out.println("------------------------------------------");

		System.out.println(TestMain.class + " Commiting transaction...");
		tx.commit();
		
		session.close();
		System.out.println(TestMain.class + " fetchAllRecordsFromDB exit...");
	}

	public static void testCachingUsingSingleThread() throws InterruptedException {
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");

		for (int i = 1; i < 6; i++) {
			TestCaching.fetchCachedData(empCIDMap.get(i));
			System.out.println("-------------------------------------------------------- " + i);

			System.out.println("-------------------------------------------------------- " + i);
			TestCaching.fetchNonCachedData(empIDMap.get(i));
			Thread.sleep(1000);

		}
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");
	}

	public static void testCachingMultipleThreads() {
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");

		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(10);

			service.submit(() -> TestCaching.fetchCachedData(empCIDMap.get(1)));
			service.submit(() -> TestCaching.fetchNonCachedData(empIDMap.get(1)));
			service.submit(() -> TestCaching.fetchCachedData(empCIDMap.get(2)));
			service.submit(() -> TestCaching.fetchNonCachedData(empIDMap.get(2)));

		} finally {
			if (null != service) {
				service.shutdown();
			}
		}

		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");
	}

}
