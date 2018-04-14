package com.hibernate.association.oto.ufka.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.association.oto.ufka.Address;
import com.hibernate.association.oto.ufka.Employee;

public class ManageEmployee {

	private static SessionFactory factory;
	private static List<Employee> empList;
	private static List<Integer> empIdList;

	private static Address address;
	private static List<Integer> addressIdList;

	static {
		System.out.println(ManageEmployee.class + " static block loading...");
		factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		System.out.println(ManageEmployee.class + " SessionFactory initialized...");

		empList = new ArrayList<Employee>();
		empList.add(new Employee("Zara1", "Ali1", 1000));
		empList.add(new Employee("Daisy1", "Das1", 5000));
		empList.add(new Employee("John1", "Paul1", 10000));

		address = new Address("Subhash Nagar", "Roorkee", "UTTARAKHAND", "247667");

	}

	public ManageEmployee() {
		System.out.println(this.getClass() + "********* Instantiated **********");
	}

	public static void main(String... args) throws IOException {
		System.out.println("\n" + ManageEmployee.class + " main enter...");
		System.out.println("\n" + ManageEmployee.class
				+ " Testing one to one association mapping using unique froeign key association...");
		testEmployeeDB();
		factory.close();
		System.out.println(ManageEmployee.class + " main exit...");

	}

	public static void testEmployeeDB() {
		System.out.println("\n" + ManageEmployee.class + " testEmployeeDB enter...");
		ManageEmployee me = new ManageEmployee();

		me.listEmployees();
		me.listAddress();

		int count = 0;

		while (count < empList.size()) {

			me.addAddress(address, empList.get(count));
			me.addEmployee(empList.get(count), address);

			count++;
		}

		/*
		 * for (Employee emp : empList) { me.addEmployee(emp); }
		 */

		me.listEmployees();
		me.listAddress();

		me.updateEmployee(71, 9999);

		me.listEmployees();
		me.listAddress();

		for (int id : empIdList) {
			// me.deleteEmployee(id);
		}
		for (int id : addressIdList) {
			// me.deleteAddress(id);
		}

		me.listEmployees();
		System.out.println(ManageEmployee.class + " testEmployeeDB exit...");
	}

	// Method to read all the employees
	public void listEmployees() {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " listEmployees.... " + session);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");

			Query query = session.createQuery("FROM com.test.hibernate.xml.pojo.mapping.association.oto.ufka.Employee");
			System.out.println("Query Created....");
			List<Employee> employees = query.list();
			System.out.println(this.getClass() + " listEmployees.... " + employees.size());
			empIdList = new ArrayList<>();

			for (Employee employee : employees) {
				empIdList.add(employee.getId());
				System.out.print(" Id: " + employee.getId());
				System.out.print(" First Name: " + employee.getFirstName());
				System.out.print(" Last Name: " + employee.getLastName());
				System.out.println(" Salary: " + employee.getSalary());
				System.out.println(" Address: " + employee.getAddr());
			}
			System.out.println("commiting transaction....");
			tx.commit();
		} finally {
			session.close();
		}
	}

	public void listAddress() {
		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " listAddress.... " + session);
		Transaction tx = null;

		try {

			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Query query = session.createQuery(" FROM com.test.hibernate.xml.pojo.mapping.association.oto.ufka.Address");
			System.out.println("Query Created....");
			List<Address> addresses = query.list();
			System.out.println(this.getClass() + " listAddress.... " + addresses.size());

			addressIdList = new ArrayList<Integer>();
			for (Address address : addresses) {
				addressIdList.add(address.getId());
				System.out.print(" Id: " + address.getId());
				System.out.println(address);
			}
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

	public Address addAddress(Address address, Employee employee) {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " addAddress.. " + session);
		address.setEmployee(employee);

		Transaction tx = null;
		Integer addressId = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin Transaction");

			addressId = (Integer) session.save(address);

			tx.commit();
			System.out.println("Transaction Committed... " + addressId);
		} finally {
			session.close();
		}
		return address;
	}

	// Method to create an employee in database
	public Integer addEmployee(Employee employee, Address address) {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " addEmployee.. " + session);
		Transaction tx = null;
		Integer employeeId = null;
		employee.setAddr(address);
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
		System.out.println("\n" + this.getClass() + " updateEmployee.... " + session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = (Employee) session.get(Employee.class, id);
			if (employee == null) {
				System.out.println(this.getClass() + "**** employee not found with Id " + id);
				return;
			}
			System.out.println(this.getClass() + " updateEmployee.... " + employee);
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
		System.out.println("\n" + this.getClass() + " deleteEmployee.... " + id + " " + session);
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Employee employee = session.get(Employee.class, id);
			System.out.println(this.getClass() + " deleteEmployee.... " + employee);
			session.delete(employee);
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

	// Method for deleting Address.
	public void deleteAddress(int id) {

		Session session = factory.openSession();
		System.out.println("\n" + this.getClass() + " deleteAddress.... " + id + " " + session);
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			System.out.println("Begin transaction....");
			Address address = session.get(Address.class, id);
			System.out.println(this.getClass() + " deleteAddress.... " + address);
			session.delete(address);
			System.out.println("commiting transaction....");
			tx.commit();

		} finally {
			session.close();
		}
	}

}
