package com.test.hibernate.xml.pojo.mapping.collection.list.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.test.hibernate.xml.pojo.mapping.collection.list.Certificate;
import com.test.hibernate.xml.pojo.mapping.collection.list.Employee;

public class ManageEmployee {

	private static SessionFactory factory;
	private static List<Employee> empList;
	private static List<Integer> empIdList;
	private static List<Certificate> certList1;
	private static List<Certificate> certList2;
	private static List<Certificate> certList3;
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

		certList1 = new ArrayList<Certificate>();
		certList2 = new ArrayList<Certificate>();
		certList3 = new ArrayList<Certificate>();

		certList2.add(new Certificate("OCA"));
		certList2.add(new Certificate("OCP"));
		certList2.add(new Certificate("OCP"));

		certList3.add(new Certificate("ITIL"));
	}

	public ManageEmployee() {
		System.out.println(this.getClass()
				+ "********* Instantiated **********");
	}

	public static void main(String... args) throws IOException {
		System.out.println("\n" + ManageEmployee.class + " main enter...");
		testEmployeeDB();
		System.out.println(ManageEmployee.class + " main exit...");
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
				me.addEmployee(empList.get(count), certList1);
				break;
			case 1:
				me.addEmployee(empList.get(count), certList2);
				break;
			default:
				me.addEmployee(empList.get(count), certList3);

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

			Query query = session.createQuery("FROM com.test.hibernate.xml.pojo.mapping.collection.list.Employee");
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
			Query query = session.createQuery(" FROM com.test.hibernate.xml.pojo.mapping.collection.list.Certificate");
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
	public Integer addEmployee(Employee employee, List<Certificate> certificates) {

		Session session = factory.openSession();
		System.out
				.println("\n" + this.getClass() + " addEmployee.. " + session);
		Transaction tx = null;
		Integer employeeId = null;
		employee.setCertificates(certificates);
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
