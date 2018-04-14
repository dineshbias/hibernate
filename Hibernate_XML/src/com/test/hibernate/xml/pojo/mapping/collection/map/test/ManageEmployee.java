package com.test.hibernate.xml.pojo.mapping.collection.map.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.test.hibernate.xml.pojo.mapping.collection.map.Certificate;
import com.test.hibernate.xml.pojo.mapping.collection.map.Employee;

public class ManageEmployee {

	private static SessionFactory factory;
	private static List<Employee> empList;
	private static List<Integer> empIdList;
	private static Map certMap1;
	private static Map certMap2;
	private static Map certMap3;
	private static List<Integer> certificateIds;

	static {
		System.out.println(ManageEmployee.class + " static block loading...");
		factory = new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
		System.out.println(ManageEmployee.class
				+ " SessionFactory initialized...");

		empList = new ArrayList<Employee>();
		empList.add(new Employee("Zara1", "Ali1", 1000));
		empList.add(new Employee("Daisy1", "Das1", 5000));
		empList.add(new Employee("John1", "Paul1", 10000));

		certMap1 = new HashMap();
		certMap2 = new HashMap();
		certMap3 = new HashMap();

		certMap2.put("LowerLevel", new Certificate("OCA"));
		certMap2.put("HigherLevel", new Certificate("OCP"));
		certMap2.put("HigherLevel", new Certificate("OCP"));
		certMap2.put("HigherLevel", new Certificate("Master"));

		certMap3.put("Management", new Certificate("ITIL"));
		certMap3.put("LowerLevel", new Certificate("OCX"));
	}

	public ManageEmployee() {
		System.out.println(this.getClass()
				+ "********* Instantiated **********");
	}

	public static void main(String... args) throws IOException {
		System.out.println("\n" + ManageEmployee.class + " main enter...");
		testEmployeeDB();
		System.out.println(ManageEmployee.class + " main exit...");
		factory.close();
	}

	public static void testEmployeeDB() {
		System.out.println("\n" + ManageEmployee.class
				+ " testEmployeeDB enter...");
		ManageEmployee me = new ManageEmployee();

		me.listEmployees();
		me.listCertificates();

		int count = 0;
		while (count < empList.size()) {
			switch (count) {
			case 0:
				me.addEmployee(empList.get(count), certMap1);
				break;
			case 1:
				me.addEmployee(empList.get(count), certMap2);
				break;
			default:
				me.addEmployee(empList.get(count), certMap3);

			}

			count++;
		}

		/*
		 * for (Employee emp : empList) { me.addEmployee(emp); }
		 */

		me.listEmployees();
		me.listCertificates();

		me.updateEmployee(71, 9999);

		me.listEmployees();
		me.listCertificates();

		for (int id : certificateIds) {
			//me.deleteCertificate(id);
		}
		for (int id : empIdList) {
			//me.deleteEmployee(id);
		}

		me.listEmployees();
		System.out.println(ManageEmployee.class + " testEmployeeDB exit...");
	}

	// Method to read all the employees
	public void listEmployees() {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " listEmployees.... "
				+ session);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");

			Query query = session
					.createQuery("FROM com.test.hibernate.xml.pojo.mapping.collection.map.Employee");
			System.out.println("Query Created....");
			List<Employee> employees = query.list();
			System.out.println(this.getClass() + " listEmployees.... "
					+ employees.size());
			empIdList = new ArrayList<>();

			for (Employee employee : employees) {
				empIdList.add(employee.getId());
				System.out.print(" Id: " + employee.getId());
				System.out.print(" First Name: " + employee.getFirstName());
				System.out.print(" Last Name: " + employee.getLastName());
				System.out.println(" Salary: " + employee.getSalary());
				
				Map certificates = employee.getCertificates();
				if(null != certificates){
					System.out.println(certificates);
				}
			}
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
	}

	public void listCertificates() {
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " listCertificates.... "
				+ session);
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Query query = session
					.createQuery(" FROM com.test.hibernate.xml.pojo.mapping.collection.map.Certificate");
			System.out.println("Query Created....");
			List<Certificate> certificates = query.list();
			System.out.println(this.getClass() + " listCertificates.... "
					+ certificates.size());

			certificateIds = new ArrayList<Integer>();
			for (Certificate certificate : certificates) {
				certificateIds.add(certificate.getId());
				System.out.print(" Id: " + certificate.getId());
				System.out.println(" Name: " + certificate.getName());
			}
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

	// Method to create an employee in database
	public Integer addEmployee(Employee employee, Map map) {

		Session session = factory.openSession();
		System.out
				.println("\n" + this.getClass() + " addEmployee.. " + session);
		Transaction tx = null;
		Integer employeeId = null;
		employee.setCertificates(map);
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			session.save(employee);
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
		return employeeId;
	}

	// Method to update Salary of employee
	public void updateEmployee(int id, int salary) {
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " updateEmployee.... "
				+ session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = (Employee) session.get(Employee.class, id);
			if (employee == null) {
				System.out.println(this.getClass()
						+ "**** employee not found with Id " + id);
				return;
			}
			System.out.println(this.getClass() + " updateEmployee.... "
					+ employee);
			employee.setSalary(salary);
			session.update(employee);
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
	}

	// Method to delete the employee record based on id
	public void deleteEmployee(int id) {
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " deleteEmployee.... " + id
				+ " " + session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = session.get(Employee.class, id);
			System.out.println(this.getClass() + " deleteEmployee.... "
					+ employee);
			session.delete(employee);
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

	// Method for deleting certificates
	public void deleteCertificate(int id) {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " deleteCertificate.... "
				+ id + " " + session);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Certificate certificate = session.get(Certificate.class, id);
			System.out.println(this.getClass() + " deleteCertificate.... "
					+ certificate);
			session.delete(certificate);
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

}
