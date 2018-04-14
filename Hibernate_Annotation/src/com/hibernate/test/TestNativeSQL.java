/**
 * 
 */
package com.hibernate.test;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hibernate.metadata.Employee;

/**
 * @author edinjos
 *
 */
public class TestNativeSQL {

	public static void testALL(SessionFactory factory) {
		System.out.println("********************************************");
		Session session = factory.openSession();
		System.out.println("Begin Transaction..");
		Transaction tx = session.beginTransaction();

		testScalarNativeQuery(session);
		testEntityNativeQuery(session);
		testNamedSQLQuery(session, "Daisy1");
		testDeleteNativeQuery(session);

		System.out.println("Commiting Transaction..");
		tx.commit();
		System.out.println("Closing session..");
		session.close();
		System.out.println("********************************************");
	}

	public static void testScalarNativeQuery(Session session) {
		System.out.println("testScalarNativeQuery Enter....");

		String sql = "Select FIRST_NAME, LAST_NAME, SALARY from EMPLOYEE_ANNOTATION where LAST_NAME='Ali1'";
		SQLQuery query = session.createSQLQuery(sql);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		List<HashMap<Object, Object>> list = query.list();
		for (HashMap<Object, Object> obj : list) {
			obj.forEach((k, v) -> System.out.println(k + " " + v));

		}
		
		
		SQLQuery query2 = session.createSQLQuery(sql);
		

		List<Object[]> list2 = query2.list();
		for (Object[] obj : list2) {
			System.out.println(" ");
			int i=0;
			while(obj.length>i){
				System.out.print(obj[i]+" ");
				i++;
			}

		}
		System.out.println(" ");
		System.out.println("testScalarNativeQuery Exit ....");
	}

	public static void testEntityNativeQuery(Session session) {
		System.out.println("testEntityNativeQuery Enter....");

		String sql = "Select * from EMPLOYEE_ANNOTATION where LAST_NAME='Das1'";
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Employee.class);

		List<Employee> empList = query.list();
		for (Employee employee : empList) {
			System.out.println(employee);
		}

		System.out.println("testEntityNativeQuery Exit ....");
	}

	public static void testNamedSQLQuery(Session session, String firstName) {
		System.out.println("testNamedSQLQuery Enter....");

		String sql = "Select * from EMPLOYEE_ANNOTATION where FIRST_NAME=:fName";
		
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Employee.class);
		query.setParameter("fName", firstName);
		
		List<Employee> empList = query.list();
		for (Employee employee : empList) {
			System.out.println(employee);
		}
		
		System.out.println("testNamedSQLQuery Enter....");
	}

	public static void testDeleteNativeQuery(Session session) {
		System.out.println("testEntityNativeQuery Enter....");

		String sql = "DELETE from EMPLOYEE_ANNOTATION where id>1";
		SQLQuery query = session.createSQLQuery(sql);

		int rows = query.executeUpdate();

		System.out.println("Rows deleted : " + rows);

		System.out.println("testEntityNativeQuery Exit ....");
	}
}
