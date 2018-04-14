package com.hibernate.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.hibernate.metadata.Employee;

public class TestCriteria {

	public static void testALL(SessionFactory factory) {
		System.out.println("********************************************");
		Session session = factory.openSession();
		System.out.println("Begin Transaction..");
		Transaction tx = session.beginTransaction();

		testCriteria(session);
		testCriteriaOrderBy(session);

		testCriteriaRestiction(session);
		testCriteriawithANDRestiction(session);
		testCriteriawithORRestiction(session);

		testCriteriaProjection(session);

		System.out.println("Commit Transaction..");
		tx.commit();
		System.out.println("Closing session..");
		session.close();
		System.out.println("********************************************");
	}

	public static void testCriteria(Session session) {

		System.out.println("testCriteria Enter ....");

		Criteria criteria = session.createCriteria(Employee.class);
		List<Employee> employeeList = criteria.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testCriteriaRestriction Exit ....");

	}

	public static void testCriteriaOrderBy(Session session) {

		System.out.println("testCriteriaOrderBy Enter ....");

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.addOrder(Order.asc("firstName"));
		List<Employee> employeeList = criteria.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testCriteriaOrderBy Exit ....");

	}

	public static void testCriteriaRestiction(Session session) {

		System.out.println("testCriteriaRestiction Enter ....");

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("firstName", "Daisy1"));
		criteria.add(Restrictions.gt("salary", 10000));

		List<Employee> employeeList = criteria.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testCriteriaRestiction Exit ....");

	}

	public static void testCriteriawithANDRestiction(Session session) {

		System.out.println("testCriteriawithANDRestiction Enter ....");

		Criterion firstName = Restrictions.eq("firstName", "Daisy1");
		Criterion salary = Restrictions.gt("salary", 10000);
		LogicalExpression exp = Restrictions.and(firstName, salary);

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(exp);

		List<Employee> employeeList = criteria.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testCriteriawithANDRestiction Exit ....");

	}

	public static void testCriteriawithORRestiction(Session session) {

		System.out.println("testCriteriawithORRestiction Enter ....");

		Criterion firstName = Restrictions.eq("firstName", "Daisy1");
		Criterion salary = Restrictions.gt("salary", 10000);
		LogicalExpression exp = Restrictions.or(firstName, salary);

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(exp);

		List<Employee> employeeList = criteria.list();

		System.out.println("Total Employees.... " + employeeList.size());

		for (Employee employee : employeeList) {
			System.out.println(employee);
		}

		System.out.println("testCriteriawithORRestiction Exit ....");

	}

	public static void testCriteriaProjection(Session session) {

		System.out.println("testCriteriaProjection Enter ....");

		Criteria criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.rowCount());
		List<Object> employeeList = criteria.list();

		System.out.println("Result.... " + employeeList.size());

		for (Object result : employeeList) {
			System.out.println("Result : " + result);
		}

		criteria.setProjection(Projections.avg("salary"));
		employeeList = criteria.list();

		System.out.println("Result.... " + employeeList.size());

		for (Object result : employeeList) {
			System.out.println("Result : " + result);
		}

		criteria.setProjection(Projections.min("salary"));
		employeeList = criteria.list();

		System.out.println("Result.... " + employeeList.size());

		for (Object result : employeeList) {
			System.out.println("Result : " + result);
		}

		criteria.setProjection(Projections.max("salary"));
		employeeList = criteria.list();

		System.out.println("Result.... " + employeeList.size());

		for (Object result : employeeList) {
			System.out.println("Result : " + result);
		}

		criteria.setProjection(Projections.sum("salary"));

		employeeList = criteria.list();

		System.out.println("Result.... " + employeeList.size());

		for (Object result : employeeList) {
			System.out.println("Result : " + result);
		}

		System.out.println("testCriteriaProjection Exit ....");

	}
}
