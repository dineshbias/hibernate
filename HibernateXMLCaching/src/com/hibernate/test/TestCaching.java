/**
 * 
 */
package com.hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.hibernate.entity.Employee;
import com.hibernate.entity.EmployeeC;
import com.hibernate.entity.EmployeeQC;

/**
 * @author edinjos
 *
 */
public class TestCaching {

	public static void fetchCachedData(int id) {
		try {
			System.out
					.println(Thread.currentThread().getName() + "  " + "***Starting TestCaching.fetchCachedData*****");

			System.out
					.println(Thread.currentThread().getName() + "  " + "TestCaching.fetchCachedData - Opening session");
			Session session = TestMain.factory.openSession();
			System.out.println(
					Thread.currentThread().getName() + "  " + "TestCaching.fetchCachedData - Begin transaction");
			Transaction tx = session.beginTransaction();

			EmployeeC employeeC = session.get(EmployeeC.class, id);
			System.out.println(Thread.currentThread().getName() + "  " + " " + employeeC.toString());
			printStatisticsDetail();

			employeeC = session.get(EmployeeC.class, id);
			System.out.println(Thread.currentThread().getName() + "  " + " " + employeeC.toString());
			printStatisticsDetail();

			System.out.println("TestCaching.fetchCachedData - Commiting transaction");
			tx.commit();
			System.out.println("TestCaching.fetchCachedData - Session closed");
			session.close();
			
			System.out
					.println(Thread.currentThread().getName() + "  " + "TestCaching.fetchCachedData - Opening session");
			Session session2 = TestMain.factory.openSession();
			System.out.println(
					Thread.currentThread().getName() + "  " + "TestCaching.fetchCachedData - Begin transaction");
			Transaction tx2 = session2.beginTransaction();

			EmployeeC employee2C = session2.get(EmployeeC.class, id);
			System.out.println(Thread.currentThread().getName() + "  " + " " + employee2C.toString());
			printStatisticsDetail();

			System.out.println("TestCaching.fetchCachedData - Commiting transaction");
			tx2.commit();

			System.out.println("TestCaching.fetchCachedData - Transaction commited");

			// session.flush();
			// session2.flush();
			// System.out.println(Thread.currentThread().getName() +
			// " session flushed");
			
			session2.close();
			System.out.println(Thread.currentThread().getName()
					+ " *********************session closed**************************");

			System.out.println(Thread.currentThread().getName() + "  " + "***Exit TestCaching.fetchCachedData*****");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void fetchNonCachedData(int id) {
		System.out.println(Thread.currentThread().getName() + "  " + "***Starting TestCaching.fetchNonCachedData*****");

		System.out
				.println(Thread.currentThread().getName() + "  " + "TestCaching.fetchNonCachedData - Opening session");
		Session session = TestMain.factory.openSession();
		System.out.println("TestCaching.fetchNonCachedData - Begin transaction");
		Transaction tx = session.beginTransaction();

		Employee Employee = session.get(Employee.class, id);
		System.out.println(Thread.currentThread().getName() + "  " + " " + Employee.toString());
		printStatisticsDetail();

		System.out.println("TestCaching.fetchNonCachedData - Commiting transaction");
		tx.commit();

		System.out.println("TestCaching.fetchNonCachedData - Transaction commited");

		System.out
				.println(Thread.currentThread().getName() + "  " + "TestCaching.fetchNonCachedData - Opening session");
		Session session2 = TestMain.factory.openSession();
		System.out.println("TestCaching.fetchNonCachedData - Begin transaction");
		Transaction tx2 = session2.beginTransaction();

		Employee employee2C = session2.get(Employee.class, id);
		System.out.println(Thread.currentThread().getName() + "  " + " " + employee2C);
		printStatisticsDetail();

		System.out.println("TestCaching.fetchNonCachedData - Commiting transaction");
		tx2.commit();

		System.out.println("TestCaching.fetchNonCachedData - Transaction commited");

		System.out.println(Thread.currentThread().getName() + "  " + "***Exit TestCaching.fetchNonCachedData*****");
	}

	public static void printStatisticsDetail() {
		System.out.println("<-------------------START----------------------------------------------->");
		System.out.println("<------------------------------------------------------------------>");
		System.out.println(Thread.currentThread().getName() + "  " + "EntityFetchCount :"
				+ TestMain.factory.getStatistics().getEntityFetchCount());
		System.out.println(Thread.currentThread().getName() + "  " + "SecondLevelCacheHitCount :"
				+ TestMain.factory.getStatistics().getSecondLevelCacheHitCount());
		System.out.println(Thread.currentThread().getName() + "  " + "SecondLevelCacheMissCount :"
				+ TestMain.factory.getStatistics().getSecondLevelCacheMissCount());
		System.out.println("<------------------------------------------------------------------>");
		System.out.println("<--------------------END------------------------------------------------>");
	}

	public static void testFirstLevelCacheUsingHQL(int id) {
		System.out.println(Thread.currentThread().getName() + "  "
				+ "***Enter TestCaching.testFirstLevelCacheUsingHQL***** " + id);
		System.out.println(
				Thread.currentThread().getName() + "  " + "TestCaching.testFirstLevelCacheUsingHQL - Open Session");
		Session session = TestMain.factory.openSession();
		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testFirstLevelCacheUsingHQL - Begin transaction");
		Transaction tx = session.beginTransaction();
		try {

			String hql = "FROM com.hibernate.entity.EmployeeC where id=" + id;

			Query query = session.createQuery(hql);
			List<EmployeeC> list = query.list();
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingHQL - Rows loaded " + list.size());

			EmployeeC employeeC = list.get(0);

			employeeC.setFirstName("XXX");
			employeeC.setLastName("Bond007");
			employeeC.setSalary(1000000000);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingHQL - Updating employee : " + employeeC);

			session.saveOrUpdate(employeeC);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingHQL - employee saved ");

		} finally {
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingHQL - Commit transaction");
			tx.commit();
		}

		printStatisticsDetail();

		System.out.println(
				Thread.currentThread().getName() + "  " + "TestCaching.testFirstLevelCacheUsingHQL - session close");
		session.close();
		System.out.println(
				Thread.currentThread().getName() + "  " + "***Exit TestCaching.testFirstLevelCacheUsingHQL*****");
	}

	public static void testFirstLevelCacheUsingCriteria(int id) {
		System.out.println(Thread.currentThread().getName() + "  "
				+ "***Enter TestCaching.testFirstLevelCacheUsingCriteria***** " + id);
		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testFirstLevelCacheUsingCriteria - Open Session");
		Session session = TestMain.factory.openSession();
		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testFirstLevelCacheUsingCriteria - Begin transaction");
		Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(EmployeeC.class);
			criteria.add(Restrictions.eq("id", id));
			List<EmployeeC> list = criteria.list();
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingCriteria - Rows loaded " + list.size());

			EmployeeC employee = list.get(0);

			employee.setFirstName("090-439");
			employee.setLastName("Bhumma");
			employee.setSalary(10000);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingCriteria - Updating employee : " + employee);

			session.saveOrUpdate(employee);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingCriteria - employee saved ");
			
			employee.setFirstName("Fucker");
			session.saveOrUpdate(employee);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingCriteria - employee saved ");
		} finally {
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingCriteria - Commit transaction");
			tx.commit();
		}

		printStatisticsDetail();
		temp(session, id);
		temp(session, id);

		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testFirstLevelCacheUsingCriteria - session close");
		session.close();
		System.out.println(
				Thread.currentThread().getName() + "  " + "***Exit TestCaching.testFirstLevelCacheUsingCriteria*****");
	}

	public static void testFirstLevelCacheNativeSQL(int id) {
		System.out.println(Thread.currentThread().getName() + "  "
				+ "***Enter TestCaching.testFirstLevelCacheNativeSQL***** " + id);
		System.out.println(
				Thread.currentThread().getName() + "  " + "TestCaching.testFirstLevelCacheNativeSQL - Open Session");
		Session session = TestMain.factory.openSession();
		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testFirstLevelCacheNativeSQL - Begin transaction");
		Transaction tx = session.beginTransaction();
		try {
			String query = "Select * from EMPLOYEE_CACHED where id=:id";
			SQLQuery q = session.createSQLQuery(query);
			q.addEntity(EmployeeC.class);
			q.setParameter("id", id);

			List<EmployeeC> list = q.list();
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheNativeSQL - Rows loaded " + list.size());

			EmployeeC employee = list.get(0);
			employee.setFirstName("XXX");
			employee.setLastName("Bond007");
			employee.setSalary(1000000000);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheNativeSQL - Updating employee : " + employee);

			session.saveOrUpdate(employee);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheNativeSQL - employee saved ");

		} finally {
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheNativeSQL - Commit transaction");
			tx.commit();
		}

		printStatisticsDetail();

		System.out.println(
				Thread.currentThread().getName() + "  " + "TestCaching.testFirstLevelCacheNativeSQL - session close");
		session.close();
		System.out.println(
				Thread.currentThread().getName() + "  " + "***Exit TestCaching.testFirstLevelCacheNativeSQL*****");
	}

	public static void testSecondLevelCacheUsingSession(int id) {
		System.out.println(Thread.currentThread().getName() + "  "
				+ "***Enter TestCaching.testSecondLevelCacheUsingSession***** " + id);
		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testSecondLevelCacheUsingSession - Open Session");
		Session session = TestMain.factory.openSession();
		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testSecondLevelCacheUsingSession - Begin transaction");
		Transaction tx = session.beginTransaction();
		try {

			EmployeeC employeeC = session.get(EmployeeC.class, id);

			employeeC.setFirstName("ABC");
			employeeC.setLastName("Bond007");
			employeeC.setSalary(10000);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testSecondLevelCacheUsingSession - Updating employee : " + employeeC);

			session.saveOrUpdate(employeeC);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testSecondLevelCacheUsingSession - employee saved ");

		} finally {
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testSecondLevelCacheUsingSession - Commit transaction");
			tx.commit();
		}

		printStatisticsDetail();

		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testSecondLevelCacheUsingSession - session close");
		session.close();
		System.out.println(
				Thread.currentThread().getName() + "  " + "***Exit TestCaching.testSecondLevelCacheUsingSession*****");
	}

	public static void temp(Session session, int id) {
		System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP " + id);
		System.out.println(Thread.currentThread().getName() + "  "
				+ "TestCaching.testSecondLevelCacheUsingSession - Begin transaction");
		Transaction tx = session.beginTransaction();
		try {

			EmployeeC employeeC = session.get(EmployeeC.class, id);

			employeeC.setFirstName("XXX");
			employeeC.setLastName("Bond007");
			employeeC.setSalary(1000000000);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testSecondLevelCacheUsingSession - Updating employee : " + employeeC);

			session.saveOrUpdate(employeeC);

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testSecondLevelCacheUsingSession - employee saved ");

		} finally {
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testSecondLevelCacheUsingSession - Commit transaction");
			tx.commit();
		}

		printStatisticsDetail();
	}

	public static void testQueryCaching(String fname) {
		System.out.println(
				Thread.currentThread().getName() + "  " + "***Enter TestCaching.testQueryCaching***** " + fname);
		System.out.println(Thread.currentThread().getName() + "  " + "TestCaching.testQueryCaching - Open Session");
		Session session = TestMain.factory.openSession();
		System.out
				.println(Thread.currentThread().getName() + "  " + "TestCaching.testQueryCaching - Begin transaction");
		Transaction tx = session.beginTransaction();
		try {

			String hql = "FROM com.hibernate.entity.EmployeeQC where salary>1";
			// + fname;
			Query query = session.createQuery(hql);
			query.setCacheable(true);

			List<EmployeeQC> list = query.list();
			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testFirstLevelCacheUsingHQL - Rows loaded " + list.size());

			EmployeeQC employeeQC = list.get(0);

			employeeQC.setLastName("Jana");

			System.out.println(Thread.currentThread().getName() + "  "
					+ "TestCaching.testQueryCaching - Updating employee : " + employeeQC);

			session.saveOrUpdate(employeeQC);

			System.out.println(
					Thread.currentThread().getName() + "  " + "TestCaching.testQueryCaching - employee saved ");

		} finally {
			System.out.println(
					Thread.currentThread().getName() + "  " + "TestCaching.testQueryCaching - Commit transaction");
			tx.commit();
		}

		printStatisticsDetail();

		System.out.println(Thread.currentThread().getName() + "  " + "TestCaching.testQueryCaching - session close");
		session.close();
		System.out.println(Thread.currentThread().getName() + "  " + "***Exit TestCaching.testQueryCaching*****");
	}
}
