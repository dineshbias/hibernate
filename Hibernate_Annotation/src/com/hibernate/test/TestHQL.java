package com.hibernate.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hibernate.metadata.Employee;

public class TestHQL {

	public static void testALL(SessionFactory factory) {
		System.out.println("********************************************");
		Session session = factory.openSession();
		System.out.println("Begin Transaction..");
		Transaction tx = session.beginTransaction();

		testFROMClause(session);
		testGROUPBYclause(session);
		
		/*testUpdateSalary(session, 72, 10);
		testUpdateSalary(session, 85, 10);
		testUpdateSalary(session, 850087);

		testSELECTandASclause(session);
		testWHEREandASclause(session);
		
		testNamedParameters(session, 53);
*/
		System.out.println("Transaction commit ..");
		tx.commit();

		System.out.println("********************************************");
		testFROMClause(session);
		testPagination(session);
		testDeleteEmployee(session);
		testInsertEmployee(session);
		
		System.out.println("********************************************");

		System.out.println("Closing session..");
		session.close();

		System.out.println("********************************************");
	}

	public static void testFROMClause(Session session) {
		System.out.println("testFROMClause Enter....");

		String hql = "FROM com.hibernate.metadata.Employee";
		Query query = session.createQuery(hql);
		List<Employee> employeeList = query.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testFROMClause Exit ....");
	}

	public static void testSELECTandASclause(Session session) {
		System.out.println("testSELECTandASclause Enter....");

		String hql = "SELECT e.firstName, e.lastName FROM com.hibernate.metadata.Employee AS e";
		Query query = session.createQuery(hql);
		List<Object[]> employeeList = query.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Object[] employee : employeeList) {
			System.out.println(employee[0] + " " + employee[1]);
		}

		System.out.println("testSELECTandASclause Exit ....");
	}

	public static void testWHEREandASclause(Session session) {
		System.out.println("testWHEREandASclause Enter....");

		String hql = "FROM com.hibernate.metadata.Employee e where e.salary > 7000 order by e.firstName";
		Query query = session.createQuery(hql);
		List<Employee> employeeList = query.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
		System.out.println("testWHEREandASclause Exit ....");
	}

	public static void testGROUPBYclause(Session session) {
		System.out.println("testGROUPBYclause Enter....");

		String hql = "Select e.firstName, SUM(e.salary) FROM com.hibernate.metadata.Employee e group by e.firstName";
		Query query = session.createQuery(hql);
		List<Object[]> employeeList = query.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Object[] obj : employeeList) {
			System.out.println(obj[0] + " " + obj[1]);
		}
		System.out.println("testWHEREandASclause Exit ....");
	}

	public static void testNamedParameters(Session session, int employeeId) {
		System.out.println("testNamedParameters Enter....");

		String hql = "FROM com.hibernate.metadata.Employee e where e.id=:employeeId";
		Query query = session.createQuery(hql);
		query.setParameter("employeeId", employeeId);

		List<Employee> employeeList = query.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testNamedParameters Exit ....");
	}

	public static void testUpdateSalary(Session session, int employeeId,
			int salary) {
		System.out.println("testUpdateSalary Enter....");

		String hql = "UPDATE com.hibernate.metadata.Employee e set e.salary=:empSalary where e.id=:employeeId";
		Query query = session.createQuery(hql);
		query.setParameter("employeeId", employeeId);
		query.setParameter("empSalary", salary);

		int rowsUpdated = query.executeUpdate();

		System.out.println("Rows Updated.... " + rowsUpdated);

		System.out.println("testUpdateSalary Exit ....");
	}

	public static void testUpdateSalary(Session session, int salary) {
		System.out.println("testUpdateSalary Enter....");

		String hql = "UPDATE com.hibernate.metadata.Employee e set e.salary=:empSalary where e.firstName=:employeeName";
		Query query = session.createQuery(hql);
		query.setParameter("employeeName", "Daisy1");
		query.setParameter("empSalary", salary);

		int rowsUpdated = query.executeUpdate();

		System.out.println("Rows Updated.... " + rowsUpdated);

		System.out.println("testUpdateSalary Exit ....");
	}

	public static void testDeleteEmployee(Session session) {
		System.out.println("testDeleteEmployee Enter....");

		String hql = "DELETE from Employee e where e.firstName=:employeeName";
		Query query = session.createQuery(hql);
		query.setParameter("employeeName", "Daisy1");

		int rowsDeleted = query.executeUpdate();

		System.out.println("Rows Deleted.... " + rowsDeleted);

		System.out.println("testUpdateSalary Exit ....");
	}

	public static void testInsertEmployee(Session session) {
		System.out.println("testInsertEmployee Enter....");

		String hql = "INSERT into com.hibernate.metadata.EmployeeTemp (firstName,lastName,salary) "
				+ "SELECT firstName,lastName,salary from com.hibernate.metadata.Employee";
		Query query = session.createQuery(hql);
		int rowsCopied = query.executeUpdate();
		
		System.out.println("Rows Copied.... " + rowsCopied);
		System.out.println("testInsertEmployee Exit ....");
	}
	
	public static void testPagination(Session session) {
		System.out.println("testPagination Enter....");

		String hql = "FROM com.hibernate.metadata.Employee";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(5);
		
		List<Employee> employeeList = query.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testPagination Exit ....");
	}
}
